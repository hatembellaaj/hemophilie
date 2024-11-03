package org.adhes.hemophilie.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HematomePsoasDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HematomePsoasDTO.class);
        HematomePsoasDTO hematomePsoasDTO1 = new HematomePsoasDTO();
        hematomePsoasDTO1.setId(1L);
        HematomePsoasDTO hematomePsoasDTO2 = new HematomePsoasDTO();
        assertThat(hematomePsoasDTO1).isNotEqualTo(hematomePsoasDTO2);
        hematomePsoasDTO2.setId(hematomePsoasDTO1.getId());
        assertThat(hematomePsoasDTO1).isEqualTo(hematomePsoasDTO2);
        hematomePsoasDTO2.setId(2L);
        assertThat(hematomePsoasDTO1).isNotEqualTo(hematomePsoasDTO2);
        hematomePsoasDTO1.setId(null);
        assertThat(hematomePsoasDTO1).isNotEqualTo(hematomePsoasDTO2);
    }
}
