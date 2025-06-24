package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationDivisionDto;
import com.example.organizationservice.mapper.OrganizationDivisionMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationDivision;
import com.example.organizationservice.repository.OrganizationDivisionRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationDivisionService {
    private final OrganizationDivisionRepository repository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationDivisionMapper mapper;

    public List<OrganizationDivisionDto> getByOrganization(UUID orgId) {
        return repository.findByOrganization_Id(orgId).stream().map(mapper::toDto).toList();
    }

    public OrganizationDivisionDto getById(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    public OrganizationDivisionDto create(UUID orgId, OrganizationDivisionDto dto) {
        Organization organization = organizationRepository.findById(orgId).orElseThrow();
        OrganizationDivision entity = mapper.toEntity(dto);
        entity.setOrganization(organization);
        return mapper.toDto(repository.save(entity));
    }
}
