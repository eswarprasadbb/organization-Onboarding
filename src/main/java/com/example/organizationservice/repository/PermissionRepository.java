package com.example.organizationservice.repository;

import com.example.organizationservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    boolean existsByName(String name);
}
