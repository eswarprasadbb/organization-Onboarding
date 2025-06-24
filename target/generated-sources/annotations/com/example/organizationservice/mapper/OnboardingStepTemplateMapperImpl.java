package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OnboardingStepTemplateDto;
import com.example.organizationservice.model.OnboardingStepTemplate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OnboardingStepTemplateMapperImpl implements OnboardingStepTemplateMapper {

    @Override
    public OnboardingStepTemplateDto toDto(OnboardingStepTemplate entity) {
        if ( entity == null ) {
            return null;
        }

        OnboardingStepTemplateDto onboardingStepTemplateDto = new OnboardingStepTemplateDto();

        onboardingStepTemplateDto.setId( entity.getId() );
        onboardingStepTemplateDto.setName( entity.getName() );
        onboardingStepTemplateDto.setDescription( entity.getDescription() );
        onboardingStepTemplateDto.setStepOrder( entity.getStepOrder() );
        onboardingStepTemplateDto.setConfig( entity.getConfig() );
        onboardingStepTemplateDto.setActive( entity.getActive() );
        onboardingStepTemplateDto.setCreatedAt( entity.getCreatedAt() );
        onboardingStepTemplateDto.setUpdatedAt( entity.getUpdatedAt() );

        return onboardingStepTemplateDto;
    }

    @Override
    public OnboardingStepTemplate toEntity(OnboardingStepTemplateDto dto) {
        if ( dto == null ) {
            return null;
        }

        OnboardingStepTemplate.OnboardingStepTemplateBuilder onboardingStepTemplate = OnboardingStepTemplate.builder();

        onboardingStepTemplate.id( dto.getId() );
        onboardingStepTemplate.name( dto.getName() );
        onboardingStepTemplate.description( dto.getDescription() );
        onboardingStepTemplate.stepOrder( dto.getStepOrder() );
        onboardingStepTemplate.config( dto.getConfig() );
        onboardingStepTemplate.active( dto.getActive() );
        onboardingStepTemplate.createdAt( dto.getCreatedAt() );
        onboardingStepTemplate.updatedAt( dto.getUpdatedAt() );

        return onboardingStepTemplate.build();
    }
}
