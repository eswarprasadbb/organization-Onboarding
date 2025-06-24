package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OAuthProviderDto;
import com.example.organizationservice.model.OAuthProvider;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OAuthProviderMapper {
    OAuthProviderMapper INSTANCE = Mappers.getMapper(OAuthProviderMapper.class);

    OAuthProviderDto toDto(OAuthProvider entity);

    OAuthProvider toEntity(OAuthProviderDto dto);
}
