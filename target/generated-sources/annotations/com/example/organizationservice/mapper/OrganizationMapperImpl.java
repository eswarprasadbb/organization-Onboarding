package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.model.Organization;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public OrganizationDto toDto(Organization entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationDto organizationDto = new OrganizationDto();

        organizationDto.setId( entity.getId() );
        organizationDto.setName( entity.getName() );
        organizationDto.setLegalName( entity.getLegalName() );
        organizationDto.setDisplayName( entity.getDisplayName() );
        organizationDto.setSlug( entity.getSlug() );
        organizationDto.setBusinessType( entity.getBusinessType() );
        organizationDto.setIndustry( entity.getIndustry() );
        organizationDto.setCompanySize( entity.getCompanySize() );
        organizationDto.setAnnualRevenueRange( entity.getAnnualRevenueRange() );
        organizationDto.setRegistrationNumber( entity.getRegistrationNumber() );
        organizationDto.setTaxId( entity.getTaxId() );
        organizationDto.setVatNumber( entity.getVatNumber() );
        organizationDto.setIncorporationCountry( entity.getIncorporationCountry() );
        organizationDto.setIncorporationDate( entity.getIncorporationDate() );
        organizationDto.setWebsiteUrl( entity.getWebsiteUrl() );
        organizationDto.setSupportEmail( entity.getSupportEmail() );
        organizationDto.setBillingEmail( entity.getBillingEmail() );
        organizationDto.setPhone( entity.getPhone() );
        organizationDto.setPrimaryCurrency( entity.getPrimaryCurrency() );
        organizationDto.setTimezone( entity.getTimezone() );
        organizationDto.setDateFormat( entity.getDateFormat() );
        organizationDto.setNumberFormat( entity.getNumberFormat() );
        organizationDto.setDefaultPaymentTerms( entity.getDefaultPaymentTerms() );
        organizationDto.setBillingCycle( entity.getBillingCycle() );
        organizationDto.setInvoicePrefix( entity.getInvoicePrefix() );
        organizationDto.setInvoiceCounterStart( entity.getInvoiceCounterStart() );
        organizationDto.setOnboardingStatus( entity.getOnboardingStatus() );
        organizationDto.setOnboardingStep( entity.getOnboardingStep() );
        organizationDto.setSubscriptionTier( entity.getSubscriptionTier() );
        organizationDto.setGdprCompliant( entity.getGdprCompliant() );
        organizationDto.setEncryptionRequired( entity.getEncryptionRequired() );
        organizationDto.setDataResidencyPreference( entity.getDataResidencyPreference() );
        organizationDto.setCreatedAt( entity.getCreatedAt() );
        organizationDto.setUpdatedAt( entity.getUpdatedAt() );
        organizationDto.setCreatedBy( entity.getCreatedBy() );
        organizationDto.setStatus( entity.getStatus() );

        return organizationDto;
    }

    @Override
    public Organization toEntity(OrganizationDto dto) {
        if ( dto == null ) {
            return null;
        }

        Organization.OrganizationBuilder organization = Organization.builder();

        organization.name( dto.getName() );
        organization.legalName( dto.getLegalName() );
        organization.displayName( dto.getDisplayName() );
        organization.slug( dto.getSlug() );
        organization.businessType( dto.getBusinessType() );
        organization.industry( dto.getIndustry() );
        organization.companySize( dto.getCompanySize() );
        organization.annualRevenueRange( dto.getAnnualRevenueRange() );
        organization.registrationNumber( dto.getRegistrationNumber() );
        organization.taxId( dto.getTaxId() );
        organization.vatNumber( dto.getVatNumber() );
        organization.incorporationCountry( dto.getIncorporationCountry() );
        organization.incorporationDate( dto.getIncorporationDate() );
        organization.websiteUrl( dto.getWebsiteUrl() );
        organization.supportEmail( dto.getSupportEmail() );
        organization.billingEmail( dto.getBillingEmail() );
        organization.phone( dto.getPhone() );
        organization.primaryCurrency( dto.getPrimaryCurrency() );
        organization.timezone( dto.getTimezone() );
        organization.dateFormat( dto.getDateFormat() );
        organization.numberFormat( dto.getNumberFormat() );
        organization.defaultPaymentTerms( dto.getDefaultPaymentTerms() );
        organization.billingCycle( dto.getBillingCycle() );
        organization.invoicePrefix( dto.getInvoicePrefix() );
        organization.invoiceCounterStart( dto.getInvoiceCounterStart() );
        organization.onboardingStatus( dto.getOnboardingStatus() );
        organization.onboardingStep( dto.getOnboardingStep() );
        organization.subscriptionTier( dto.getSubscriptionTier() );
        organization.gdprCompliant( dto.getGdprCompliant() );
        organization.encryptionRequired( dto.getEncryptionRequired() );
        organization.dataResidencyPreference( dto.getDataResidencyPreference() );
        organization.createdAt( dto.getCreatedAt() );
        organization.updatedAt( dto.getUpdatedAt() );
        organization.createdBy( dto.getCreatedBy() );
        organization.status( dto.getStatus() );

        return organization.build();
    }
}
