package com.example.organizationservice.dto;

import com.example.organizationservice.model.OrganizationDivision;
import lombok.Data;

import java.util.UUID;

@Data
public class OrganizationDivisionDto {
    private UUID id;
    private UUID organizationId;
    private String name;
    private String description;
    private String email;
    private OrganizationDivision.DivisionStatus status;
}
