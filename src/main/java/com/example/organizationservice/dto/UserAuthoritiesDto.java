package com.example.organizationservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * DTO that bundles a user's roles and their flattened permission names.
 */
@Data
public class UserAuthoritiesDto {
    private UUID userId;
    private List<String> roles;        // ROLE_ prefixed names
    private List<String> permissions;  // plain permission names
}
