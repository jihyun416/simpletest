package com.jessy.simpletest.map;

import com.jessy.simpletest.domain.Order;
import com.jessy.simpletest.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    @Mapping(source = "customer.name.firstName", target="customerFirstName")
    @Mapping(source = "customer.name.lastName", target="customerLastName")
    @Mapping(source = "billing.city", target="billingCity")
    @Mapping(source = "billing.street", target="billingStreet")
    OrderDTO toDTO(Order order);
}
