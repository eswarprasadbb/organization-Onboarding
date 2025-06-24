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
@Table(name = "organization_addresses", indexes = {
        @Index(name = "idx_addr_org", columnList = "organization_id"),
        @Index(name = "idx_addr_type", columnList = "type")
})
public class OrganizationAddress {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AddressType type;

    @Column(nullable = false, length = 255)
    private String line1;

    private String line2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    @Column(precision = 10, scale = 6)
    private java.math.BigDecimal latitude;
    @Column(precision = 10, scale = 6)
    private java.math.BigDecimal longitude;

    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public enum AddressType { BILLING, HEADQUARTERS, SHIPPING, OTHER }
}
