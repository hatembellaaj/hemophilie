package org.adhes.hemophilie.domain;

import static org.adhes.hemophilie.domain.FicheTestSamples.*;
import static org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueusesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HemorragiesCutaneoMuqueusesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HemorragiesCutaneoMuqueuses.class);
        HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses1 = getHemorragiesCutaneoMuqueusesSample1();
        HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses2 = new HemorragiesCutaneoMuqueuses();
        assertThat(hemorragiesCutaneoMuqueuses1).isNotEqualTo(hemorragiesCutaneoMuqueuses2);

        hemorragiesCutaneoMuqueuses2.setId(hemorragiesCutaneoMuqueuses1.getId());
        assertThat(hemorragiesCutaneoMuqueuses1).isEqualTo(hemorragiesCutaneoMuqueuses2);

        hemorragiesCutaneoMuqueuses2 = getHemorragiesCutaneoMuqueusesSample2();
        assertThat(hemorragiesCutaneoMuqueuses1).isNotEqualTo(hemorragiesCutaneoMuqueuses2);
    }

    @Test
    void ficheTest() {
        HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses = getHemorragiesCutaneoMuqueusesRandomSampleGenerator();
        Fiche ficheBack = getFicheRandomSampleGenerator();

        hemorragiesCutaneoMuqueuses.setFiche(ficheBack);
        assertThat(hemorragiesCutaneoMuqueuses.getFiche()).isEqualTo(ficheBack);
        assertThat(ficheBack.getHemorragiesCutaneoMuqueuses()).isEqualTo(hemorragiesCutaneoMuqueuses);

        hemorragiesCutaneoMuqueuses.fiche(null);
        assertThat(hemorragiesCutaneoMuqueuses.getFiche()).isNull();
        assertThat(ficheBack.getHemorragiesCutaneoMuqueuses()).isNull();
    }
}
