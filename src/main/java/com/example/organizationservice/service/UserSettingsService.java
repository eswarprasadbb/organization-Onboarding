package com.example.organizationservice.service;

import com.example.organizationservice.dto.UserSettingsDto;
import com.example.organizationservice.mapper.UserSettingsMapper;
import com.example.organizationservice.model.User;
import com.example.organizationservice.model.UserSettings;
import com.example.organizationservice.repository.UserRepository;
import com.example.organizationservice.repository.UserSettingsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserSettingsService {

    private final UserSettingsRepository repository;
    private final UserRepository userRepository;
    private final UserSettingsMapper mapper;

    public UserSettingsDto get(UUID userId) {
        return repository.findByUser_Id(userId).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    public UserSettingsDto upsert(UUID userId, UserSettingsDto dto) {
        UserSettings settings = repository.findByUser_Id(userId).orElse(null);
        if (settings == null) {
            User user = userRepository.findById(userId).orElseThrow();
            settings = new UserSettings();
            settings.setUser(user);
        }
        settings.setSettings(dto.getSettings());
        return mapper.toDto(repository.save(settings));
    }
}
