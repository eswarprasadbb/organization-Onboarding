package com.example.organizationservice.dto;

import com.example.organizationservice.model.Customer;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;
import java.util.Map;

@Data
public class CustomerDto {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private Customer.CustomerStatus status;
    private UUID organizationId;
    private UUID divisionId;
    private Instant startDate;
    private Map<String, Object> metadata;
}
