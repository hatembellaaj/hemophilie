package org.adhes.hemophilie.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HematomeSuperficielDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HematomeSuperficielDTO.class);
        HematomeSuperficielDTO hematomeSuperficielDTO1 = new HematomeSuperficielDTO();
        hematomeSuperficielDTO1.setId(1L);
        HematomeSuperficielDTO hematomeSuperficielDTO2 = new HematomeSuperficielDTO();
        assertThat(hematomeSuperficielDTO1).isNotEqualTo(hematomeSuperficielDTO2);
        hematomeSuperficielDTO2.setId(hematomeSuperficielDTO1.getId());
        assertThat(hematomeSuperficielDTO1).isEqualTo(hematomeSuperficielDTO2);
        hematomeSuperficielDTO2.setId(2L);
        assertThat(hematomeSuperficielDTO1).isNotEqualTo(hematomeSuperficielDTO2);
        hematomeSuperficielDTO1.setId(null);
        assertThat(hematomeSuperficielDTO1).isNotEqualTo(hematomeSuperficielDTO2);
    }
}
