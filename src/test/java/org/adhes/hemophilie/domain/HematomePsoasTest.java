package org.adhes.hemophilie.domain;

import static org.adhes.hemophilie.domain.FicheTestSamples.*;
import static org.adhes.hemophilie.domain.HematomePsoasTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HematomePsoasTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HematomePsoas.class);
        HematomePsoas hematomePsoas1 = getHematomePsoasSample1();
        HematomePsoas hematomePsoas2 = new HematomePsoas();
        assertThat(hematomePsoas1).isNotEqualTo(hematomePsoas2);

        hematomePsoas2.setId(hematomePsoas1.getId());
        assertThat(hematomePsoas1).isEqualTo(hematomePsoas2);

        hematomePsoas2 = getHematomePsoasSample2();
        assertThat(hematomePsoas1).isNotEqualTo(hematomePsoas2);
    }

    @Test
    void ficheTest() {
        HematomePsoas hematomePsoas = getHematomePsoasRandomSampleGenerator();
        Fiche ficheBack = getFicheRandomSampleGenerator();

        hematomePsoas.setFiche(ficheBack);
        assertThat(hematomePsoas.getFiche()).isEqualTo(ficheBack);
        assertThat(ficheBack.getHematomePsoas()).isEqualTo(hematomePsoas);

        hematomePsoas.fiche(null);
        assertThat(hematomePsoas.getFiche()).isNull();
        assertThat(ficheBack.getHematomePsoas()).isNull();
    }
}
