package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucLoaiSoCongChungTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucLoaiSoCongChung getDanhMucLoaiSoCongChungSample1() {
        return new DanhMucLoaiSoCongChung().id(1L).idLoai(1L).tenLoai("tenLoai1").trangThai(1L);
    }

    public static DanhMucLoaiSoCongChung getDanhMucLoaiSoCongChungSample2() {
        return new DanhMucLoaiSoCongChung().id(2L).idLoai(2L).tenLoai("tenLoai2").trangThai(2L);
    }

    public static DanhMucLoaiSoCongChung getDanhMucLoaiSoCongChungRandomSampleGenerator() {
        return new DanhMucLoaiSoCongChung()
            .id(longCount.incrementAndGet())
            .idLoai(longCount.incrementAndGet())
            .tenLoai(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet());
    }
}
