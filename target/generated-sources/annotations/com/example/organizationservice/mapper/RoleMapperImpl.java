package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.RoleDto;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.Role;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setOrganizationId( entityOrganizationId( entity ) );
        roleDto.setId( entity.getId() );
        roleDto.setName( entity.getName() );
        roleDto.setDescription( entity.getDescription() );
        roleDto.setCreatedAt( entity.getCreatedAt() );
        roleDto.setUpdatedAt( entity.getUpdatedAt() );

        return roleDto;
    }

    @Override
    public Role toEntity(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role.RoleBuilder role = Role.builder();

        role.id( dto.getId() );
        role.name( dto.getName() );
        role.description( dto.getDescription() );
        role.createdAt( dto.getCreatedAt() );
        role.updatedAt( dto.getUpdatedAt() );

        return role.build();
    }

    private UUID entityOrganizationId(Role role) {
        if ( role == null ) {
            return null;
        }
        Organization organization = role.getOrganization();
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
