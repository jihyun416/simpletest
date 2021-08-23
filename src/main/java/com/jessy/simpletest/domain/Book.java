package com.jessy.simpletest.domain;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Book {
    String title;
    String isbn;
    Author author;
    List<Content> contents;
}
