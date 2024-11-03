package org.adhes.hemophilie.domain;

import static org.adhes.hemophilie.domain.FicheTestSamples.*;
import static org.adhes.hemophilie.domain.HemarthroseTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HemarthroseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hemarthrose.class);
        Hemarthrose hemarthrose1 = getHemarthroseSample1();
        Hemarthrose hemarthrose2 = new Hemarthrose();
        assertThat(hemarthrose1).isNotEqualTo(hemarthrose2);

        hemarthrose2.setId(hemarthrose1.getId());
        assertThat(hemarthrose1).isEqualTo(hemarthrose2);

        hemarthrose2 = getHemarthroseSample2();
        assertThat(hemarthrose1).isNotEqualTo(hemarthrose2);
    }

    @Test
    void ficheTest() {
        Hemarthrose hemarthrose = getHemarthroseRandomSampleGenerator();
        Fiche ficheBack = getFicheRandomSampleGenerator();

        hemarthrose.setFiche(ficheBack);
        assertThat(hemarthrose.getFiche()).isEqualTo(ficheBack);
        assertThat(ficheBack.getHemarthrose()).isEqualTo(hemarthrose);

        hemarthrose.fiche(null);
        assertThat(hemarthrose.getFiche()).isNull();
        assertThat(ficheBack.getHemarthrose()).isNull();
    }
}
