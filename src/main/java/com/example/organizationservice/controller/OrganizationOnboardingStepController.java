package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationOnboardingStepDto;
import com.example.organizationservice.model.OrganizationOnboardingStep;
import com.example.organizationservice.service.OrganizationOnboardingStepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/organizations/{organizationId}/onboarding-steps")
@RequiredArgsConstructor
@Tag(name = "Organization Onboarding", description = "Manage organization onboarding progress")
public class OrganizationOnboardingStepController {

    private final OrganizationOnboardingStepService onboardingStepService;

    @GetMapping
    @Operation(summary = "List all onboarding steps for an organization")
    public ResponseEntity<List<OrganizationOnboardingStepDto>> listSteps(
                        @Parameter(name = "organizationId", description = "ID of the organization", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable(name = "organizationId") UUID organizationId) {
        List<OrganizationOnboardingStep> steps = onboardingStepService.listSteps(organizationId);
        List<OrganizationOnboardingStepDto> dtos = steps.stream()
                .map(step -> {
                    OrganizationOnboardingStepDto dto = new OrganizationOnboardingStepDto();
                    dto.setId(step.getId());
                    dto.setOrganizationId(organizationId);
                    dto.setStep(step.getStep());
                    dto.setStatus(step.getStatus());
                    dto.setCompletedAt(step.getCompletedAt());
                    dto.setCreatedAt(step.getCreatedAt());
                    dto.setUpdatedAt(step.getUpdatedAt());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{step}/complete")
    @Operation(summary = "Mark an onboarding step as complete")
    public ResponseEntity<OrganizationOnboardingStepDto> completeStep(
                        @Parameter(name = "organizationId", description = "ID of the organization", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable(name = "organizationId") UUID organizationId,
            @Parameter(description = "Step to mark as complete", required = true, schema = @Schema(implementation = OrganizationOnboardingStep.Step.class))
            @PathVariable("step") OrganizationOnboardingStep.Step step) {
        return ResponseEntity.ok(onboardingStepService.completeStep(organizationId, step));
    }

    @PostMapping("/initialize")
    @Operation(summary = "Initialize onboarding steps for a new organization")
    public ResponseEntity<Void> initializeOnboarding(
                        @Parameter(name = "organizationId", description = "ID of the organization", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable(name = "organizationId") UUID organizationId) {
        onboardingStepService.initializeOnboarding(organizationId);
        return ResponseEntity.ok().build();
    }
}
