package com.example.organizationservice.service;

import com.example.organizationservice.dto.RoleDto;
import com.example.organizationservice.mapper.RoleMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.Role;
import com.example.organizationservice.model.Permission;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.repository.RoleRepository;
import com.example.organizationservice.repository.PermissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final OrganizationRepository organizationRepository;
    private final RoleMapper mapper;
    private final PermissionRepository permissionRepository;
    private final com.example.organizationservice.mapper.PermissionMapper permissionMapper;

    public List<RoleDto> list(UUID orgId) {
        return roleRepository.findByOrganization_Id(orgId).stream().map(mapper::toDto).toList();
    }

    public RoleDto get(UUID id) {
        return roleRepository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    public RoleDto create(UUID orgId, RoleDto dto) {
        if (roleRepository.existsByOrganization_IdAndName(orgId, dto.getName())) {
            throw new IllegalStateException("Role with the same name already exists in organization");
        }
        Organization org = organizationRepository.findById(orgId).orElseThrow();
        Role entity = mapper.toEntity(dto);
        entity.setOrganization(org);
        return mapper.toDto(roleRepository.save(entity));
    }

    @Transactional
    public RoleDto update(UUID id, RoleDto dto) {
        Role role = roleRepository.findById(id).orElseThrow();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        return mapper.toDto(role);
    }

    @Transactional
    public void delete(UUID id) {
        roleRepository.deleteById(id);
    }

    // ---- permissions management ----
    public List<com.example.organizationservice.dto.PermissionDto> listPermissions(UUID roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow();
        return role.getPermissions().stream().map(permissionMapper::toDto).toList();
    }

    @Transactional
    public void addPermission(UUID roleId, UUID permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow();
        Permission perm = permissionRepository.findById(permissionId).orElseThrow();
        role.getPermissions().add(perm);
        roleRepository.save(role);
    }

    @Transactional
    public void removePermission(UUID roleId, UUID permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow();
        role.getPermissions().removeIf(p -> p.getId().equals(permissionId));
        roleRepository.save(role);
    }
}
