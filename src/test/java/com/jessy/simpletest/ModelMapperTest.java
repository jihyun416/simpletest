package com.jessy.simpletest;

import com.jessy.simpletest.domain.*;
import com.jessy.simpletest.dto.AuthorDTO;
import com.jessy.simpletest.dto.BookDTO;
import com.jessy.simpletest.dto.OrderDTO;
import com.jessy.simpletest.dto.OrderLooseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ModelMapperTest {
    @DisplayName("ModelMapper STANDARD 테스트")
    @Test
    public void ModelMapper_STANDARD() {
        Order order = Order.builder()
                .orderNumber("ORD0001")
                .customer(Customer.builder()
                        .name(Name.builder().firstName("Jessy").lastName("Song").build())
                        .build())
                .billing(Address.builder().street("Gaebongro").city("Seoul").build())
                .build();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        assertEquals(order.getOrderNumber(), orderDTO.getOrderNumber());
        assertEquals(order.getCustomer().getName().getFirstName(), orderDTO.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), orderDTO.getCustomerLastName());
        assertEquals(order.getBilling().getStreet(), orderDTO.getBillingStreet());
        assertEquals(order.getBilling().getCity(), orderDTO.getBillingCity());
    }

    @DisplayName("ModelMapper LOOSE 테스트")
    @Test
    public void ModelMapper_LOOSE() {
        Order order = Order.builder()
                .orderNumber("ORD0001")
                .customer(Customer.builder()
                        .name(Name.builder().firstName("Jessy").lastName("Song").build())
                        .build())
                .billing(Address.builder().street("Gaebongro").city("Seoul").build())
                .build();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        OrderLooseDTO orderLooseDTO = modelMapper.map(order, OrderLooseDTO.class);

        assertEquals(order.getOrderNumber(), orderLooseDTO.getOrderNo());
        assertEquals(order.getCustomer().getName().getFirstName(), orderLooseDTO.getFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), orderLooseDTO.getLastName());
        assertEquals(order.getBilling().getCity(), orderLooseDTO.getCity());
        assertEquals(order.getBilling().getStreet(), orderLooseDTO.getStreet());
    }

    @DisplayName("ModelMapper STRICT 테스트")
    @Test
    public void ModelMapper_STRICT() {
        Order order = Order.builder()
                .orderNumber("ORD0001")
                .customer(Customer.builder()
                        .name(Name.builder().firstName("Jessy").lastName("Song").build())
                        .build())
                .billing(Address.builder().street("Gaebongro").city("Seoul").build())
                .build();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.createTypeMap(Order.class, OrderDTO.class)
                .addMapping(src->src.getCustomer().getName().getFirstName(), OrderDTO::setCustomerFirstName)
                .addMapping(src->src.getCustomer().getName().getLastName(), OrderDTO::setCustomerLastName)
                .addMappings(mapper -> {
                    mapper.map(src->src.getBilling().getCity(), OrderDTO::setBillingCity);
                    mapper.map(src->src.getBilling().getStreet(), OrderDTO::setBillingStreet);
                })
        ;
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);

        assertEquals(order.getOrderNumber(), orderDTO.getOrderNumber());
        assertEquals(order.getCustomer().getName().getFirstName(), orderDTO.getCustomerFirstName());
        assertEquals(order.getCustomer().getName().getLastName(), orderDTO.getCustomerLastName());
        assertEquals(order.getBilling().getStreet(), orderDTO.getBillingStreet());
        assertEquals(order.getBilling().getCity(), orderDTO.getBillingCity());
    }

    @DisplayName("ModelMapper NULL 복사 테스트")
    @Test
    public void ModelMapper_Strict_copy_null() {
        OrderDTO origin = OrderDTO.builder()
                .orderNumber("ORD0001")
                .customerFirstName("Jessy")
                .customerLastName(null)
                .billingCity(null)
                .billingStreet(null)
                .build();
        OrderDTO destination = OrderDTO.builder()
                .orderNumber("ORD0001")
                .customerFirstName("Jessssy")
                .customerLastName("Song")
                .billingCity("Seoul")
                .billingStreet("Teheran")
                .build();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(origin, destination);

        assertEquals(origin.getOrderNumber(), destination.getOrderNumber());
        assertEquals(origin.getCustomerFirstName(), destination.getCustomerFirstName());
        assertEquals(origin.getCustomerLastName(), destination.getCustomerLastName());
        assertEquals(origin.getBillingCity(), destination.getBillingCity());
        assertEquals(origin.getBillingStreet(), destination.getBillingStreet());
    }

    @DisplayName("ModelMapper Skip Null 테스트")
    @Test
    public void ModelMapper_Skip_Null() {
        OrderDTO origin = OrderDTO.builder()
                .orderNumber("ORD0001")
                .customerFirstName("Jessy")
                .customerLastName(null)
                .billingCity(null)
                .billingStreet(null)
                .build();
        OrderDTO destination = OrderDTO.builder()
                .orderNumber("ORD0001")
                .customerFirstName("Jessssy")
                .customerLastName("Song")
                .billingCity("Seoul")
                .billingStreet("Gaebong")
                .build();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(origin, destination);

        assertEquals(origin.getOrderNumber(), destination.getOrderNumber());
        assertEquals(origin.getCustomerFirstName(), destination.getCustomerFirstName());
        assertNotEquals(origin.getCustomerLastName(), destination.getCustomerLastName());
        assertNotEquals(origin.getBillingCity(), destination.getBillingCity());
        assertNotEquals(origin.getBillingStreet(), destination.getBillingStreet());
    }

    @DisplayName("ModelMapper Copy 테스트")
    @Test
    public void ModelMapper_Copy() {
        List<Content> contents = new ArrayList<>();
        contents.add(Content.builder().page(1).title("Intro").build());
        contents.add(Content.builder().page(2).title("How to use").build());
        Book book = Book.builder()
                .title("Model Mapper Guide")
                .isbn("123456789")
                .author(Author.builder().firstName("Jessy").lastName("Song").build())
                .contents(contents)
                .build();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.createTypeMap(Author.class, AuthorDTO.class)
                .addMapping(Author::getFirstName, AuthorDTO::setFirst)
                .addMapping(Author::getLastName, AuthorDTO::setLast);

        modelMapper.createTypeMap(Book.class, BookDTO.class)
                .addMapping(Book::getAuthor, BookDTO::setWriter);

        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);

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
