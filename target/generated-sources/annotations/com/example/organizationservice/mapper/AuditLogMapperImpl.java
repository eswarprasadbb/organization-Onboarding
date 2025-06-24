package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.AuditLogDto;
import com.example.organizationservice.model.AuditLog;
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
public class AuditLogMapperImpl implements AuditLogMapper {

    @Override
    public AuditLogDto toDto(AuditLog entity) {
        if ( entity == null ) {
            return null;
        }

        AuditLogDto auditLogDto = new AuditLogDto();

        auditLogDto.setActorUserId( entityActorUserId( entity ) );
        auditLogDto.setId( entity.getId() );
        auditLogDto.setEntity( entity.getEntity() );
        auditLogDto.setEntityId( entity.getEntityId() );
        auditLogDto.setAction( entity.getAction() );
        auditLogDto.setDetails( entity.getDetails() );
        auditLogDto.setIpAddress( entity.getIpAddress() );
        auditLogDto.setCreatedAt( entity.getCreatedAt() );

        return auditLogDto;
    }

    private UUID entityActorUserId(AuditLog auditLog) {
        if ( auditLog == null ) {
            return null;
        }
        User actorUser = auditLog.getActorUser();
        if ( actorUser == null ) {
            return null;
        }
        UUID id = actorUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
