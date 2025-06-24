package com.example.organizationservice.service;

import com.example.organizationservice.dto.ApiCallLogDto;
import com.example.organizationservice.mapper.ApiCallLogMapper;
import com.example.organizationservice.model.ApiCallLog;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.User;
import com.example.organizationservice.repository.ApiCallLogRepository;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiCallLogService {

    private final ApiCallLogRepository repository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final ApiCallLogMapper mapper;

    @Transactional
    public void record(UUID userId, UUID orgId, String method, String path, int status, long durationMs, String ip) {
        ApiCallLog log = new ApiCallLog();
        if (userId != null) {
            User user = userRepository.findById(userId).orElse(null);
            log.setUser(user);
        }
        if (orgId != null) {
            Organization org = organizationRepository.findById(orgId).orElse(null);
            log.setOrganization(org);
        }
        log.setMethod(method);
        log.setPath(path);
        log.setStatusCode(status);
        log.setDurationMs(durationMs);
        log.setIpAddress(ip);
        repository.save(log);
    }

    public Page<ApiCallLogDto> search(Specification<ApiCallLog> spec, Pageable pageable) {
        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

    public ApiCallLogDto get(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }
}
