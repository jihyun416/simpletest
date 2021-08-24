package com.jessy.simpletest;

import com.jessy.simpletest.domain.TestDomain;
import com.jessy.simpletest.dto.TestDTO;
import com.jessy.simpletest.map.TestMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModelMapperAndMapStructTest {
    @Autowired
    TestMapper testMapper;
    @Autowired
    ModelMapper modelMapper;


    @DisplayName("ModelMapper 테스트")
    @Test
    public void ModelMapper() {
        TestDomain testDomain = TestDomain.builder().a("a").b("b").c("c").d("d").e("e").f("f").g("g").h(1).i(2).j(3).k(4).build();

        for(int i=0; i<1000000; i++) {
            TestDTO testDTO = modelMapper.map(testDomain, TestDTO.class);
        }
    }

    @DisplayName("MapStruct 테스트")
    @Test
    public void MapStruct() {
        TestDomain testDomain = TestDomain.builder().a("a").b("b").c("c").d("d").e("e").f("f").g("g").h(1).i(2).j(3).k(4).build();

        for(int i=0; i<1000000; i++) {
            TestDTO testDTO = testMapper.toDTO(testDomain);
        }
    }
}
