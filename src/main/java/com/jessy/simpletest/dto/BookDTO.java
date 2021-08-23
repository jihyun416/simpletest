package com.jessy.simpletest.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookDTO {
    String title;
    String isbn;
    AuthorDTO writer;
    List<ContentDTO> contents;
}
