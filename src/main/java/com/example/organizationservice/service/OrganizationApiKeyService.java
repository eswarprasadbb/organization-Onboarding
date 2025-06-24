package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationApiKeyDto;
import com.example.organizationservice.mapper.OrganizationApiKeyMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationApiKey;
import com.example.organizationservice.model.OrganizationApiKey.Status;
import com.example.organizationservice.repository.OrganizationApiKeyRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationApiKeyService {

    private static final int KEY_BYTES = 32; // 256-bit
    private static final SecureRandom RANDOM = new SecureRandom();

    private final OrganizationApiKeyRepository apiKeyRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationApiKeyMapper mapper;

    public List<OrganizationApiKeyDto> list(UUID orgId) {
        return apiKeyRepository.findByOrganizationId(orgId).stream().map(mapper::toDto).toList();
    }

    /**
     * Generates a new API key, returns plaintext once, stores hash and prefix.
     */
    @Transactional
    public OrganizationApiKeyDto create(UUID orgId, OrganizationApiKeyDto request) {
        Organization org = organizationRepository.findById(orgId).orElseThrow();

        // generate key
        byte[] bytes = new byte[KEY_BYTES];
        RANDOM.nextBytes(bytes);
        String plaintext = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        String prefix = plaintext.substring(0, 8);
        String hash = hash(plaintext);

        OrganizationApiKey entity = OrganizationApiKey.builder()
                .organization(org)
                .name(request.getName())
                .keyHash(hash)
                .keyPrefix(prefix)
                .scopes(request.getScopes())
                .expiresAt(request.getExpiresAt())
                .status(Status.ACTIVE)
                .build();
        OrganizationApiKey saved = apiKeyRepository.save(entity);
        OrganizationApiKeyDto dto = mapper.toDto(saved);
        dto.setPlaintextKey(plaintext);
        return dto;
    }

    @Transactional
    public void revoke(UUID keyId) {
        OrganizationApiKey key = apiKeyRepository.findById(keyId).orElseThrow();
        key.setStatus(Status.REVOKED);
        key.setRevokedAt(java.time.Instant.now());
    }

    private String hash(String plaintext) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(plaintext.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (Exception e) {
            throw new RuntimeException("Unable to hash API key", e);
        }
    }
}
