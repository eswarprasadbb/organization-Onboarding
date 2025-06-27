package com.example.organizationservice.controller;

import com.example.organizationservice.model.RefreshToken;
import com.example.organizationservice.repository.RefreshTokenRepository;
import com.example.organizationservice.model.Permission;
import com.example.organizationservice.repository.UserRepository;
import com.example.organizationservice.security.JwtProperties;
import com.example.organizationservice.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final JwtProperties properties;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthController(AuthenticationManager authenticationManager,
                         JwtTokenProvider tokenProvider,
                         JwtProperties properties,
                         UserRepository userRepository,
                         RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.properties = properties;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password()));
            
            // Get user details
            var userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            var user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
            
            // Generate tokens
            String accessToken = tokenProvider.generateToken(
                user.getEmail(),
                Map.of(
                    "userId", user.getId(),
                    "email", user.getEmail(),
                    "authorities", userDetails.getAuthorities().stream()
                            .map(org.springframework.security.core.GrantedAuthority::getAuthority)
                            .toList()
                )
            );
            String refreshToken = tokenProvider.generateRefreshToken(user.getId());
            
            // Save refresh token
            var refreshTokenEntity = new RefreshToken(
                user,
                refreshToken,
                Instant.now().plusSeconds(properties.getRefreshTokenValidityDays() * 24 * 60 * 60)
            );
            refreshTokenRepository.save(refreshTokenEntity);
            
            return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken, "Bearer"));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        try {
            String refreshToken = request.refreshToken();
            
            RefreshToken existingToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
                
            if (existingToken.isRevoked()) {
                throw new RuntimeException("Refresh token has been revoked");
            }

            // Generate new access token
            String accessToken = tokenProvider.generateToken(
                existingToken.getUser().getEmail(),
                Map.of(
                    "userId", existingToken.getUser().getId(),
                    "email", existingToken.getUser().getEmail(),
                    "authorities", existingToken.getUser().getRoles().stream()
                            .flatMap(role -> {
                                java.util.stream.Stream<String> roleAuth = java.util.stream.Stream.of("ROLE_" + role.getName());
                                java.util.stream.Stream<String> permAuth = role.getPermissions() == null ?
                                        java.util.stream.Stream.empty() :
                                        role.getPermissions().stream().map(Permission::getName);
                                return java.util.stream.Stream.concat(roleAuth, permAuth);
                            }).toList()
                )
            );

            // Generate new refresh token
            RefreshToken newRefreshToken = new RefreshToken();
            newRefreshToken.setToken(UUID.randomUUID().toString());
            newRefreshToken.setExpiryDate(Instant.now().plusSeconds(properties.getRefreshTokenValidityDays() * 24 * 60 * 60));
            newRefreshToken.setRevoked(false);
            newRefreshToken.setUser(existingToken.getUser());
            
            refreshTokenRepository.save(newRefreshToken);
            
            return ResponseEntity.ok(new LoginResponse(accessToken, newRefreshToken.getToken(), "Bearer"));
        } catch (Exception ex) {
            return ResponseEntity.status(401).body("Invalid refresh token");
        }
    }

    public record LoginRequest(String email, String password) {
    }

    public record LoginResponse(String accessToken, String refreshToken, String tokenType) {
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody RefreshTokenRequest request) {
        RefreshToken token = refreshTokenRepository.findByToken(request.refreshToken())
                .orElse(null);
        if (token != null) {
            token.setRevoked(true);
            refreshTokenRepository.save(token);
        }
        return ResponseEntity.ok().build();
    }

    public record RefreshTokenRequest(String refreshToken) {
    }
}
