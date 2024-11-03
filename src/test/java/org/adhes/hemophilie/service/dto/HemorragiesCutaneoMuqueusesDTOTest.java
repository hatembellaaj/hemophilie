package org.adhes.hemophilie.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemophilie.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HemorragiesCutaneoMuqueusesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HemorragiesCutaneoMuqueusesDTO.class);
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO1 = new HemorragiesCutaneoMuqueusesDTO();
        hemorragiesCutaneoMuqueusesDTO1.setId(1L);
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO2 = new HemorragiesCutaneoMuqueusesDTO();
        assertThat(hemorragiesCutaneoMuqueusesDTO1).isNotEqualTo(hemorragiesCutaneoMuqueusesDTO2);
        hemorragiesCutaneoMuqueusesDTO2.setId(hemorragiesCutaneoMuqueusesDTO1.getId());
        assertThat(hemorragiesCutaneoMuqueusesDTO1).isEqualTo(hemorragiesCutaneoMuqueusesDTO2);
        hemorragiesCutaneoMuqueusesDTO2.setId(2L);
        assertThat(hemorragiesCutaneoMuqueusesDTO1).isNotEqualTo(hemorragiesCutaneoMuqueusesDTO2);
        hemorragiesCutaneoMuqueusesDTO1.setId(null);
        assertThat(hemorragiesCutaneoMuqueusesDTO1).isNotEqualTo(hemorragiesCutaneoMuqueusesDTO2);
    }
}
