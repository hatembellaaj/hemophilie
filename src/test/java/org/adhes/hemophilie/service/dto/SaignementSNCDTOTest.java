package org.adhes.hemophilie.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SaignementSNCDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SaignementSNCDTO.class);
        SaignementSNCDTO saignementSNCDTO1 = new SaignementSNCDTO();
        saignementSNCDTO1.setId(1L);
        SaignementSNCDTO saignementSNCDTO2 = new SaignementSNCDTO();
        assertThat(saignementSNCDTO1).isNotEqualTo(saignementSNCDTO2);
        saignementSNCDTO2.setId(saignementSNCDTO1.getId());
        assertThat(saignementSNCDTO1).isEqualTo(saignementSNCDTO2);
        saignementSNCDTO2.setId(2L);
        assertThat(saignementSNCDTO1).isNotEqualTo(saignementSNCDTO2);
        saignementSNCDTO1.setId(null);
        assertThat(saignementSNCDTO1).isNotEqualTo(saignementSNCDTO2);
    }
}
