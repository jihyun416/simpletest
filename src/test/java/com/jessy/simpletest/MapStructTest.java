package com.jessy.simpletest;

import com.jessy.simpletest.domain.*;
import com.jessy.simpletest.dto.BookDTO;
import com.jessy.simpletest.dto.OrderDTO;
import com.jessy.simpletest.map.BookMapper;
import com.jessy.simpletest.map.OrderMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapStructTest {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BookMapper bookMapper;


    @DisplayName("OrderMapper toDTO 테스트")
    @Test
    public void OrderMapper_toDTO_TEST() {
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

    @DisplayName("OrderMapper toEntity 테스트")
    @Test
    public void OrderMapper_toEntity_TEST() {
        OrderDTO orderDTO = OrderDTO.builder()
                .orderNumber("ORD0001")
                .customerFirstName("Jessy")
                .customerLastName("Song")
                .billingCity("Seoul")
                .billingStreet("Gaebongro")
                .build();

        Order order = orderMapper.toEntity(orderDTO);

        assertEquals(order.getOrderNumber(), orderDTO.getOrderNumber());
        assertEquals(order.getCustomer().getName().getFirstName(), orderDTO.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), orderDTO.getCustomerLastName());
        assertEquals(order.getBilling().getStreet(), orderDTO.getBillingStreet());
        assertEquals(order.getBilling().getCity(), orderDTO.getBillingCity());
    }

    @DisplayName("BookMapper 테스트")
    @Test
    public void BookMapper_TEST() {
        List<Content> contents = new ArrayList<>();
        contents.add(Content.builder().page(1).title("Intro").build());
        contents.add(Content.builder().page(2).title("How to use").build());
        Book book = Book.builder()
                .title("Model Mapper Guide")
                .isbn("123456789")
                .author(Author.builder().firstName("Jessy").lastName("Song").build())
                .contents(contents)
                .build();

        BookDTO bookDTO = bookMapper.toDTO(book);

        assertEquals(book.getTitle(), bookDTO.getTitle());
        assertEquals(book.getIsbn(), bookDTO.getIsbn());
        assertEquals(book.getAuthor().getFirstName(), bookDTO.getWriter().getFirst());
        assertEquals(book.getAuthor().getLastName(), bookDTO.getWriter().getLast());
        assertEquals(book.getContents().get(0).getPage(), bookDTO.getContents().get(0).getPage());
        assertEquals(book.getContents().get(0).getTitle(), bookDTO.getContents().get(0).getTitle());
        assertEquals(book.getContents().get(1).getPage(), bookDTO.getContents().get(1).getPage());
        assertEquals(book.getContents().get(1).getTitle(), bookDTO.getContents().get(1).getTitle());
    }
}
