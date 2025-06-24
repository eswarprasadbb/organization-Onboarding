package com.example.organizationservice.dto;

import com.example.organizationservice.model.OrganizationOnboardingStep.Status;
import com.example.organizationservice.model.OrganizationOnboardingStep.Step;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class OrganizationOnboardingStepDto {
    private UUID id;
    private UUID organizationId;
    private Step step;
    private Status status;
    private Instant completedAt;
    private Instant createdAt;
    private Instant updatedAt;
}
