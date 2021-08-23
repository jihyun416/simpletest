package com.jessy.simpletest.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    String city;
    String street;
}
