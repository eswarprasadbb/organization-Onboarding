package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationSettingsDto;
import com.example.organizationservice.model.OrganizationSettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationSettingsMapper {
    OrganizationSettingsMapper INSTANCE = Mappers.getMapper(OrganizationSettingsMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    OrganizationSettingsDto toDto(OrganizationSettings entity);

    @Mapping(target = "organization", ignore = true)
    OrganizationSettings toEntity(OrganizationSettingsDto dto);
}
