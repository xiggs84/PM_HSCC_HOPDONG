package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class PhanLoaiHopDongDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhanLoaiHopDongDTO.class);
        PhanLoaiHopDongDTO phanLoaiHopDongDTO1 = new PhanLoaiHopDongDTO();
        phanLoaiHopDongDTO1.setId(1L);
        PhanLoaiHopDongDTO phanLoaiHopDongDTO2 = new PhanLoaiHopDongDTO();
        assertThat(phanLoaiHopDongDTO1).isNotEqualTo(phanLoaiHopDongDTO2);
        phanLoaiHopDongDTO2.setId(phanLoaiHopDongDTO1.getId());
        assertThat(phanLoaiHopDongDTO1).isEqualTo(phanLoaiHopDongDTO2);
        phanLoaiHopDongDTO2.setId(2L);
        assertThat(phanLoaiHopDongDTO1).isNotEqualTo(phanLoaiHopDongDTO2);
        phanLoaiHopDongDTO1.setId(null);
        assertThat(phanLoaiHopDongDTO1).isNotEqualTo(phanLoaiHopDongDTO2);
    }
}
