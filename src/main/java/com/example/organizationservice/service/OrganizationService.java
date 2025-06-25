package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.model.Role;
import com.example.organizationservice.mapper.OrganizationMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.repository.RoleRepository;
import com.example.organizationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository repository;
    private final OrganizationMapper mapper;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public List<OrganizationDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public OrganizationDto getById(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    public OrganizationDto create(OrganizationDto dto) {
        Organization entity = mapper.toEntity(dto);
        Organization savedOrg = repository.save(entity);

        // create default OWNER role
        if (!roleRepository.existsByOrganization_IdAndName(savedOrg.getId(), "OWNER")) {
            Role ownerRole = Role.builder()
                    .name("OWNER")
                    .organization(savedOrg)
                    .build();
            roleRepository.save(ownerRole);

            // link current authenticated user as OWNER, if present
            var auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                String email = auth.getName();
                userRepository.findByEmail(email).ifPresent(user -> {
                    user.getRoles().add(ownerRole);
                    userRepository.save(user);
                });
            }
        }

        return mapper.toDto(savedOrg);
    }
}
