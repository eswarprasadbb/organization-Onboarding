package com.example.organizationservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ApiCallLogDto {
    private UUID id;
    private UUID userId;
    private UUID organizationId;
    private String method;
    private String path;
    private Integer statusCode;
    private Long durationMs;
    private String ipAddress;
    private Instant createdAt;
}
