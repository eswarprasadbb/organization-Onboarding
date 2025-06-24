package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OnboardingStepTemplateDto;
import com.example.organizationservice.model.OnboardingStepTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OnboardingStepTemplateMapper {
    OnboardingStepTemplateMapper INSTANCE = Mappers.getMapper(OnboardingStepTemplateMapper.class);

    OnboardingStepTemplateDto toDto(OnboardingStepTemplate entity);

    OnboardingStepTemplate toEntity(OnboardingStepTemplateDto dto);
}
