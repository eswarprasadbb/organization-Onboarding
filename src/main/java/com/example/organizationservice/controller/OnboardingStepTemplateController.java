package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OnboardingStepTemplateDto;
import com.example.organizationservice.service.OnboardingStepTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/onboarding-step-templates")
@RequiredArgsConstructor
@Tag(name = "Onboarding Step Templates")
public class OnboardingStepTemplateController {

    private final OnboardingStepTemplateService service;

    @GetMapping("/active")
    @Operation(summary = "List active templates in order")
    public List<OnboardingStepTemplateDto> listActive() {
        return service.listActive();
    }

    @GetMapping
    @Operation(summary = "List all templates")
    public List<OnboardingStepTemplateDto> listAll() {
        return service.listAll();
    }

    @PostMapping
    @Operation(summary = "Create or update a template")
    public OnboardingStepTemplateDto save(@RequestBody OnboardingStepTemplateDto dto) {
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a template")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }
}
