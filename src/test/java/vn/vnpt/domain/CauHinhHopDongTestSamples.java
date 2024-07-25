package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CauHinhHopDongTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CauHinhHopDong getCauHinhHopDongSample1() {
        return new CauHinhHopDong()
            .id(1L)
            .idLoaiHopDong(1L)
            .idDonVi(1L)
            .chieuDai(1L)
            .tienTo("tienTo1")
            .giaTri(1L)
            .hienThi("hienThi1")
            .trangThai(1L);
    }

    public static CauHinhHopDong getCauHinhHopDongSample2() {
        return new CauHinhHopDong()
            .id(2L)
            .idLoaiHopDong(2L)
            .idDonVi(2L)
            .chieuDai(2L)
            .tienTo("tienTo2")
            .giaTri(2L)
            .hienThi("hienThi2")
            .trangThai(2L);
    }

    public static CauHinhHopDong getCauHinhHopDongRandomSampleGenerator() {
        return new CauHinhHopDong()
            .id(longCount.incrementAndGet())
            .idLoaiHopDong(longCount.incrementAndGet())
            .idDonVi(longCount.incrementAndGet())
            .chieuDai(longCount.incrementAndGet())
            .tienTo(UUID.randomUUID().toString())
            .giaTri(longCount.incrementAndGet())
            .hienThi(UUID.randomUUID().toString())
            .trangThai(longCount.incrementAndGet());
    }
}
