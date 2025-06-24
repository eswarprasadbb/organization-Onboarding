package com.example.organizationservice.repository;

import com.example.organizationservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    List<Role> findByOrganization_Id(UUID organizationId);
    boolean existsByOrganization_IdAndName(UUID organizationId, String name);
}
