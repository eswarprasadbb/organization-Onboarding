package com.example.organizationservice.repository;

import com.example.organizationservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    List<Customer> findByOrganization_Id(UUID organizationId);
    List<Customer> findByDivision_Id(UUID divisionId);
}
