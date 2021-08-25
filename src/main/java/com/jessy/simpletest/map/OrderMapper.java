package com.jessy.simpletest.map;

import com.jessy.simpletest.domain.Order;
import com.jessy.simpletest.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "dummyField", ignore = true)
    @Mapping(source = "customer.name.firstName", target="customerFirstName")
    @Mapping(source = "customer.name.lastName", target="customerLastName")
    @Mapping(source = "billing.city", target="billingCity")
    @Mapping(source = "billing.street", target="billingStreet")
    OrderDTO toDTO(Order order);

    @Mapping(target="customer.name", expression="java(Name.builder().firstName(orderDTO.getCustomerFirstName()).lastName(orderDTO.getCustomerLastName()).build())")
    @Mapping(target="billing", expression="java(Address.builder().city(orderDTO.getBillingCity()).street(orderDTO.getBillingStreet()).build())")
    Order toEntity(OrderDTO orderDTO);
}
