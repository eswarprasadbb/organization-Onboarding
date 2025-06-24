package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationApiKeyDto;
import com.example.organizationservice.model.OrganizationApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationApiKeyMapper {
    OrganizationApiKeyMapper INSTANCE = Mappers.getMapper(OrganizationApiKeyMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "plaintextKey", ignore = true)
    OrganizationApiKeyDto toDto(OrganizationApiKey entity);

}
