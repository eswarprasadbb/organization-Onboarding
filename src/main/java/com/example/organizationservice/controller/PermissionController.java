package com.example.organizationservice.controller;

import com.example.organizationservice.dto.PermissionDto;
import com.example.organizationservice.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@Tag(name = "Permissions")
public class PermissionController {

    private final PermissionService service;

    @GetMapping
    @Operation(summary = "List permissions")
    public List<PermissionDto> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get permission by id")
    public PermissionDto get(@PathVariable("id") UUID id) {
        return service.get(id);
    }

    @PostMapping
    @Operation(summary = "Create permission")
    public ResponseEntity<PermissionDto> create(@RequestBody PermissionDto dto) {
        PermissionDto created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/permissions/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update permission")
    public PermissionDto update(@PathVariable("id") UUID id, @RequestBody PermissionDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete permission")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
