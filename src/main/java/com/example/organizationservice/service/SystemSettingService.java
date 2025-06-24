package com.example.organizationservice.service;

import com.example.organizationservice.dto.SystemSettingDto;
import com.example.organizationservice.mapper.SystemSettingMapper;
import com.example.organizationservice.model.SystemSetting;
import com.example.organizationservice.repository.SystemSettingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SystemSettingService {

    public static final String CACHE_NAME = "systemSettings";

    private final SystemSettingRepository repository;
    private final SystemSettingMapper mapper;

    @Cacheable(value = CACHE_NAME, key = "#key")
    public Object getValue(String key) {
        return repository.findByKey(key).map(SystemSetting::getValue).orElse(null);
    }

    public List<SystemSettingDto> list() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, key = "#dto.key")
    public SystemSettingDto upsert(SystemSettingDto dto) {
        SystemSetting setting = repository.findByKey(dto.getKey()).orElse(SystemSetting.builder().key(dto.getKey()).build());
        setting.setValue(dto.getValue());
        return mapper.toDto(repository.save(setting));
    }

    @Transactional
    @CacheEvict(value = CACHE_NAME, key = "#id")
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    // clear whole cache daily
    @Scheduled(cron = "0 0 0 * * *")
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public void evictCache() {
        log.info("Clearing systemSettings cache");
    }
}
