package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.SystemSettingDto;
import com.example.organizationservice.model.SystemSetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SystemSettingMapper {
    SystemSettingMapper INSTANCE = Mappers.getMapper(SystemSettingMapper.class);

    SystemSettingDto toDto(SystemSetting entity);

    SystemSetting toEntity(SystemSettingDto dto);
}
