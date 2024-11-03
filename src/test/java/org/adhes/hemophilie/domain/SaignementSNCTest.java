package org.adhes.hemophilie.domain;

import static org.adhes.hemophilie.domain.FicheTestSamples.*;
import static org.adhes.hemophilie.domain.SaignementSNCTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SaignementSNCTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SaignementSNC.class);
        SaignementSNC saignementSNC1 = getSaignementSNCSample1();
        SaignementSNC saignementSNC2 = new SaignementSNC();
        assertThat(saignementSNC1).isNotEqualTo(saignementSNC2);

        saignementSNC2.setId(saignementSNC1.getId());
        assertThat(saignementSNC1).isEqualTo(saignementSNC2);

        saignementSNC2 = getSaignementSNCSample2();
        assertThat(saignementSNC1).isNotEqualTo(saignementSNC2);
    }

    @Test
    void ficheTest() {
        SaignementSNC saignementSNC = getSaignementSNCRandomSampleGenerator();
        Fiche ficheBack = getFicheRandomSampleGenerator();

        saignementSNC.setFiche(ficheBack);
        assertThat(saignementSNC.getFiche()).isEqualTo(ficheBack);
        assertThat(ficheBack.getSaignementSNC()).isEqualTo(saignementSNC);

        saignementSNC.fiche(null);
        assertThat(saignementSNC.getFiche()).isNull();
        assertThat(ficheBack.getSaignementSNC()).isNull();
    }
}
