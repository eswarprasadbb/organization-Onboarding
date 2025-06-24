package com.example.organizationservice.controller;

import com.example.organizationservice.dto.CustomerDto;
import com.example.organizationservice.service.CustomerService;
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
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/organizations/{orgId}/customers")
    @Operation(summary = "List customers for organization")
    public List<CustomerDto> listByOrg(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId) {
        return service.listByOrganization(orgId);
    }

    @GetMapping("/divisions/{divisionId}/customers")
    @Operation(summary = "List customers for division")
    public List<CustomerDto> listByDivision(@Parameter(description = "Division ID", required = true) @PathVariable("divisionId") UUID divisionId) {
        return service.listByDivision(divisionId);
    }

    @GetMapping("/customers/{id}")
    @Operation(summary = "Get customer by id")
    public CustomerDto get(@Parameter(description = "Customer ID", required = true) @PathVariable("id") UUID id) {
        return service.get(id);
    }

    @PostMapping("/organizations/{orgId}/customers")
    @Operation(summary = "Create customer under organization")
    public ResponseEntity<CustomerDto> createForOrg(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
                                              @RequestBody CustomerDto dto) {
        CustomerDto created = service.createInOrganization(orgId, dto);
        return ResponseEntity.created(URI.create("/api/customers/" + created.getId())).body(created);
    }

    @PostMapping("/divisions/{divisionId}/customers")
    @Operation(summary = "Create customer under division")
    public ResponseEntity<CustomerDto> createForDivision(@Parameter(description = "Division ID", required = true) @PathVariable("divisionId") UUID divisionId,
                                                  @RequestBody CustomerDto dto) {
        CustomerDto created = service.createInDivision(divisionId, dto);
        return ResponseEntity.created(URI.create("/api/customers/" + created.getId())).body(created);
    }
}
