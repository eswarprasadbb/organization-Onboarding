package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationAddressDto;
import com.example.organizationservice.mapper.OrganizationAddressMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationAddress;
import com.example.organizationservice.repository.OrganizationAddressRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationAddressService {

    private final OrganizationAddressRepository addressRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationAddressMapper mapper;

    public List<OrganizationAddressDto> list(UUID orgId) {
        return addressRepository.findByOrganizationId(orgId).stream().map(mapper::toDto).toList();
    }

    @Transactional
    public OrganizationAddressDto create(UUID orgId, OrganizationAddressDto dto) {
        Organization org = organizationRepository.findById(orgId).orElseThrow();
        OrganizationAddress entity = mapper.toEntity(dto);
        entity.setOrganization(org);
        return mapper.toDto(addressRepository.save(entity));
    }

    @Transactional
    public OrganizationAddressDto update(UUID id, OrganizationAddressDto dto) {
        OrganizationAddress existing = addressRepository.findById(id).orElseThrow();
        existing.setType(dto.getType());
        existing.setLine1(dto.getLine1());
        existing.setLine2(dto.getLine2());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setPostalCode(dto.getPostalCode());
        existing.setCountry(dto.getCountry());
        existing.setLatitude(dto.getLatitude());
        existing.setLongitude(dto.getLongitude());
        existing.setActive(dto.getActive());
        return mapper.toDto(addressRepository.save(existing));
    }

    public void delete(UUID id) {
        addressRepository.deleteById(id);
    }
}
