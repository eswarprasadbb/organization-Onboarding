package com.example.organizationservice.dto;

import com.example.organizationservice.model.OrganizationApiKey.Status;
import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
public class OrganizationApiKeyDto {
    private UUID id;
    private UUID organizationId;
    private String name;
    private String keyPrefix;
    private Map<String, Object> scopes;
    private Instant expiresAt;
    private Status status;
    private Instant createdAt;
    private Instant revokedAt;
    /**
     * Returned only on creation so the client can store it. Never persisted.
     */
    private String plaintextKey;
}
