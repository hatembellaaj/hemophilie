package org.adhes.hemophilie.service.mapper;

import static org.adhes.hemophilie.domain.HemarthroseAsserts.*;
import static org.adhes.hemophilie.domain.HemarthroseTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HemarthroseMapperTest {

    private HemarthroseMapper hemarthroseMapper;

    @BeforeEach
    void setUp() {
        hemarthroseMapper = new HemarthroseMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHemarthroseSample1();
        var actual = hemarthroseMapper.toEntity(hemarthroseMapper.toDto(expected));
        assertHemarthroseAllPropertiesEquals(expected, actual);
    }
}
