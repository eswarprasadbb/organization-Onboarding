package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.UserDto;
import com.example.organizationservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "notificationPreferences", source = "preferences")
    UserDto toDto(User entity);

    @Mapping(target = "preferences", source = "notificationPreferences")
    User toEntity(UserDto dto);
}

