package vn.vnpt.service.mapper;

import static vn.vnpt.domain.CauHinhHopDongAsserts.*;
import static vn.vnpt.domain.CauHinhHopDongTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CauHinhHopDongMapperTest {

    private CauHinhHopDongMapper cauHinhHopDongMapper;

    @BeforeEach
    void setUp() {
        cauHinhHopDongMapper = new CauHinhHopDongMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCauHinhHopDongSample1();
        var actual = cauHinhHopDongMapper.toEntity(cauHinhHopDongMapper.toDto(expected));
        assertCauHinhHopDongAllPropertiesEquals(expected, actual);
    }
}
