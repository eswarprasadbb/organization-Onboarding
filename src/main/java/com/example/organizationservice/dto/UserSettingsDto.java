package com.example.organizationservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
public class UserSettingsDto {
    private UUID id;
    private UUID userId;
    private Map<String, Object> settings;
    private Instant createdAt;
    private Instant updatedAt;
}
