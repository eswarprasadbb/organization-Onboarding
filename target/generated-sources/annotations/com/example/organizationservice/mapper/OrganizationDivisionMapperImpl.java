package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationDivisionDto;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationDivision;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OrganizationDivisionMapperImpl implements OrganizationDivisionMapper {

    @Override
    public OrganizationDivisionDto toDto(OrganizationDivision entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationDivisionDto organizationDivisionDto = new OrganizationDivisionDto();

        organizationDivisionDto.setOrganizationId( entityOrganizationId( entity ) );
        organizationDivisionDto.setId( entity.getId() );
        organizationDivisionDto.setName( entity.getName() );
        organizationDivisionDto.setDescription( entity.getDescription() );
        organizationDivisionDto.setEmail( entity.getEmail() );
        organizationDivisionDto.setStatus( entity.getStatus() );

        return organizationDivisionDto;
    }

    @Override
    public OrganizationDivision toEntity(OrganizationDivisionDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrganizationDivision.OrganizationDivisionBuilder organizationDivision = OrganizationDivision.builder();

        organizationDivision.id( dto.getId() );
        organizationDivision.name( dto.getName() );
        organizationDivision.description( dto.getDescription() );
        organizationDivision.email( dto.getEmail() );
        organizationDivision.status( dto.getStatus() );

        return organizationDivision.build();
    }

    private UUID entityOrganizationId(OrganizationDivision organizationDivision) {
        if ( organizationDivision == null ) {
            return null;
        }
        Organization organization = organizationDivision.getOrganization();
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
