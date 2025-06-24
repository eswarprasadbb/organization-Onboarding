package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.RoleDto;
import com.example.organizationservice.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    RoleDto toDto(Role entity);

    @Mapping(target = "organization", ignore = true)
    Role toEntity(RoleDto dto);
}
