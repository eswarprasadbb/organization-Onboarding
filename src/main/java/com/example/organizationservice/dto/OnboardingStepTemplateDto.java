package com.example.organizationservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class OnboardingStepTemplateDto {
    private UUID id;
    private String name;
    private String description;
    private Integer stepOrder;
    private String config;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
}
