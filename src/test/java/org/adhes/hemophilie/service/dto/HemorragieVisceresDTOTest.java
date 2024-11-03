package org.adhes.hemophilie.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HemorragieVisceresDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HemorragieVisceresDTO.class);
        HemorragieVisceresDTO hemorragieVisceresDTO1 = new HemorragieVisceresDTO();
        hemorragieVisceresDTO1.setId(1L);
        HemorragieVisceresDTO hemorragieVisceresDTO2 = new HemorragieVisceresDTO();
        assertThat(hemorragieVisceresDTO1).isNotEqualTo(hemorragieVisceresDTO2);
        hemorragieVisceresDTO2.setId(hemorragieVisceresDTO1.getId());
        assertThat(hemorragieVisceresDTO1).isEqualTo(hemorragieVisceresDTO2);
        hemorragieVisceresDTO2.setId(2L);
        assertThat(hemorragieVisceresDTO1).isNotEqualTo(hemorragieVisceresDTO2);
        hemorragieVisceresDTO1.setId(null);
        assertThat(hemorragieVisceresDTO1).isNotEqualTo(hemorragieVisceresDTO2);
    }
}
