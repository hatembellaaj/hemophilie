package org.adhes.hemophilie.service.mapper;

import static org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueusesAsserts.*;
import static org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueusesTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HemorragiesCutaneoMuqueusesMapperTest {

    private HemorragiesCutaneoMuqueusesMapper hemorragiesCutaneoMuqueusesMapper;

    @BeforeEach
    void setUp() {
        hemorragiesCutaneoMuqueusesMapper = new HemorragiesCutaneoMuqueusesMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHemorragiesCutaneoMuqueusesSample1();
        var actual = hemorragiesCutaneoMuqueusesMapper.toEntity(hemorragiesCutaneoMuqueusesMapper.toDto(expected));
        assertHemorragiesCutaneoMuqueusesAllPropertiesEquals(expected, actual);
    }
}
