package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationMemberDto;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationMember;
import com.example.organizationservice.model.User;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OrganizationMemberMapperImpl implements OrganizationMemberMapper {

    @Override
    public OrganizationMemberDto toDto(OrganizationMember entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationMemberDto organizationMemberDto = new OrganizationMemberDto();

        organizationMemberDto.setOrganizationId( entityOrganizationId( entity ) );
        organizationMemberDto.setUserId( entityUserId( entity ) );
        organizationMemberDto.setInvitedBy( entityInvitedById( entity ) );
        organizationMemberDto.setId( entity.getId() );
        organizationMemberDto.setRole( entity.getRole() );
        Map<String, Object> map = entity.getPermissions();
        if ( map != null ) {
            organizationMemberDto.setPermissions( new LinkedHashMap<String, Object>( map ) );
        }
        organizationMemberDto.setStatus( entity.getStatus() );
        organizationMemberDto.setInvitedAt( entity.getInvitedAt() );
        organizationMemberDto.setJoinedAt( entity.getJoinedAt() );
        organizationMemberDto.setCreatedAt( entity.getCreatedAt() );
        organizationMemberDto.setUpdatedAt( entity.getUpdatedAt() );

        return organizationMemberDto;
    }

    @Override
    public OrganizationMember toEntity(OrganizationMemberDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrganizationMember.OrganizationMemberBuilder organizationMember = OrganizationMember.builder();

        organizationMember.id( dto.getId() );
        organizationMember.role( dto.getRole() );
        Map<String, Object> map = dto.getPermissions();
        if ( map != null ) {
            organizationMember.permissions( new LinkedHashMap<String, Object>( map ) );
        }
        organizationMember.status( dto.getStatus() );
        organizationMember.invitedAt( dto.getInvitedAt() );
        organizationMember.joinedAt( dto.getJoinedAt() );
        organizationMember.createdAt( dto.getCreatedAt() );
        organizationMember.updatedAt( dto.getUpdatedAt() );

        return organizationMember.build();
    }

    private UUID entityOrganizationId(OrganizationMember organizationMember) {
        if ( organizationMember == null ) {
            return null;
        }
        Organization organization = organizationMember.getOrganization();
        if ( organization == null ) {
            return null;
        }
        UUID id = organization.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityUserId(OrganizationMember organizationMember) {
        if ( organizationMember == null ) {
            return null;
        }
        User user = organizationMember.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityInvitedById(OrganizationMember organizationMember) {
        if ( organizationMember == null ) {
            return null;
        }
        User invitedBy = organizationMember.getInvitedBy();
        if ( invitedBy == null ) {
            return null;
        }
        UUID id = invitedBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
