package com.example.organizationservice.service;

import com.example.organizationservice.dto.CustomerDto;
import com.example.organizationservice.mapper.CustomerMapper;
import com.example.organizationservice.model.Customer;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationDivision;
import com.example.organizationservice.repository.CustomerRepository;
import com.example.organizationservice.repository.OrganizationDivisionRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationDivisionRepository divisionRepository;
    private final CustomerMapper mapper;

    public List<CustomerDto> listByOrganization(UUID orgId) {
        return repository.findByOrganization_Id(orgId).stream().map(mapper::toDto).toList();
    }

    public List<CustomerDto> listByDivision(UUID divisionId) {
        return repository.findByDivision_Id(divisionId).stream().map(mapper::toDto).toList();
    }

    public CustomerDto get(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    public CustomerDto createInOrganization(UUID orgId, CustomerDto dto) {
        Organization organization = organizationRepository.findById(orgId).orElseThrow();
        Customer entity = mapper.toEntity(dto);
        entity.setOrganization(organization);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public CustomerDto createInDivision(UUID divisionId, CustomerDto dto) {
        OrganizationDivision division = divisionRepository.findById(divisionId).orElseThrow();
        Customer entity = mapper.toEntity(dto);
        entity.setDivision(division);
        return mapper.toDto(repository.save(entity));
    }
}
