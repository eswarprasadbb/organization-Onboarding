package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationAddressDto;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationAddress;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:08+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OrganizationAddressMapperImpl implements OrganizationAddressMapper {

    @Override
    public OrganizationAddressDto toDto(OrganizationAddress entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationAddressDto organizationAddressDto = new OrganizationAddressDto();

        organizationAddressDto.setOrganizationId( entityOrganizationId( entity ) );
        organizationAddressDto.setId( entity.getId() );
        organizationAddressDto.setType( entity.getType() );
        organizationAddressDto.setLine1( entity.getLine1() );
        organizationAddressDto.setLine2( entity.getLine2() );
        organizationAddressDto.setCity( entity.getCity() );
        organizationAddressDto.setState( entity.getState() );
        organizationAddressDto.setPostalCode( entity.getPostalCode() );
        organizationAddressDto.setCountry( entity.getCountry() );
        organizationAddressDto.setLatitude( entity.getLatitude() );
        organizationAddressDto.setLongitude( entity.getLongitude() );
        organizationAddressDto.setActive( entity.getActive() );
        organizationAddressDto.setCreatedAt( entity.getCreatedAt() );
        organizationAddressDto.setUpdatedAt( entity.getUpdatedAt() );

        return organizationAddressDto;
    }

    @Override
    public OrganizationAddress toEntity(OrganizationAddressDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrganizationAddress.OrganizationAddressBuilder organizationAddress = OrganizationAddress.builder();

        organizationAddress.id( dto.getId() );
        organizationAddress.type( dto.getType() );
        organizationAddress.line1( dto.getLine1() );
        organizationAddress.line2( dto.getLine2() );
        organizationAddress.city( dto.getCity() );
        organizationAddress.state( dto.getState() );
        organizationAddress.postalCode( dto.getPostalCode() );
        organizationAddress.country( dto.getCountry() );
        organizationAddress.latitude( dto.getLatitude() );
        organizationAddress.longitude( dto.getLongitude() );
        organizationAddress.active( dto.getActive() );
        organizationAddress.createdAt( dto.getCreatedAt() );
        organizationAddress.updatedAt( dto.getUpdatedAt() );

        return organizationAddress.build();
    }

    private UUID entityOrganizationId(OrganizationAddress organizationAddress) {
        if ( organizationAddress == null ) {
            return null;
        }
        Organization organization = organizationAddress.getOrganization();
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
