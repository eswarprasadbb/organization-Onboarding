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
@Table(name = "organization_divisions", uniqueConstraints = @UniqueConstraint(columnNames = {"organization_id", "name"}),
        indexes = @Index(name = "idx_division_org", columnList = "organization_id"))
public class OrganizationDivision {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(nullable = false)
    private String name;
    private String description, email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DivisionStatus status = DivisionStatus.ACTIVE;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public enum DivisionStatus { ACTIVE, INACTIVE, SUSPENDED }
}
