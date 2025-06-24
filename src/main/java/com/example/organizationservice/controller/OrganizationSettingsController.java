package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationSettingsDto;
import com.example.organizationservice.service.OrganizationSettingsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/organizations/{orgId}/settings")
@RequiredArgsConstructor
@Tag(name = "Organization Settings")
public class OrganizationSettingsController {

    private final OrganizationSettingsService settingsService;

    @GetMapping
    public OrganizationSettingsDto get(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId) {
        return settingsService.get(orgId);
    }

    @PutMapping
    public ResponseEntity<OrganizationSettingsDto> upsert(@PathVariable("orgId") UUID orgId,
                                                          @RequestBody OrganizationSettingsDto dto) {
        OrganizationSettingsDto saved = settingsService.upsert(orgId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }
}
