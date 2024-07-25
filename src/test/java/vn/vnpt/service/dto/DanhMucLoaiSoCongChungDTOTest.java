package vn.vnpt.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class DanhMucLoaiSoCongChungDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhMucLoaiSoCongChungDTO.class);
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO1 = new DanhMucLoaiSoCongChungDTO();
        danhMucLoaiSoCongChungDTO1.setId(1L);
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO2 = new DanhMucLoaiSoCongChungDTO();
        assertThat(danhMucLoaiSoCongChungDTO1).isNotEqualTo(danhMucLoaiSoCongChungDTO2);
        danhMucLoaiSoCongChungDTO2.setId(danhMucLoaiSoCongChungDTO1.getId());
        assertThat(danhMucLoaiSoCongChungDTO1).isEqualTo(danhMucLoaiSoCongChungDTO2);
        danhMucLoaiSoCongChungDTO2.setId(2L);
        assertThat(danhMucLoaiSoCongChungDTO1).isNotEqualTo(danhMucLoaiSoCongChungDTO2);
        danhMucLoaiSoCongChungDTO1.setId(null);
        assertThat(danhMucLoaiSoCongChungDTO1).isNotEqualTo(danhMucLoaiSoCongChungDTO2);
    }
}
