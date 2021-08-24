package com.jessy.simpletest.map;

import com.jessy.simpletest.domain.Book;
import com.jessy.simpletest.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    @Mapping(source="author.firstName", target = "writer.first")
    @Mapping(source="author.lastName", target = "writer.last")
    BookDTO toDTO(Book book);
}
