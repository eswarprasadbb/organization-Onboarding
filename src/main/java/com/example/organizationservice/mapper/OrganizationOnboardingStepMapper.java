package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationOnboardingStepDto;
import com.example.organizationservice.model.OrganizationOnboardingStep;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationOnboardingStepMapper {
    OrganizationOnboardingStepMapper INSTANCE = Mappers.getMapper(OrganizationOnboardingStepMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    OrganizationOnboardingStepDto toDto(OrganizationOnboardingStep entity);

    @Mapping(target = "organization", ignore = true)
    OrganizationOnboardingStep toEntity(OrganizationOnboardingStepDto dto);
}
