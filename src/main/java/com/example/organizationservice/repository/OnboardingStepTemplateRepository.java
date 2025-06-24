package com.example.organizationservice.repository;

import com.example.organizationservice.model.OnboardingStepTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OnboardingStepTemplateRepository extends JpaRepository<OnboardingStepTemplate, UUID> {
    List<OnboardingStepTemplate> findAllByActiveTrueOrderByStepOrder();
}
