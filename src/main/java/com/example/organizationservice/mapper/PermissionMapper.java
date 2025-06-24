package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.PermissionDto;
import com.example.organizationservice.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    PermissionDto toDto(Permission entity);
    Permission toEntity(PermissionDto dto);
}
