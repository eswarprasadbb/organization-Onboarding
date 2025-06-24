package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationAddressDto;
import com.example.organizationservice.model.OrganizationAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationAddressMapper {
    OrganizationAddressMapper INSTANCE = Mappers.getMapper(OrganizationAddressMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    OrganizationAddressDto toDto(OrganizationAddress entity);

    @Mapping(target = "organization", ignore = true)
    OrganizationAddress toEntity(OrganizationAddressDto dto);
}
