package com.example.organizationservice.repository;

import com.example.organizationservice.model.OrganizationSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationSettingsRepository extends JpaRepository<OrganizationSettings, UUID> {
    Optional<OrganizationSettings> findByOrganizationId(UUID organizationId);
}
