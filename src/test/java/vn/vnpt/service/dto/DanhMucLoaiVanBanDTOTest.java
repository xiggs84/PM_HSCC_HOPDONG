package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiVanBanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiVanBanDTO.class);
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO1 = new DanhMucLoaiVanBanDTO();
        danhMucLoaiVanBanDTO1.setId(1L);
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO2 = new DanhMucLoaiVanBanDTO();
        assertThat(danhMucLoaiVanBanDTO1).isNotEqualTo(danhMucLoaiVanBanDTO2);
        danhMucLoaiVanBanDTO2.setId(danhMucLoaiVanBanDTO1.getId());
        assertThat(danhMucLoaiVanBanDTO1).isEqualTo(danhMucLoaiVanBanDTO2);
        danhMucLoaiVanBanDTO2.setId(2L);
        assertThat(danhMucLoaiVanBanDTO1).isNotEqualTo(danhMucLoaiVanBanDTO2);
        danhMucLoaiVanBanDTO1.setId(null);
        assertThat(danhMucLoaiVanBanDTO1).isNotEqualTo(danhMucLoaiVanBanDTO2);
    }
}
