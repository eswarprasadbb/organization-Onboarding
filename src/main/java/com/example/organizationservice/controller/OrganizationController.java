package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService service;

    @GetMapping
    @PreAuthorize("hasAuthority('ORG_READ') or hasRole('SYSTEM_ADMIN')")
    @Operation(summary = "List organizations")
    public List<OrganizationDto> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ORG_READ') or hasRole('SYSTEM_ADMIN')")
    @Operation(summary = "Get organization by id")
    public OrganizationDto get(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ORG_CREATE') or isAuthenticated()")
    @Operation(summary = "Create organization")
    public ResponseEntity<OrganizationDto> create(@RequestBody OrganizationDto dto) {
        OrganizationDto created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/organizations/" + created.getId())).body(created);
    }
}
