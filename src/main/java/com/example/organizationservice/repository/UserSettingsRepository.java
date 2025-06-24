package com.example.organizationservice.repository;

import com.example.organizationservice.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserSettingsRepository extends JpaRepository<UserSettings, UUID> {
    Optional<UserSettings> findByUser_Id(UUID userId);
    boolean existsByUser_Id(UUID userId);
}
