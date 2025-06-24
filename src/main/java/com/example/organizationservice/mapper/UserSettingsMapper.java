package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.UserSettingsDto;
import com.example.organizationservice.model.UserSettings;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserSettingsMapper {
    UserSettingsMapper INSTANCE = Mappers.getMapper(UserSettingsMapper.class);

    @Mapping(source = "user.id", target = "userId")
    UserSettingsDto toDto(UserSettings entity);

    @InheritInverseConfiguration
    @Mapping(target = "user", ignore = true)
    UserSettings toEntity(UserSettingsDto dto);
}
