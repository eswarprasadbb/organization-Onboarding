package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.CustomerDto;
import com.example.organizationservice.model.Customer;
import com.example.organizationservice.model.Organization;
import com.example.organizationservice.model.OrganizationDivision;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T11:37:10+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto toDto(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setOrganizationId( entityOrganizationId( entity ) );
        customerDto.setDivisionId( entityDivisionId( entity ) );
        customerDto.setId( entity.getId() );
        customerDto.setName( entity.getName() );
        customerDto.setEmail( entity.getEmail() );
        customerDto.setPhone( entity.getPhone() );
        customerDto.setStatus( entity.getStatus() );
        customerDto.setStartDate( entity.getStartDate() );
        Map<String, Object> map = entity.getMetadata();
        if ( map != null ) {
            customerDto.setMetadata( new LinkedHashMap<String, Object>( map ) );
        }

        return customerDto;
    }

    @Override
    public Customer toEntity(CustomerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( dto.getId() );
        customer.name( dto.getName() );
        customer.email( dto.getEmail() );
        customer.phone( dto.getPhone() );
        customer.status( dto.getStatus() );
        customer.startDate( dto.getStartDate() );
        Map<String, Object> map = dto.getMetadata();
        if ( map != null ) {
            customer.metadata( new LinkedHashMap<String, Object>( map ) );
        }

        return customer.build();
    }

    private UUID entityOrganizationId(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        Organization organization = customer.getOrganization();
        if ( organization == null ) {
            return null;
        }
        UUID id = organization.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityDivisionId(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        OrganizationDivision division = customer.getDivision();
        if ( division == null ) {
            return null;
        }
        UUID id = division.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
