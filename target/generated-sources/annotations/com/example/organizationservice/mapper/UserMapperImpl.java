package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.UserDto;
import com.example.organizationservice.model.User;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        Map<String, Object> map = entity.getPreferences();
        if ( map != null ) {
            userDto.setNotificationPreferences( new LinkedHashMap<String, Object>( map ) );
        }
        userDto.setId( entity.getId() );
        userDto.setFirstName( entity.getFirstName() );
        userDto.setLastName( entity.getLastName() );
        userDto.setEmail( entity.getEmail() );
        userDto.setPhone( entity.getPhone() );
        userDto.setEmailVerified( entity.getEmailVerified() );
        userDto.setAvatarUrl( entity.getAvatarUrl() );
        userDto.setJobTitle( entity.getJobTitle() );
        userDto.setDepartment( entity.getDepartment() );
        userDto.setTimezone( entity.getTimezone() );
        userDto.setLanguage( entity.getLanguage() );
        userDto.setCreatedAt( entity.getCreatedAt() );
        userDto.setUpdatedAt( entity.getUpdatedAt() );
        userDto.setStatus( entity.getStatus() );

        return userDto;
    }

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        Map<String, Object> map = dto.getNotificationPreferences();
        if ( map != null ) {
            user.preferences( new LinkedHashMap<String, Object>( map ) );
        }
        user.id( dto.getId() );
        user.firstName( dto.getFirstName() );
        user.lastName( dto.getLastName() );
        user.email( dto.getEmail() );
        user.phone( dto.getPhone() );
        user.status( dto.getStatus() );
        user.emailVerified( dto.getEmailVerified() );
        user.avatarUrl( dto.getAvatarUrl() );
        user.jobTitle( dto.getJobTitle() );
        user.department( dto.getDepartment() );
        user.timezone( dto.getTimezone() );
        user.language( dto.getLanguage() );
        user.createdAt( dto.getCreatedAt() );
        user.updatedAt( dto.getUpdatedAt() );

        return user.build();
    }
}
