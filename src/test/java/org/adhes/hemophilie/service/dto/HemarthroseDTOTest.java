package org.adhes.hemophilie.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HemarthroseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HemarthroseDTO.class);
        HemarthroseDTO hemarthroseDTO1 = new HemarthroseDTO();
        hemarthroseDTO1.setId(1L);
        HemarthroseDTO hemarthroseDTO2 = new HemarthroseDTO();
        assertThat(hemarthroseDTO1).isNotEqualTo(hemarthroseDTO2);
        hemarthroseDTO2.setId(hemarthroseDTO1.getId());
        assertThat(hemarthroseDTO1).isEqualTo(hemarthroseDTO2);
        hemarthroseDTO2.setId(2L);
        assertThat(hemarthroseDTO1).isNotEqualTo(hemarthroseDTO2);
        hemarthroseDTO1.setId(null);
        assertThat(hemarthroseDTO1).isNotEqualTo(hemarthroseDTO2);
    }
}
