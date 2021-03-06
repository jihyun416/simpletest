package com.jessy.simpletest.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLooseDTO {
    String orderNo;
    String firstName;
    String lastName;
    String city;
    String street;
}
