package org.adhes.hemophilie.domain;

import static org.adhes.hemophilie.domain.FicheTestSamples.*;
import static org.adhes.hemophilie.domain.HemorragieVisceresTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HemorragieVisceresTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HemorragieVisceres.class);
        HemorragieVisceres hemorragieVisceres1 = getHemorragieVisceresSample1();
        HemorragieVisceres hemorragieVisceres2 = new HemorragieVisceres();
        assertThat(hemorragieVisceres1).isNotEqualTo(hemorragieVisceres2);

        hemorragieVisceres2.setId(hemorragieVisceres1.getId());
        assertThat(hemorragieVisceres1).isEqualTo(hemorragieVisceres2);

        hemorragieVisceres2 = getHemorragieVisceresSample2();
        assertThat(hemorragieVisceres1).isNotEqualTo(hemorragieVisceres2);
    }

    @Test
    void ficheTest() {
        HemorragieVisceres hemorragieVisceres = getHemorragieVisceresRandomSampleGenerator();
        Fiche ficheBack = getFicheRandomSampleGenerator();

        hemorragieVisceres.setFiche(ficheBack);
        assertThat(hemorragieVisceres.getFiche()).isEqualTo(ficheBack);
        assertThat(ficheBack.getHemorragieVisceres()).isEqualTo(hemorragieVisceres);

        hemorragieVisceres.fiche(null);
        assertThat(hemorragieVisceres.getFiche()).isNull();
        assertThat(ficheBack.getHemorragieVisceres()).isNull();
    }
}
