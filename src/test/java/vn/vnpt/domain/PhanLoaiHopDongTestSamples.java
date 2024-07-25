package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PhanLoaiHopDongTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static PhanLoaiHopDong getPhanLoaiHopDongSample1() {
        return new PhanLoaiHopDong().id(1L).idPhanLoaiHopDong(1L).dienGiai("dienGiai1");
    }

    public static PhanLoaiHopDong getPhanLoaiHopDongSample2() {
        return new PhanLoaiHopDong().id(2L).idPhanLoaiHopDong(2L).dienGiai("dienGiai2");
    }

    public static PhanLoaiHopDong getPhanLoaiHopDongRandomSampleGenerator() {
        return new PhanLoaiHopDong()
            .id(longCount.incrementAndGet())
            .idPhanLoaiHopDong(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString());
    }
}
