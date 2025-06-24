package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationApiKeyDto;
import com.example.organizationservice.service.OrganizationApiKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizations/{orgId}/api-keys")
@RequiredArgsConstructor
@Tag(name = "Organization API Keys")
public class OrganizationApiKeyController {

    private final OrganizationApiKeyService apiKeyService;

    @GetMapping
    public List<OrganizationApiKeyDto> list(@PathVariable("orgId") UUID orgId) {
        return apiKeyService.list(orgId);
    }

    @PostMapping
    public ResponseEntity<OrganizationApiKeyDto> create(@PathVariable("orgId") UUID orgId,
                                                        @RequestBody OrganizationApiKeyDto request) {
        OrganizationApiKeyDto created = apiKeyService.create(orgId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{keyId}/revoke")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revoke(@PathVariable("orgId") UUID orgId, @PathVariable("keyId") UUID keyId) {
        apiKeyService.revoke(keyId);
    }
}
