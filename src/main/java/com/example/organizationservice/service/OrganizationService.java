package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.mapper.OrganizationMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.OrganizationRepository;
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

    public List<OrganizationDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public OrganizationDto getById(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    public OrganizationDto create(OrganizationDto dto) {
        Organization entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }
}
