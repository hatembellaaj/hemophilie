package org.adhes.hemophilie.service.mapper;

import static org.adhes.hemophilie.domain.HematomePsoasAsserts.*;
import static org.adhes.hemophilie.domain.HematomePsoasTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HematomePsoasMapperTest {

    private HematomePsoasMapper hematomePsoasMapper;

    @BeforeEach
    void setUp() {
        hematomePsoasMapper = new HematomePsoasMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHematomePsoasSample1();
        var actual = hematomePsoasMapper.toEntity(hematomePsoasMapper.toDto(expected));
        assertHematomePsoasAllPropertiesEquals(expected, actual);
    }
}
