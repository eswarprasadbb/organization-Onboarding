package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.CustomerDto;
import com.example.organizationservice.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "divisionId", source = "division.id")
    CustomerDto toDto(Customer entity);

    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "division", ignore = true)
    Customer toEntity(CustomerDto dto);
}
