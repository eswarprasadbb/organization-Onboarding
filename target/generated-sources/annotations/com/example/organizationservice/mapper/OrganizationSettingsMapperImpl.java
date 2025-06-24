package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationSettingsDto;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationSettings;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OrganizationSettingsMapperImpl implements OrganizationSettingsMapper {

    @Override
    public OrganizationSettingsDto toDto(OrganizationSettings entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationSettingsDto organizationSettingsDto = new OrganizationSettingsDto();

        organizationSettingsDto.setOrganizationId( entityOrganizationId( entity ) );
        organizationSettingsDto.setId( entity.getId() );
        Map<String, Object> map = entity.getSettings();
        if ( map != null ) {
            organizationSettingsDto.setSettings( new LinkedHashMap<String, Object>( map ) );
        }
        organizationSettingsDto.setCreatedAt( entity.getCreatedAt() );
        organizationSettingsDto.setUpdatedAt( entity.getUpdatedAt() );

        return organizationSettingsDto;
    }

    @Override
    public OrganizationSettings toEntity(OrganizationSettingsDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrganizationSettings.OrganizationSettingsBuilder organizationSettings = OrganizationSettings.builder();

        organizationSettings.id( dto.getId() );
        Map<String, Object> map = dto.getSettings();
        if ( map != null ) {
            organizationSettings.settings( new LinkedHashMap<String, Object>( map ) );
        }
        organizationSettings.createdAt( dto.getCreatedAt() );
        organizationSettings.updatedAt( dto.getUpdatedAt() );

        return organizationSettings.build();
    }

    private UUID entityOrganizationId(OrganizationSettings organizationSettings) {
        if ( organizationSettings == null ) {
            return null;
        }
        Organization organization = organizationSettings.getOrganization();
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
