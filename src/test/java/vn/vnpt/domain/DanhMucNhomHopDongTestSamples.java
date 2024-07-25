package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucNhomHopDongTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucNhomHopDong getDanhMucNhomHopDongSample1() {
        return new DanhMucNhomHopDong().id(1L).idNhom(1L).dienGiai("dienGiai1");
    }

    public static DanhMucNhomHopDong getDanhMucNhomHopDongSample2() {
        return new DanhMucNhomHopDong().id(2L).idNhom(2L).dienGiai("dienGiai2");
    }

    public static DanhMucNhomHopDong getDanhMucNhomHopDongRandomSampleGenerator() {
        return new DanhMucNhomHopDong()
            .id(longCount.incrementAndGet())
            .idNhom(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString());
    }
}
