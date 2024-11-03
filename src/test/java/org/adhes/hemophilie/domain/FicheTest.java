package org.adhes.hemophilie.domain;

import static org.adhes.hemophilie.domain.FicheTestSamples.*;
import static org.adhes.hemophilie.domain.HemarthroseTestSamples.*;
import static org.adhes.hemophilie.domain.HematomePsoasTestSamples.*;
import static org.adhes.hemophilie.domain.HematomeSuperficielTestSamples.*;
import static org.adhes.hemophilie.domain.HemorragieVisceresTestSamples.*;
import static org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueusesTestSamples.*;
import static org.adhes.hemophilie.domain.PatientTestSamples.*;
import static org.adhes.hemophilie.domain.SaignementSNCTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FicheTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fiche.class);
        Fiche fiche1 = getFicheSample1();
        Fiche fiche2 = new Fiche();
        assertThat(fiche1).isNotEqualTo(fiche2);

        fiche2.setId(fiche1.getId());
        assertThat(fiche1).isEqualTo(fiche2);

        fiche2 = getFicheSample2();
        assertThat(fiche1).isNotEqualTo(fiche2);
    }

    @Test
    void hemarthroseTest() {
        Fiche fiche = getFicheRandomSampleGenerator();
        Hemarthrose hemarthroseBack = getHemarthroseRandomSampleGenerator();

        fiche.setHemarthrose(hemarthroseBack);
        assertThat(fiche.getHemarthrose()).isEqualTo(hemarthroseBack);

        fiche.hemarthrose(null);
        assertThat(fiche.getHemarthrose()).isNull();
    }

    @Test
    void hematomeSuperficielTest() {
        Fiche fiche = getFicheRandomSampleGenerator();
        HematomeSuperficiel hematomeSuperficielBack = getHematomeSuperficielRandomSampleGenerator();

        fiche.setHematomeSuperficiel(hematomeSuperficielBack);
        assertThat(fiche.getHematomeSuperficiel()).isEqualTo(hematomeSuperficielBack);

        fiche.hematomeSuperficiel(null);
        assertThat(fiche.getHematomeSuperficiel()).isNull();
    }

    @Test
    void hematomePsoasTest() {
        Fiche fiche = getFicheRandomSampleGenerator();
        HematomePsoas hematomePsoasBack = getHematomePsoasRandomSampleGenerator();

        fiche.setHematomePsoas(hematomePsoasBack);
        assertThat(fiche.getHematomePsoas()).isEqualTo(hematomePsoasBack);

        fiche.hematomePsoas(null);
        assertThat(fiche.getHematomePsoas()).isNull();
    }

    @Test
    void hemorragiesCutaneoMuqueusesTest() {
        Fiche fiche = getFicheRandomSampleGenerator();
        HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueusesBack = getHemorragiesCutaneoMuqueusesRandomSampleGenerator();

        fiche.setHemorragiesCutaneoMuqueuses(hemorragiesCutaneoMuqueusesBack);
        assertThat(fiche.getHemorragiesCutaneoMuqueuses()).isEqualTo(hemorragiesCutaneoMuqueusesBack);

        fiche.hemorragiesCutaneoMuqueuses(null);
        assertThat(fiche.getHemorragiesCutaneoMuqueuses()).isNull();
    }

    @Test
    void hemorragieVisceresTest() {
        Fiche fiche = getFicheRandomSampleGenerator();
        HemorragieVisceres hemorragieVisceresBack = getHemorragieVisceresRandomSampleGenerator();

        fiche.setHemorragieVisceres(hemorragieVisceresBack);
        assertThat(fiche.getHemorragieVisceres()).isEqualTo(hemorragieVisceresBack);

        fiche.hemorragieVisceres(null);
        assertThat(fiche.getHemorragieVisceres()).isNull();
    }

    @Test
    void saignementSNCTest() {
        Fiche fiche = getFicheRandomSampleGenerator();
        SaignementSNC saignementSNCBack = getSaignementSNCRandomSampleGenerator();

        fiche.setSaignementSNC(saignementSNCBack);
        assertThat(fiche.getSaignementSNC()).isEqualTo(saignementSNCBack);

        fiche.saignementSNC(null);
        assertThat(fiche.getSaignementSNC()).isNull();
    }

    @Test
    void patientTest() {
        Fiche fiche = getFicheRandomSampleGenerator();
        Patient patientBack = getPatientRandomSampleGenerator();

        fiche.setPatient(patientBack);
        assertThat(fiche.getPatient()).isEqualTo(patientBack);

        fiche.patient(null);
        assertThat(fiche.getPatient()).isNull();
    }
}
