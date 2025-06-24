package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.AuditLogDto;
import com.example.organizationservice.model.AuditLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {
    AuditLogMapper INSTANCE = Mappers.getMapper(AuditLogMapper.class);

    @Mapping(source = "actorUser.id", target = "actorUserId")
    AuditLogDto toDto(AuditLog entity);
}
