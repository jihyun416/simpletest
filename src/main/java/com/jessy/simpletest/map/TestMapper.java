package com.jessy.simpletest.map;

import com.jessy.simpletest.domain.TestDomain;
import com.jessy.simpletest.dto.TestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestMapper {
    TestDTO toDTO(TestDomain test);
}
