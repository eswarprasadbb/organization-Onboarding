package com.example.organizationservice.dto;

import com.example.organizationservice.model.OrganizationAddress.AddressType;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class OrganizationAddressDto {
    private UUID id;
    private UUID organizationId;
    private AddressType type;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private java.math.BigDecimal latitude;
    private java.math.BigDecimal longitude;
    private Boolean active;
    private Instant createdAt;
    private Instant updatedAt;
}
