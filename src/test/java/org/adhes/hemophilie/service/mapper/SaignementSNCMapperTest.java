package org.adhes.hemophilie.service.mapper;

import static org.adhes.hemophilie.domain.SaignementSNCAsserts.*;
import static org.adhes.hemophilie.domain.SaignementSNCTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaignementSNCMapperTest {

    private SaignementSNCMapper saignementSNCMapper;

    @BeforeEach
    void setUp() {
        saignementSNCMapper = new SaignementSNCMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSaignementSNCSample1();
        var actual = saignementSNCMapper.toEntity(saignementSNCMapper.toDto(expected));
        assertSaignementSNCAllPropertiesEquals(expected, actual);
    }
}
