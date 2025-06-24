package com.example.organizationservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class SystemSettingDto {
    private UUID id;
    private String key;
    private String value;
    private Instant createdAt;
    private Instant updatedAt;
}
