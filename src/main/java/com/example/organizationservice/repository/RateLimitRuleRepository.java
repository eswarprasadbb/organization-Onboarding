package com.example.organizationservice.repository;

import com.example.organizationservice.model.RateLimitRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RateLimitRuleRepository extends JpaRepository<RateLimitRule, UUID> {
    List<RateLimitRule> findByScopeTypeAndScopeId(String scopeType, UUID scopeId);
    List<RateLimitRule> findByScopeType(String scopeType);
}
