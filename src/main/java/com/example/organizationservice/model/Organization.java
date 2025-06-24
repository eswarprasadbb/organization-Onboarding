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
@Table(name = "organizations", indexes = {
        @Index(name = "idx_org_slug", columnList = "slug"),
        @Index(name = "idx_org_status", columnList = "status"),
        @Index(name = "idx_org_onboarding", columnList = "onboardingStatus")
})
public class Organization {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private String name;
    private String legalName;
    private String displayName;
    @Column(nullable = false, unique = true, length = 100)
    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BusinessType businessType;
    private String industry;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanySize companySize;
    private String annualRevenueRange;

    private String registrationNumber, taxId, vatNumber, incorporationCountry;
    private Instant incorporationDate;

    private String websiteUrl, supportEmail, billingEmail, phone;
    @Column(nullable = false, length = 3)
    private String primaryCurrency = "USD";
    @Column(nullable = false)
    private String timezone = "UTC";
    private String dateFormat = "YYYY-MM-DD", numberFormat = "US";

    private Integer defaultPaymentTerms = 30;
    @Enumerated(EnumType.STRING)
    private BillingCycle billingCycle = BillingCycle.MONTHLY;
    private String invoicePrefix = "INV";
    private Integer invoiceCounterStart = 1;

    @Enumerated(EnumType.STRING)
    private OnboardingStatus onboardingStatus = OnboardingStatus.PENDING;
    private Integer onboardingStep = 1;
    @Enumerated(EnumType.STRING)
    private SubscriptionTier subscriptionTier = SubscriptionTier.STARTER;

    private Boolean gdprCompliant = false, encryptionRequired = true;
    private String dataResidencyPreference;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    private UUID createdBy;

    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.ACTIVE;

    public enum BusinessType { B2B, B2C, B2B2C, MARKETPLACE }
    public enum CompanySize { STARTUP, SMB, MID_MARKET, ENTERPRISE }
    public enum BillingCycle { MONTHLY, QUARTERLY, ANNUALLY, CUSTOM }
    public enum OnboardingStatus { PENDING, IN_PROGRESS, COMPLETED, SUSPENDED }
    public enum SubscriptionTier { STARTER, PROFESSIONAL, ENTERPRISE, CUSTOM }
    public enum EntityStatus { ACTIVE, INACTIVE, SUSPENDED, DELETED }
}
