package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucLoaiVanBanTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucLoaiVanBan getDanhMucLoaiVanBanSample1() {
        return new DanhMucLoaiVanBan().id(1L).idLoaiVb(1L).dienGiai("dienGiai1").idLoaiHopDong(1L);
    }

    public static DanhMucLoaiVanBan getDanhMucLoaiVanBanSample2() {
        return new DanhMucLoaiVanBan().id(2L).idLoaiVb(2L).dienGiai("dienGiai2").idLoaiHopDong(2L);
    }

    public static DanhMucLoaiVanBan getDanhMucLoaiVanBanRandomSampleGenerator() {
        return new DanhMucLoaiVanBan()
            .id(longCount.incrementAndGet())
            .idLoaiVb(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString())
            .idLoaiHopDong(longCount.incrementAndGet());
    }
}
