package org.adhes.hemophilie.domain;

import static org.adhes.hemophilie.domain.FicheTestSamples.*;
import static org.adhes.hemophilie.domain.PatientTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PatientTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Patient.class);
        Patient patient1 = getPatientSample1();
        Patient patient2 = new Patient();
        assertThat(patient1).isNotEqualTo(patient2);

        patient2.setId(patient1.getId());
        assertThat(patient1).isEqualTo(patient2);

        patient2 = getPatientSample2();
        assertThat(patient1).isNotEqualTo(patient2);
    }

    @Test
    void fichesTest() {
        Patient patient = getPatientRandomSampleGenerator();
        Fiche ficheBack = getFicheRandomSampleGenerator();

        patient.addFiches(ficheBack);
        assertThat(patient.getFiches()).containsOnly(ficheBack);
        assertThat(ficheBack.getPatient()).isEqualTo(patient);

        patient.removeFiches(ficheBack);
        assertThat(patient.getFiches()).doesNotContain(ficheBack);
        assertThat(ficheBack.getPatient()).isNull();

        patient.fiches(new HashSet<>(Set.of(ficheBack)));
        assertThat(patient.getFiches()).containsOnly(ficheBack);
        assertThat(ficheBack.getPatient()).isEqualTo(patient);

        patient.setFiches(new HashSet<>());
        assertThat(patient.getFiches()).doesNotContain(ficheBack);
        assertThat(ficheBack.getPatient()).isNull();
    }
}
