package com.example.organizationservice.dto;

import com.example.organizationservice.model.OutboxStatus;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class OutboxEventDto {
    private UUID id;
    private String aggregateType;
    private UUID aggregateId;
    private String eventType;
    private String payload;
    private OutboxStatus status;
    private Instant createdAt;
    private Instant sentAt;
}
