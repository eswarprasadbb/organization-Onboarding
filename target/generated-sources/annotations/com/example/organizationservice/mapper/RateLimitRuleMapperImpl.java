package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.RateLimitRuleDto;
import com.example.organizationservice.model.RateLimitRule;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class RateLimitRuleMapperImpl implements RateLimitRuleMapper {

    @Override
    public RateLimitRuleDto toDto(RateLimitRule entity) {
        if ( entity == null ) {
            return null;
        }

        RateLimitRuleDto rateLimitRuleDto = new RateLimitRuleDto();

        rateLimitRuleDto.setId( entity.getId() );
        rateLimitRuleDto.setScopeType( entity.getScopeType() );
        rateLimitRuleDto.setScopeId( entity.getScopeId() );
        rateLimitRuleDto.setWindowSeconds( entity.getWindowSeconds() );
        rateLimitRuleDto.setMaxRequests( entity.getMaxRequests() );
        rateLimitRuleDto.setCreatedAt( entity.getCreatedAt() );
        rateLimitRuleDto.setUpdatedAt( entity.getUpdatedAt() );

        return rateLimitRuleDto;
    }

    @Override
    public RateLimitRule toEntity(RateLimitRuleDto dto) {
        if ( dto == null ) {
            return null;
        }

        RateLimitRule.RateLimitRuleBuilder rateLimitRule = RateLimitRule.builder();

        rateLimitRule.id( dto.getId() );
        rateLimitRule.scopeType( dto.getScopeType() );
        rateLimitRule.scopeId( dto.getScopeId() );
        rateLimitRule.windowSeconds( dto.getWindowSeconds() );
        rateLimitRule.maxRequests( dto.getMaxRequests() );
        rateLimitRule.createdAt( dto.getCreatedAt() );
        rateLimitRule.updatedAt( dto.getUpdatedAt() );

        return rateLimitRule.build();
    }
}
