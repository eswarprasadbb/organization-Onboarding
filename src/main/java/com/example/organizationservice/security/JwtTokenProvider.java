package com.example.organizationservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private final JwtProperties properties;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public JwtTokenProvider(JwtProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        try {
            var privPem = new String(getClass().getClassLoader().getResourceAsStream("keystore/private_key.pem").readAllBytes(), StandardCharsets.UTF_8);
            var pubPem = new String(getClass().getClassLoader().getResourceAsStream("keystore/public_key.pem").readAllBytes(), StandardCharsets.UTF_8);
            String priv = privPem.replace("\r", "")
                    .lines()
                    .filter(l -> !l.startsWith("-----"))
                    .reduce("", (a,b) -> a + b);
            String pub = pubPem.replace("\r", "")
                    .lines()
                    .filter(l -> !l.startsWith("-----"))
                    .reduce("", (a,b) -> a + b);
            byte[] privKeyBytes = Decoders.BASE64.decode(priv);
            byte[] pubKeyBytes = Decoders.BASE64.decode(pub);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privKeyBytes));
            publicKey = kf.generatePublic(new X509EncodedKeySpec(pubKeyBytes));
        } catch (Exception ex) {
            // Fallback: generate in-memory RSA key pair for dev environments
            try {
                java.security.KeyPairGenerator kpg = java.security.KeyPairGenerator.getInstance("RSA");
                kpg.initialize(2048);
                java.security.KeyPair kp = kpg.generateKeyPair();
                privateKey = kp.getPrivate();
                publicKey = kp.getPublic();
                System.err.println("[WARN] JwtTokenProvider – PEM keys missing or invalid. Generated temporary in-memory RSA key pair. Tokens will be invalid after restart.");
            } catch (Exception genEx) {
                throw new IllegalStateException("Unable to load or generate RSA keys", ex);
            }
        }
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(properties.getAccessTokenValidityMinutes() * 60)))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public String generateRefreshToken(UUID userId) {
        // Use simple UUID string to keep token length well below DB limit (255 chars)
        return UUID.randomUUID().toString();
    }

    public Map<String, Object> extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, String claimKey, Class<T> type) {
        Map<String, Object> claims = extractAllClaims(token);
        return type.cast(claims.get(claimKey));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
