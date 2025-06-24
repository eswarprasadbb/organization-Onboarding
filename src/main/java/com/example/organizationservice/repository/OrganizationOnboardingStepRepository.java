package com.example.organizationservice.repository;

import com.example.organizationservice.model.OrganizationOnboardingStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrganizationOnboardingStepRepository extends JpaRepository<OrganizationOnboardingStep, UUID> {
    List<OrganizationOnboardingStep> findByOrganizationId(UUID organizationId);
    boolean existsByOrganizationIdAndStep(UUID organizationId, OrganizationOnboardingStep.Step step);
    Optional<OrganizationOnboardingStep> findByOrganizationIdAndStep(UUID organizationId, OrganizationOnboardingStep.Step step);
}
