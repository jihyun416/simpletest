package com.jessy.simpletest.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    String firstName;
    String lastName;
}
