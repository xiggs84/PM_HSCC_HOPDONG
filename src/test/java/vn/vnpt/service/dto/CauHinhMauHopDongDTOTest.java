package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CauHinhMauHopDongDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhMauHopDongDTO.class);
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO1 = new CauHinhMauHopDongDTO();
        cauHinhMauHopDongDTO1.setId(1L);
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO2 = new CauHinhMauHopDongDTO();
        assertThat(cauHinhMauHopDongDTO1).isNotEqualTo(cauHinhMauHopDongDTO2);
        cauHinhMauHopDongDTO2.setId(cauHinhMauHopDongDTO1.getId());
        assertThat(cauHinhMauHopDongDTO1).isEqualTo(cauHinhMauHopDongDTO2);
        cauHinhMauHopDongDTO2.setId(2L);
        assertThat(cauHinhMauHopDongDTO1).isNotEqualTo(cauHinhMauHopDongDTO2);
        cauHinhMauHopDongDTO1.setId(null);
        assertThat(cauHinhMauHopDongDTO1).isNotEqualTo(cauHinhMauHopDongDTO2);
    }
}
