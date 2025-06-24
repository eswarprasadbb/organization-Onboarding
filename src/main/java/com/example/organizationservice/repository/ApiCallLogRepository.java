package com.example.organizationservice.repository;

import com.example.organizationservice.model.ApiCallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ApiCallLogRepository extends JpaRepository<ApiCallLog, UUID>, JpaSpecificationExecutor<ApiCallLog> {
}
