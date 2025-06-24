package com.example.organizationservice.service;

import com.example.organizationservice.dto.OutboxEventDto;
import com.example.organizationservice.mapper.OutboxEventMapper;
import com.example.organizationservice.model.OutboxEvent;
import com.example.organizationservice.model.OutboxStatus;
import com.example.organizationservice.repository.OutboxEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxEventService {

    private final OutboxEventRepository repository;
    private final OutboxEventMapper mapper;

    @Transactional
    public OutboxEventDto saveEvent(String aggregateType, UUID aggregateId, String eventType, String payload) {
        OutboxEvent event = OutboxEvent.builder()
                .aggregateType(aggregateType)
                .aggregateId(aggregateId)
                .eventType(eventType)
                .payload(payload)
                .status(OutboxStatus.PENDING)
                .build();
        return mapper.toDto(repository.save(event));
    }

    public List<OutboxEventDto> list(OutboxStatus status) {
        return repository.findTop100ByStatusOrderByCreatedAt(status).stream().map(mapper::toDto).toList();
    }

    @Scheduled(fixedDelayString = "${outbox.publisher.delay-ms:5000}")
    @Transactional
    public void publishPending() {
        List<OutboxEvent> pending = repository.findTop100ByStatusOrderByCreatedAt(OutboxStatus.PENDING);
        for (OutboxEvent e : pending) {
            try {
                // TODO publish to message broker
                log.info("Publishing outbox event {}", e.getId());
                e.setStatus(OutboxStatus.SENT);
                e.setSentAt(Instant.now());
            } catch (Exception ex) {
                log.error("Failed to publish outbox event {}", e.getId(), ex);
                e.setStatus(OutboxStatus.FAILED);
            }
        }
    }
}
