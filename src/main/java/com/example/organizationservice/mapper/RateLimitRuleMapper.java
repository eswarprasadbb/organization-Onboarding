package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.RateLimitRuleDto;
import com.example.organizationservice.model.RateLimitRule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RateLimitRuleMapper {
    RateLimitRuleMapper INSTANCE = Mappers.getMapper(RateLimitRuleMapper.class);

    RateLimitRuleDto toDto(RateLimitRule entity);
    RateLimitRule toEntity(RateLimitRuleDto dto);
}
