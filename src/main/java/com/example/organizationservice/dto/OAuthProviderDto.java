package com.example.organizationservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class OAuthProviderDto {
    private UUID id;
    private String name;
    private String clientId;
    private String clientSecret;
    private String authUrl;
    private String tokenUrl;
    private String scopes;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
}
