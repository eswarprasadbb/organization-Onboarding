package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.UserSettingsDto;
import com.example.organizationservice.model.User;
import com.example.organizationservice.model.UserSettings;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class UserSettingsMapperImpl implements UserSettingsMapper {

    @Override
    public UserSettingsDto toDto(UserSettings entity) {
        if ( entity == null ) {
            return null;
        }

        UserSettingsDto userSettingsDto = new UserSettingsDto();

        userSettingsDto.setUserId( entityUserId( entity ) );
        userSettingsDto.setId( entity.getId() );
        Map<String, Object> map = entity.getSettings();
        if ( map != null ) {
            userSettingsDto.setSettings( new LinkedHashMap<String, Object>( map ) );
        }
        userSettingsDto.setCreatedAt( entity.getCreatedAt() );
        userSettingsDto.setUpdatedAt( entity.getUpdatedAt() );

        return userSettingsDto;
    }

    @Override
    public UserSettings toEntity(UserSettingsDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserSettings.UserSettingsBuilder userSettings = UserSettings.builder();

        userSettings.id( dto.getId() );
        Map<String, Object> map = dto.getSettings();
        if ( map != null ) {
            userSettings.settings( new LinkedHashMap<String, Object>( map ) );
        }
        userSettings.createdAt( dto.getCreatedAt() );
        userSettings.updatedAt( dto.getUpdatedAt() );

        return userSettings.build();
    }

    private UUID entityUserId(UserSettings userSettings) {
        if ( userSettings == null ) {
            return null;
        }
        User user = userSettings.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
