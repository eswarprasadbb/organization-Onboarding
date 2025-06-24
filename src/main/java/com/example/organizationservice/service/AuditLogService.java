package com.example.organizationservice.service;

import com.example.organizationservice.dto.AuditLogDto;
import com.example.organizationservice.mapper.AuditLogMapper;
import com.example.organizationservice.model.AuditAction;
import com.example.organizationservice.model.AuditLog;
import com.example.organizationservice.model.User;
import com.example.organizationservice.repository.AuditLogRepository;
import com.example.organizationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository repository;
    private final UserRepository userRepository;
    private final AuditLogMapper mapper;

    @Transactional
    public void record(UUID actorUserId, String entity, UUID entityId, AuditAction action, String details, String ip) {
        AuditLog log = AuditLog.builder()
                .entity(entity)
                .entityId(entityId)
                .action(action)
                .details(details)
                .ipAddress(ip)
                .build();
        if (actorUserId != null) {
            User user = userRepository.findById(actorUserId).orElse(null);
            log.setActorUser(user);
        }
        repository.save(log);
    }

    public Page<AuditLogDto> search(Specification<AuditLog> spec, Pageable pageable) {
        return repository.findAll(spec, pageable).map(mapper::toDto);
    }

    public AuditLogDto get(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow();
    }
}
