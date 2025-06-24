package com.example.organizationservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RateLimitRuleDto {
    private UUID id;
    private String scopeType; // GLOBAL, ORGANIZATION, USER
    private UUID scopeId;
    private Integer windowSeconds;
    private Integer maxRequests;
    private Instant createdAt;
    private Instant updatedAt;
}
