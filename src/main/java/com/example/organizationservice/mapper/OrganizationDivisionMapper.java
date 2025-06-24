package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationDivisionDto;
import com.example.organizationservice.model.OrganizationDivision;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationDivisionMapper {
    OrganizationDivisionMapper INSTANCE = Mappers.getMapper(OrganizationDivisionMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    OrganizationDivisionDto toDto(OrganizationDivision entity);

    @Mapping(target = "organization", ignore = true)
    OrganizationDivision toEntity(OrganizationDivisionDto dto);
}
