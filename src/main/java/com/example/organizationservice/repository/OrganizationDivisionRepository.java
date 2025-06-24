package com.example.organizationservice.repository;

import com.example.organizationservice.model.OrganizationDivision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrganizationDivisionRepository extends JpaRepository<OrganizationDivision, UUID> {
    List<OrganizationDivision> findByOrganization_Id(UUID organizationId);
}
