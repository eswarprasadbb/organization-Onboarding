package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OutboxEventDto;
import com.example.organizationservice.model.OutboxEvent;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OutboxEventMapperImpl implements OutboxEventMapper {

    @Override
    public OutboxEventDto toDto(OutboxEvent entity) {
        if ( entity == null ) {
            return null;
        }

        OutboxEventDto outboxEventDto = new OutboxEventDto();

        outboxEventDto.setId( entity.getId() );
        outboxEventDto.setAggregateType( entity.getAggregateType() );
        outboxEventDto.setAggregateId( entity.getAggregateId() );
        outboxEventDto.setEventType( entity.getEventType() );
        outboxEventDto.setPayload( entity.getPayload() );
        outboxEventDto.setStatus( entity.getStatus() );
        outboxEventDto.setCreatedAt( entity.getCreatedAt() );
        outboxEventDto.setSentAt( entity.getSentAt() );

        return outboxEventDto;
    }

    @Override
    public OutboxEvent toEntity(OutboxEventDto dto) {
        if ( dto == null ) {
            return null;
        }

        OutboxEvent.OutboxEventBuilder outboxEvent = OutboxEvent.builder();

        outboxEvent.id( dto.getId() );
        outboxEvent.aggregateType( dto.getAggregateType() );
        outboxEvent.aggregateId( dto.getAggregateId() );
        outboxEvent.eventType( dto.getEventType() );
        outboxEvent.payload( dto.getPayload() );
        outboxEvent.status( dto.getStatus() );
        outboxEvent.createdAt( dto.getCreatedAt() );
        outboxEvent.sentAt( dto.getSentAt() );

        return outboxEvent.build();
    }
}
