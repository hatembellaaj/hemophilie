package org.adhes.hemophilie.domain;

import static org.adhes.hemophilie.domain.FicheTestSamples.*;
import static org.adhes.hemophilie.domain.HematomeSuperficielTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HematomeSuperficielTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HematomeSuperficiel.class);
        HematomeSuperficiel hematomeSuperficiel1 = getHematomeSuperficielSample1();
        HematomeSuperficiel hematomeSuperficiel2 = new HematomeSuperficiel();
        assertThat(hematomeSuperficiel1).isNotEqualTo(hematomeSuperficiel2);

        hematomeSuperficiel2.setId(hematomeSuperficiel1.getId());
        assertThat(hematomeSuperficiel1).isEqualTo(hematomeSuperficiel2);

        hematomeSuperficiel2 = getHematomeSuperficielSample2();
        assertThat(hematomeSuperficiel1).isNotEqualTo(hematomeSuperficiel2);
    }

    @Test
    void ficheTest() {
        HematomeSuperficiel hematomeSuperficiel = getHematomeSuperficielRandomSampleGenerator();
        Fiche ficheBack = getFicheRandomSampleGenerator();

        hematomeSuperficiel.setFiche(ficheBack);
        assertThat(hematomeSuperficiel.getFiche()).isEqualTo(ficheBack);
        assertThat(ficheBack.getHematomeSuperficiel()).isEqualTo(hematomeSuperficiel);

        hematomeSuperficiel.fiche(null);
        assertThat(hematomeSuperficiel.getFiche()).isNull();
        assertThat(ficheBack.getHematomeSuperficiel()).isNull();
    }
}
