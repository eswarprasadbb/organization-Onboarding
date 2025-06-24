package com.example.organizationservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
public class OrganizationSettingsDto {
    private UUID id;
    private UUID organizationId;
    private Map<String, Object> settings;
    private Instant createdAt;
    private Instant updatedAt;
}
