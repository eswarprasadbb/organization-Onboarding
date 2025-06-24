package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationOnboardingStepDto;
import com.example.organizationservice.exception.OrganizationNotFoundException;
import com.example.organizationservice.mapper.OrganizationOnboardingStepMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationOnboardingStep;
import com.example.organizationservice.repository.OrganizationOnboardingStepRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationOnboardingStepService {

    private final OrganizationOnboardingStepRepository stepRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationOnboardingStepMapper mapper;

    public List<OrganizationOnboardingStep> listSteps(UUID organizationId) {
        return stepRepository.findByOrganizationId(organizationId);
    }

    @Transactional
    public OrganizationOnboardingStepDto completeStep(UUID organizationId, OrganizationOnboardingStep.Step step) {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));

        OrganizationOnboardingStep onboardingStep = stepRepository.findByOrganizationIdAndStep(organizationId, step)
                .orElseGet(() -> OrganizationOnboardingStep.builder()
                        .organization(organization)
                        .step(step)
                        .build());

        onboardingStep.setStatus(OrganizationOnboardingStep.Status.COMPLETE);
        onboardingStep.setCompletedAt(Instant.now());
        
        return mapper.toDto(stepRepository.save(onboardingStep));
    }
    
    public Optional<OrganizationOnboardingStep> findByOrganizationIdAndStep(UUID organizationId, OrganizationOnboardingStep.Step step) {
        return stepRepository.findByOrganizationIdAndStep(organizationId, step);
    }

    @Transactional
    public void initializeOnboarding(UUID organizationId) {
        if (!organizationRepository.existsById(organizationId)) {
            throw new OrganizationNotFoundException(organizationId);
        }

        // Only create if not already existing
        for (OrganizationOnboardingStep.Step step : OrganizationOnboardingStep.Step.values()) {
            if (!stepRepository.existsByOrganizationIdAndStep(organizationId, step)) {
                stepRepository.save(OrganizationOnboardingStep.builder()
                        .organization(Organization.builder().id(organizationId).build())
                        .step(step)
                        .status(OrganizationOnboardingStep.Status.PENDING)
                        .build());
            }
        }
    }
}
