package com.example.organizationservice.controller;

import com.example.organizationservice.dto.RoleDto;
import com.example.organizationservice.dto.PermissionDto;
import com.example.organizationservice.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/organizations/{orgId}/roles")
@RequiredArgsConstructor
@Tag(name = "Roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @Operation(summary = "List roles for organization")
    public List<RoleDto> list(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId) {
        return roleService.list(orgId);
    }

    @PostMapping
    @Operation(summary = "Create role")
    public ResponseEntity<RoleDto> create(@PathVariable("orgId") UUID orgId, @RequestBody RoleDto dto) {
        RoleDto created = roleService.create(orgId, dto);
        return ResponseEntity.created(URI.create("/api/organizations/" + orgId + "/roles/" + created.getId())).body(created);
    }

    @GetMapping("/{roleId}")
    @Operation(summary = "Get role by id")
    public RoleDto get(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
                       @PathVariable("roleId") UUID roleId) {
        return roleService.get(roleId);
    }

    @PutMapping("/{roleId}")
    @Operation(summary = "Update role")
    public RoleDto update(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
                       @PathVariable("roleId") UUID roleId,
                       @RequestBody RoleDto dto) {
        return roleService.update(roleId, dto);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete role")
    public void delete(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
                  @PathVariable("roleId") UUID roleId) {
        roleService.delete(roleId);
    }

    // ----- permissions -----
    @GetMapping("/{roleId}/permissions")
    @Operation(summary = "List permissions for role")
    public List<PermissionDto> listPermissions(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
                                              @PathVariable("roleId") UUID roleId) {
        return roleService.listPermissions(roleId);
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Add permission to role")
    public void addPermission(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
                          @PathVariable("roleId") UUID roleId,
                          @PathVariable("permissionId") UUID permissionId) {
        roleService.addPermission(roleId, permissionId);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove permission from role")
    public void removePermission(@Parameter(description = "Organization ID", required = true) @PathVariable("orgId") UUID orgId,
                              @PathVariable("roleId") UUID roleId,
                              @PathVariable("permissionId") UUID permissionId) {
        roleService.removePermission(roleId, permissionId);
    }
}
