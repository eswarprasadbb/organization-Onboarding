package com.example.organizationservice.repository;

import com.example.organizationservice.model.OrganizationMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrganizationMemberRepository extends JpaRepository<OrganizationMember, UUID> {
    List<OrganizationMember> findByOrganizationId(UUID organizationId);
    List<OrganizationMember> findByUserId(UUID userId);
    boolean existsByOrganizationIdAndUserId(UUID organizationId, UUID userId);
}
