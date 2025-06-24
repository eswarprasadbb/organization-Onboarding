package com.example.organizationservice.service;

import com.example.organizationservice.dto.RateLimitRuleDto;
import com.example.organizationservice.mapper.RateLimitRuleMapper;
import com.example.organizationservice.model.RateLimitRule;
import com.example.organizationservice.repository.RateLimitRuleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RateLimitRuleService {

    private final RateLimitRuleRepository repository;
    private final RateLimitRuleMapper mapper;

    public List<RateLimitRuleDto> list() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public RateLimitRuleDto get(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }

    @Transactional
    public RateLimitRuleDto create(RateLimitRuleDto dto) {
        RateLimitRule entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public RateLimitRuleDto update(UUID id, RateLimitRuleDto dto) {
        RateLimitRule rule = repository.findById(id).orElseThrow();
        rule.setScopeType(dto.getScopeType());
        rule.setScopeId(dto.getScopeId());
        rule.setWindowSeconds(dto.getWindowSeconds());
        rule.setMaxRequests(dto.getMaxRequests());
        return mapper.toDto(rule);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
