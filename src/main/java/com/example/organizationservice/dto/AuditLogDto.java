package com.example.organizationservice.dto;

import com.example.organizationservice.model.AuditAction;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AuditLogDto {
    private UUID id;
    private UUID actorUserId;
    private String entity;
    private UUID entityId;
    private AuditAction action;
    private String details;
    private String ipAddress;
    private Instant createdAt;
}
