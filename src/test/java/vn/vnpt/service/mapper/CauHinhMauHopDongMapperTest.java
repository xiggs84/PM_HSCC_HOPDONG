package vn.vnpt.service.mapper;

import static vn.vnpt.domain.CauHinhMauHopDongAsserts.*;
import static vn.vnpt.domain.CauHinhMauHopDongTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CauHinhMauHopDongMapperTest {

    private CauHinhMauHopDongMapper cauHinhMauHopDongMapper;

    @BeforeEach
    void setUp() {
        cauHinhMauHopDongMapper = new CauHinhMauHopDongMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCauHinhMauHopDongSample1();
        var actual = cauHinhMauHopDongMapper.toEntity(cauHinhMauHopDongMapper.toDto(expected));
        assertCauHinhMauHopDongAllPropertiesEquals(expected, actual);
    }
}
