package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CauHinhHopDongDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhHopDongDTO.class);
        CauHinhHopDongDTO cauHinhHopDongDTO1 = new CauHinhHopDongDTO();
        cauHinhHopDongDTO1.setId(1L);
        CauHinhHopDongDTO cauHinhHopDongDTO2 = new CauHinhHopDongDTO();
        assertThat(cauHinhHopDongDTO1).isNotEqualTo(cauHinhHopDongDTO2);
        cauHinhHopDongDTO2.setId(cauHinhHopDongDTO1.getId());
        assertThat(cauHinhHopDongDTO1).isEqualTo(cauHinhHopDongDTO2);
        cauHinhHopDongDTO2.setId(2L);
        assertThat(cauHinhHopDongDTO1).isNotEqualTo(cauHinhHopDongDTO2);
        cauHinhHopDongDTO1.setId(null);
        assertThat(cauHinhHopDongDTO1).isNotEqualTo(cauHinhHopDongDTO2);
    }
}
