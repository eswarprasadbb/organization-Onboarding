package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.PermissionDto;
import com.example.organizationservice.model.Permission;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionDto toDto(Permission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( entity.getId() );
        permissionDto.setName( entity.getName() );
        permissionDto.setDescription( entity.getDescription() );
        permissionDto.setCreatedAt( entity.getCreatedAt() );
        permissionDto.setUpdatedAt( entity.getUpdatedAt() );

        return permissionDto;
    }

    @Override
    public Permission toEntity(PermissionDto dto) {
        if ( dto == null ) {
            return null;
        }

        Permission.PermissionBuilder permission = Permission.builder();

        permission.id( dto.getId() );
        permission.name( dto.getName() );
        permission.description( dto.getDescription() );
        permission.createdAt( dto.getCreatedAt() );
        permission.updatedAt( dto.getUpdatedAt() );

        return permission.build();
    }
}
