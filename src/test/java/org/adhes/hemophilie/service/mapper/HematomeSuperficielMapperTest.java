package org.adhes.hemophilie.service.mapper;

import static org.adhes.hemophilie.domain.HematomeSuperficielAsserts.*;
import static org.adhes.hemophilie.domain.HematomeSuperficielTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HematomeSuperficielMapperTest {

    private HematomeSuperficielMapper hematomeSuperficielMapper;

    @BeforeEach
    void setUp() {
        hematomeSuperficielMapper = new HematomeSuperficielMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHematomeSuperficielSample1();
        var actual = hematomeSuperficielMapper.toEntity(hematomeSuperficielMapper.toDto(expected));
        assertHematomeSuperficielAllPropertiesEquals(expected, actual);
    }
}
