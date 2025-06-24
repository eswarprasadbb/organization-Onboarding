package com.example.organizationservice.dto;

import com.example.organizationservice.model.Organization;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class OrganizationDto {
    private UUID id;

    private String name;
    private String legalName;
    private String displayName;
    private String slug;

    private Organization.BusinessType businessType;
    private String industry;
    private Organization.CompanySize companySize;
    private String annualRevenueRange;

    private String registrationNumber;
    private String taxId;
    private String vatNumber;
    private String incorporationCountry;
    private Instant incorporationDate;

    private String websiteUrl;
    private String supportEmail;
    private String billingEmail;
    private String phone;

    private String primaryCurrency;
    private String timezone;
    private String dateFormat;
    private String numberFormat;

    private Integer defaultPaymentTerms;
    private Organization.BillingCycle billingCycle;
    private String invoicePrefix;
    private Integer invoiceCounterStart;

    private Organization.OnboardingStatus onboardingStatus;
    private Integer onboardingStep;
    private Organization.SubscriptionTier subscriptionTier;

    private Boolean gdprCompliant;
    private Boolean encryptionRequired;
    private String dataResidencyPreference;

    private Instant createdAt;
    private Instant updatedAt;
    private UUID createdBy;

    private Organization.EntityStatus status;
}
