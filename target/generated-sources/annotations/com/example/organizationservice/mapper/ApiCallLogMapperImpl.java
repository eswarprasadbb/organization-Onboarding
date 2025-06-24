package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.ApiCallLogDto;
import com.example.organizationservice.model.ApiCallLog;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ApiCallLogMapperImpl implements ApiCallLogMapper {

    @Override
    public ApiCallLogDto toDto(ApiCallLog entity) {
        if ( entity == null ) {
            return null;
        }

        ApiCallLogDto apiCallLogDto = new ApiCallLogDto();

        apiCallLogDto.setUserId( entityUserId( entity ) );
        apiCallLogDto.setOrganizationId( entityOrganizationId( entity ) );
        apiCallLogDto.setId( entity.getId() );
        apiCallLogDto.setMethod( entity.getMethod() );
        apiCallLogDto.setPath( entity.getPath() );
        apiCallLogDto.setStatusCode( entity.getStatusCode() );
        apiCallLogDto.setDurationMs( entity.getDurationMs() );
        apiCallLogDto.setIpAddress( entity.getIpAddress() );
        apiCallLogDto.setCreatedAt( entity.getCreatedAt() );

        return apiCallLogDto;
    }

    private UUID entityUserId(ApiCallLog apiCallLog) {
        if ( apiCallLog == null ) {
            return null;
        }
        User user = apiCallLog.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityOrganizationId(ApiCallLog apiCallLog) {
        if ( apiCallLog == null ) {
            return null;
        }
        Organization organization = apiCallLog.getOrganization();
        if ( organization == null ) {
            return null;
        }
        UUID id = organization.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
