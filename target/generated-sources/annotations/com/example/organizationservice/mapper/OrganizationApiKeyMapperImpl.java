package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationApiKeyDto;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationApiKey;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OrganizationApiKeyMapperImpl implements OrganizationApiKeyMapper {

    @Override
    public OrganizationApiKeyDto toDto(OrganizationApiKey entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationApiKeyDto organizationApiKeyDto = new OrganizationApiKeyDto();

        organizationApiKeyDto.setOrganizationId( entityOrganizationId( entity ) );
        organizationApiKeyDto.setId( entity.getId() );
        organizationApiKeyDto.setName( entity.getName() );
        organizationApiKeyDto.setKeyPrefix( entity.getKeyPrefix() );
        Map<String, Object> map = entity.getScopes();
        if ( map != null ) {
            organizationApiKeyDto.setScopes( new LinkedHashMap<String, Object>( map ) );
        }
        organizationApiKeyDto.setExpiresAt( entity.getExpiresAt() );
        organizationApiKeyDto.setStatus( entity.getStatus() );
        organizationApiKeyDto.setCreatedAt( entity.getCreatedAt() );
        organizationApiKeyDto.setRevokedAt( entity.getRevokedAt() );

        return organizationApiKeyDto;
    }

    private UUID entityOrganizationId(OrganizationApiKey organizationApiKey) {
        if ( organizationApiKey == null ) {
            return null;
        }
        Organization organization = organizationApiKey.getOrganization();
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
