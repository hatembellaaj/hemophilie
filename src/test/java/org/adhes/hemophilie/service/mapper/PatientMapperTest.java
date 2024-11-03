package org.adhes.hemophilie.service.mapper;

import static org.adhes.hemophilie.domain.PatientAsserts.*;
import static org.adhes.hemophilie.domain.PatientTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientMapperTest {

    private PatientMapper patientMapper;

    @BeforeEach
    void setUp() {
        patientMapper = new PatientMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPatientSample1();
        var actual = patientMapper.toEntity(patientMapper.toDto(expected));
        assertPatientAllPropertiesEquals(expected, actual);
    }
}
