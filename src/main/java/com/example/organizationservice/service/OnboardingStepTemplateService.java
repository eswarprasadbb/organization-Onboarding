package com.example.organizationservice.service;

import com.example.organizationservice.dto.OnboardingStepTemplateDto;
import com.example.organizationservice.mapper.OnboardingStepTemplateMapper;
import com.example.organizationservice.model.OnboardingStepTemplate;
import com.example.organizationservice.repository.OnboardingStepTemplateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OnboardingStepTemplateService {

    private final OnboardingStepTemplateRepository repository;
    private final OnboardingStepTemplateMapper mapper;

    public List<OnboardingStepTemplateDto> listActive() {
        return repository.findAllByActiveTrueOrderByStepOrder()
                .stream().map(mapper::toDto).toList();
    }

    public List<OnboardingStepTemplateDto> listAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public OnboardingStepTemplateDto save(OnboardingStepTemplateDto dto) {
        OnboardingStepTemplate entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
