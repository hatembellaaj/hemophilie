package org.adhes.hemophilie.service.mapper;

import static org.adhes.hemophilie.domain.FicheAsserts.*;
import static org.adhes.hemophilie.domain.FicheTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheMapperTest {

    private FicheMapper ficheMapper;

    @BeforeEach
    void setUp() {
        ficheMapper = new FicheMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheSample1();
        var actual = ficheMapper.toEntity(ficheMapper.toDto(expected));
        assertFicheAllPropertiesEquals(expected, actual);
    }
}
