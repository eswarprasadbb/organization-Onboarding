package com.example.organizationservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rate_limit_rules", uniqueConstraints = @UniqueConstraint(name = "uq_rate_limit_scope", columnNames = {"scope_type", "scope_id"}))
public class RateLimitRule {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    // SCOPE
    @Column(name = "scope_type", nullable = false, length = 20) // e.g. GLOBAL, ORGANIZATION, USER
    private String scopeType;

    @Column(name = "scope_id") // organization_id or user_id depending on scopeType
    private UUID scopeId;

    @Column(name = "window_seconds", nullable = false)
    private Integer windowSeconds; // rolling window size

    @Column(name = "max_requests", nullable = false)
    private Integer maxRequests;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
