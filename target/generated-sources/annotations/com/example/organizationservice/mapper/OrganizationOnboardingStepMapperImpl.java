package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationOnboardingStepDto;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationOnboardingStep;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OrganizationOnboardingStepMapperImpl implements OrganizationOnboardingStepMapper {

    @Override
    public OrganizationOnboardingStepDto toDto(OrganizationOnboardingStep entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationOnboardingStepDto organizationOnboardingStepDto = new OrganizationOnboardingStepDto();

        organizationOnboardingStepDto.setOrganizationId( entityOrganizationId( entity ) );
        organizationOnboardingStepDto.setId( entity.getId() );
        organizationOnboardingStepDto.setStep( entity.getStep() );
        organizationOnboardingStepDto.setStatus( entity.getStatus() );
        organizationOnboardingStepDto.setCompletedAt( entity.getCompletedAt() );
        organizationOnboardingStepDto.setCreatedAt( entity.getCreatedAt() );
        organizationOnboardingStepDto.setUpdatedAt( entity.getUpdatedAt() );

        return organizationOnboardingStepDto;
    }

    @Override
    public OrganizationOnboardingStep toEntity(OrganizationOnboardingStepDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrganizationOnboardingStep.OrganizationOnboardingStepBuilder organizationOnboardingStep = OrganizationOnboardingStep.builder();

        organizationOnboardingStep.id( dto.getId() );
        organizationOnboardingStep.step( dto.getStep() );
        organizationOnboardingStep.status( dto.getStatus() );
        organizationOnboardingStep.completedAt( dto.getCompletedAt() );
        organizationOnboardingStep.createdAt( dto.getCreatedAt() );
        organizationOnboardingStep.updatedAt( dto.getUpdatedAt() );

        return organizationOnboardingStep.build();
    }

    private UUID entityOrganizationId(OrganizationOnboardingStep organizationOnboardingStep) {
        if ( organizationOnboardingStep == null ) {
            return null;
        }
        Organization organization = organizationOnboardingStep.getOrganization();
        if ( organization == null ) {
            return null;
        }
        UUID id = organization.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
