package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.CauHinhHopDongTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CauHinhHopDongTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhHopDong.class);
        CauHinhHopDong cauHinhHopDong1 = getCauHinhHopDongSample1();
        CauHinhHopDong cauHinhHopDong2 = new CauHinhHopDong();
        assertThat(cauHinhHopDong1).isNotEqualTo(cauHinhHopDong2);

        cauHinhHopDong2.setId(cauHinhHopDong1.getId());
        assertThat(cauHinhHopDong1).isEqualTo(cauHinhHopDong2);

        cauHinhHopDong2 = getCauHinhHopDongSample2();
        assertThat(cauHinhHopDong1).isNotEqualTo(cauHinhHopDong2);
    }
}
