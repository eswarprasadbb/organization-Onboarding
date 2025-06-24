package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationDivisionDto;
import com.example.organizationservice.service.OrganizationDivisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrganizationDivisionController {

    private final OrganizationDivisionService service;

    @GetMapping("/organizations/{orgId}/divisions")
    @Operation(summary = "List divisions for an organization")
    public List<OrganizationDivisionDto> listByOrg(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId) {
        return service.getByOrganization(orgId);
    }

    @PostMapping("/organizations/{orgId}/divisions")
    @Operation(summary = "Create division under organization")
    public ResponseEntity<OrganizationDivisionDto> create(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
                                                    @RequestBody OrganizationDivisionDto dto) {
        OrganizationDivisionDto created = service.create(orgId, dto);
        return ResponseEntity.created(URI.create("/api/divisions/" + created.getId())).body(created);
    }

    @GetMapping("/divisions/{id}")
    @Operation(summary = "Get division by id")
    public OrganizationDivisionDto get(@Parameter(description = "Division ID", required = true) @PathVariable("id") UUID id) {
        return service.getById(id);
    }
}
