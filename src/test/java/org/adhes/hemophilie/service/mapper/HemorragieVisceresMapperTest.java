package org.adhes.hemophilie.service.mapper;

import static org.adhes.hemophilie.domain.HemorragieVisceresAsserts.*;
import static org.adhes.hemophilie.domain.HemorragieVisceresTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HemorragieVisceresMapperTest {

    private HemorragieVisceresMapper hemorragieVisceresMapper;

    @BeforeEach
    void setUp() {
        hemorragieVisceresMapper = new HemorragieVisceresMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHemorragieVisceresSample1();
        var actual = hemorragieVisceresMapper.toEntity(hemorragieVisceresMapper.toDto(expected));
        assertHemorragieVisceresAllPropertiesEquals(expected, actual);
    }
}
