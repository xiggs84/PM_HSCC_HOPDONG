package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vn.vnpt.domain.CauHinhMauHopDongTestSamples.*;

import org.junit.jupiter.api.Test;
import vn.vnpt.web.rest.TestUtil;

class CauHinhMauHopDongTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHinhMauHopDong.class);
        CauHinhMauHopDong cauHinhMauHopDong1 = getCauHinhMauHopDongSample1();
        CauHinhMauHopDong cauHinhMauHopDong2 = new CauHinhMauHopDong();
        assertThat(cauHinhMauHopDong1).isNotEqualTo(cauHinhMauHopDong2);

        cauHinhMauHopDong2.setId(cauHinhMauHopDong1.getId());
        assertThat(cauHinhMauHopDong1).isEqualTo(cauHinhMauHopDong2);

        cauHinhMauHopDong2 = getCauHinhMauHopDongSample2();
        assertThat(cauHinhMauHopDong1).isNotEqualTo(cauHinhMauHopDong2);
    }
}
