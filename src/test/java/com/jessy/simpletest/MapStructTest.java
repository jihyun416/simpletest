package com.jessy.simpletest;

import com.jessy.simpletest.domain.*;
import com.jessy.simpletest.dto.OrderDTO;
import com.jessy.simpletest.dto.TestDTO;
import com.jessy.simpletest.map.OrderMapper;
import com.jessy.simpletest.map.TestMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapStructTest {
    @Autowired
    OrderMapper orderMapper;


    @DisplayName("OrderMapper 테스트")
    @Test
    public void OrderMapper_TEST() {
        Order order = Order.builder()
                .orderNumber("ORD0001")
                .customer(Customer.builder()
                        .name(Name.builder().firstName("Jessy").lastName("Song").build())
                        .build())
                .billing(Address.builder().street("Gaebongro").city("Seoul").build())
                .build();

        OrderDTO orderDTO = orderMapper.toDTO(order);

        assertEquals(order.getOrderNumber(), orderDTO.getOrderNumber());
        assertEquals(order.getCustomer().getName().getFirstName(), orderDTO.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), orderDTO.getCustomerLastName());
        assertEquals(order.getBilling().getStreet(), orderDTO.getBillingStreet());
        assertEquals(order.getBilling().getCity(), orderDTO.getBillingCity());
    }
}
