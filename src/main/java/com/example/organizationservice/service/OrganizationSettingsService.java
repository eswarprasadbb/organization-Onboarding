package com.example.organizationservice.service;

import com.example.organizationservice.dto.OrganizationSettingsDto;
import com.example.organizationservice.mapper.OrganizationSettingsMapper;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationSettings;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.repository.OrganizationSettingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationSettingsService {

    private final OrganizationSettingsRepository settingsRepository;
    private final OrganizationRepository organizationRepository;
    private final OrganizationSettingsMapper mapper;

    public OrganizationSettingsDto get(UUID orgId) {
        return settingsRepository.findByOrganizationId(orgId).map(mapper::toDto).orElse(null);
    }

    @Transactional
    public OrganizationSettingsDto upsert(UUID orgId, OrganizationSettingsDto dto) {
        OrganizationSettings entity = settingsRepository.findByOrganizationId(orgId).orElse(null);
        if (entity == null) {
            Organization org = organizationRepository.findById(orgId).orElseThrow();
            entity = OrganizationSettings.builder()
                    .organization(org)
                    .settings(dto.getSettings())
                    .build();
        } else {
            entity.setSettings(dto.getSettings());
        }
        return mapper.toDto(settingsRepository.save(entity));
    }
}
