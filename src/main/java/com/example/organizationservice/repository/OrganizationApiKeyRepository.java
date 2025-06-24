package com.example.organizationservice.repository;

import com.example.organizationservice.model.OrganizationApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationApiKeyRepository extends JpaRepository<OrganizationApiKey, UUID> {
    List<OrganizationApiKey> findByOrganizationId(UUID organizationId);

    Optional<OrganizationApiKey> findByKeyPrefix(String keyPrefix);
}
