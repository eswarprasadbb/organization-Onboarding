package com.example.organizationservice.dto;

import com.example.organizationservice.model.OrganizationMember;
import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
public class OrganizationMemberDto {
    private UUID id;
    private UUID organizationId;
    private UUID userId;
    private OrganizationMember.Role role;
    private Map<String, Object> permissions;
    private OrganizationMember.Status status;
    private Instant invitedAt;
    private Instant joinedAt;
    private UUID invitedBy;
    private Instant createdAt;
    private Instant updatedAt;
}
