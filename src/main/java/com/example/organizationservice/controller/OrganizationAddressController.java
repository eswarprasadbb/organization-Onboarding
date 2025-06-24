package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationAddressDto;
import com.example.organizationservice.service.OrganizationAddressService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizations/{orgId}/addresses")
@RequiredArgsConstructor
@Tag(name = "Organization Addresses")
public class OrganizationAddressController {

    private final OrganizationAddressService addressService;

    @GetMapping
    public List<OrganizationAddressDto> list(@PathVariable("orgId") UUID orgId) {
        return addressService.list(orgId);
    }

    @PostMapping
    public ResponseEntity<OrganizationAddressDto> create(
            @Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
            @RequestBody OrganizationAddressDto dto) {
        OrganizationAddressDto saved = addressService.create(orgId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public OrganizationAddressDto update(@PathVariable("orgId") UUID orgId, @PathVariable("id") UUID id, @RequestBody OrganizationAddressDto dto) {
        return addressService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("orgId") UUID orgId, @PathVariable("id") UUID id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
