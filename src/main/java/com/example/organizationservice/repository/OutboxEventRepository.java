package com.example.organizationservice.repository;

import com.example.organizationservice.model.OutboxEvent;
import com.example.organizationservice.model.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {
    List<OutboxEvent> findTop100ByStatusOrderByCreatedAt(OutboxStatus status);
}
