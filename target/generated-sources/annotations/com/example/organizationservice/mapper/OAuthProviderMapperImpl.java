package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OAuthProviderDto;
import com.example.organizationservice.model.OAuthProvider;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OAuthProviderMapperImpl implements OAuthProviderMapper {

    @Override
    public OAuthProviderDto toDto(OAuthProvider entity) {
        if ( entity == null ) {
            return null;
        }

        OAuthProviderDto oAuthProviderDto = new OAuthProviderDto();

        oAuthProviderDto.setId( entity.getId() );
        oAuthProviderDto.setName( entity.getName() );
        oAuthProviderDto.setClientId( entity.getClientId() );
        oAuthProviderDto.setClientSecret( entity.getClientSecret() );
        oAuthProviderDto.setAuthUrl( entity.getAuthUrl() );
        oAuthProviderDto.setTokenUrl( entity.getTokenUrl() );
        oAuthProviderDto.setScopes( entity.getScopes() );
        oAuthProviderDto.setActive( entity.getActive() );
        oAuthProviderDto.setCreatedAt( entity.getCreatedAt() );
        oAuthProviderDto.setUpdatedAt( entity.getUpdatedAt() );

        return oAuthProviderDto;
    }

    @Override
    public OAuthProvider toEntity(OAuthProviderDto dto) {
        if ( dto == null ) {
            return null;
        }

        OAuthProvider.OAuthProviderBuilder oAuthProvider = OAuthProvider.builder();

        oAuthProvider.id( dto.getId() );
        oAuthProvider.name( dto.getName() );
        oAuthProvider.clientId( dto.getClientId() );
        oAuthProvider.clientSecret( dto.getClientSecret() );
        oAuthProvider.authUrl( dto.getAuthUrl() );
        oAuthProvider.tokenUrl( dto.getTokenUrl() );
        oAuthProvider.scopes( dto.getScopes() );
        oAuthProvider.active( dto.getActive() );
        oAuthProvider.createdAt( dto.getCreatedAt() );
        oAuthProvider.updatedAt( dto.getUpdatedAt() );

        return oAuthProvider.build();
    }
}
