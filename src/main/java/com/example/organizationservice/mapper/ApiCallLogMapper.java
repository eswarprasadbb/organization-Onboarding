package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.ApiCallLogDto;
import com.example.organizationservice.model.ApiCallLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApiCallLogMapper {
    ApiCallLogMapper INSTANCE = Mappers.getMapper(ApiCallLogMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "organization.id", target = "organizationId")
    ApiCallLogDto toDto(ApiCallLog entity);
}
