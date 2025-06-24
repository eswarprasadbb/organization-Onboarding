package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.SystemSettingDto;
import com.example.organizationservice.model.SystemSetting;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class SystemSettingMapperImpl implements SystemSettingMapper {

    @Override
    public SystemSettingDto toDto(SystemSetting entity) {
        if ( entity == null ) {
            return null;
        }

        SystemSettingDto systemSettingDto = new SystemSettingDto();

        systemSettingDto.setId( entity.getId() );
        systemSettingDto.setKey( entity.getKey() );
        systemSettingDto.setValue( entity.getValue() );
        systemSettingDto.setCreatedAt( entity.getCreatedAt() );
        systemSettingDto.setUpdatedAt( entity.getUpdatedAt() );

        return systemSettingDto;
    }

    @Override
    public SystemSetting toEntity(SystemSettingDto dto) {
        if ( dto == null ) {
            return null;
        }

        SystemSetting.SystemSettingBuilder systemSetting = SystemSetting.builder();

        systemSetting.id( dto.getId() );
        systemSetting.key( dto.getKey() );
        systemSetting.value( dto.getValue() );
        systemSetting.createdAt( dto.getCreatedAt() );
        systemSetting.updatedAt( dto.getUpdatedAt() );

        return systemSetting.build();
    }
}
