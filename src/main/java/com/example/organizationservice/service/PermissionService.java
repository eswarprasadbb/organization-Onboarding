package com.example.organizationservice.service;

import com.example.organizationservice.dto.PermissionDto;
import com.example.organizationservice.mapper.PermissionMapper;
import com.example.organizationservice.model.Permission;
import com.example.organizationservice.repository.PermissionRepository;
import com.example.organizationservice.repository.RoleRepository;
import com.example.organizationservice.model.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository repository;
    private final RoleRepository roleRepository;
    private final PermissionMapper mapper;

    public List<PermissionDto> list() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public PermissionDto get(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    public PermissionDto create(PermissionDto dto) {
        if (repository.existsByName(dto.getName())) {
            throw new IllegalStateException("Permission name already exists");
        }
        Permission entity = mapper.toEntity(dto);
        Permission saved = repository.save(entity);

        // auto-grant to all OWNER roles
        List<Role> ownerRoles = roleRepository.findAll().stream()
                .filter(r -> "OWNER".equalsIgnoreCase(r.getName()))
                .toList();
        ownerRoles.forEach(r -> r.getPermissions().add(saved));
        roleRepository.saveAll(ownerRoles);

        return mapper.toDto(saved);
    }

    @Transactional
    public PermissionDto update(UUID id, PermissionDto dto) {
        Permission perm = repository.findById(id).orElseThrow();
        perm.setName(dto.getName());
        perm.setDescription(dto.getDescription());
        return mapper.toDto(perm);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
