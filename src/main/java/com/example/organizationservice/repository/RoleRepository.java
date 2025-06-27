package com.example.organizationservice.repository;

import com.example.organizationservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    @org.springframework.data.jpa.repository.Query("select r from Role r left join fetch r.permissions where r.organization is null and r.name = :name")
    Optional<Role> findByOrganizationIsNullAndNameFetchPermissions(@org.springframework.data.repository.query.Param("name") String name);
    List<Role> findByOrganization_Id(UUID organizationId);
    boolean existsByOrganization_IdAndName(UUID orgId, String name);

    Optional<Role> findByOrganizationIsNullAndName(String name);
    java.util.Optional<Role> findByOrganization_IdAndName(UUID organizationId, String name);
}
