package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationMemberDto;
import com.example.organizationservice.model.OrganizationMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMemberMapper {
    OrganizationMemberMapper INSTANCE = Mappers.getMapper(OrganizationMemberMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "invitedBy", source = "invitedBy.id")
    OrganizationMemberDto toDto(OrganizationMember entity);

    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "invitedBy", ignore = true)
    OrganizationMember toEntity(OrganizationMemberDto dto);
}
