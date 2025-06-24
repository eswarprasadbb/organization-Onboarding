package com.example.organizationservice.repository;

import com.example.organizationservice.model.OrganizationAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrganizationAddressRepository extends JpaRepository<OrganizationAddress, UUID> {
    List<OrganizationAddress> findByOrganizationId(UUID organizationId);
}
