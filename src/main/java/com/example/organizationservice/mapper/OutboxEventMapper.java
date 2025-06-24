package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OutboxEventDto;
import com.example.organizationservice.model.OutboxEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OutboxEventMapper {
    OutboxEventMapper INSTANCE = Mappers.getMapper(OutboxEventMapper.class);

    OutboxEventDto toDto(OutboxEvent entity);
    OutboxEvent toEntity(OutboxEventDto dto);
}
