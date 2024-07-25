package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhMucLoaiHopDongTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhMucLoaiHopDong getDanhMucLoaiHopDongSample1() {
        return new DanhMucLoaiHopDong()
            .id(1L)
            .idLoaiHd(1L)
            .dienGiai("dienGiai1")
            .idVaiTro1(1L)
            .idVaiTro2(1L)
            .fileHopDong("fileHopDong1")
            .srcHopDong("srcHopDong1")
            .dieuKhoan("dieuKhoan1")
            .idDonVi(1L)
            .trangThai(1L)
            .nguoiThaoTac(1L)
            .srcLoiChung("srcLoiChung1")
            .idNhom(1L)
            .fileLoiChung("fileLoiChung1")
            .chuyenTaiSan(1L)
            .loaiSuaDoi(1L)
            .loaiHuyBo(1L)
            .trangThaiDuyet(1L)
            .idPhanLoaiHopDong(1L)
            .srcCv("srcCv1")
            .srcTb("srcTb1")
            .srcTtpc("srcTtpc1")
            .dgTen("dgTen1")
            .nhomTen(1L)
            .idVaiTro3(1L);
    }

    public static DanhMucLoaiHopDong getDanhMucLoaiHopDongSample2() {
        return new DanhMucLoaiHopDong()
            .id(2L)
            .idLoaiHd(2L)
            .dienGiai("dienGiai2")
            .idVaiTro1(2L)
            .idVaiTro2(2L)
            .fileHopDong("fileHopDong2")
            .srcHopDong("srcHopDong2")
            .dieuKhoan("dieuKhoan2")
            .idDonVi(2L)
            .trangThai(2L)
            .nguoiThaoTac(2L)
            .srcLoiChung("srcLoiChung2")
            .idNhom(2L)
            .fileLoiChung("fileLoiChung2")
            .chuyenTaiSan(2L)
            .loaiSuaDoi(2L)
            .loaiHuyBo(2L)
            .trangThaiDuyet(2L)
            .idPhanLoaiHopDong(2L)
            .srcCv("srcCv2")
            .srcTb("srcTb2")
            .srcTtpc("srcTtpc2")
            .dgTen("dgTen2")
            .nhomTen(2L)
            .idVaiTro3(2L);
    }

    public static DanhMucLoaiHopDong getDanhMucLoaiHopDongRandomSampleGenerator() {
        return new DanhMucLoaiHopDong()
            .id(longCount.incrementAndGet())
            .idLoaiHd(longCount.incrementAndGet())
            .dienGiai(UUID.randomUUID().toString())
            .idVaiTro1(longCount.incrementAndGet())
            .idVaiTro2(longCount.incrementAndGet())
            .fileHopDong(UUID.randomUUID().toString())
            .srcHopDong(UUID.randomUUID().toString())
            .dieuKhoan(UUID.randomUUID().toString())
            .idDonVi(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .srcLoiChung(UUID.randomUUID().toString())
            .idNhom(longCount.incrementAndGet())
            .fileLoiChung(UUID.randomUUID().toString())
            .chuyenTaiSan(longCount.incrementAndGet())
            .loaiSuaDoi(longCount.incrementAndGet())
            .loaiHuyBo(longCount.incrementAndGet())
            .trangThaiDuyet(longCount.incrementAndGet())
            .idPhanLoaiHopDong(longCount.incrementAndGet())
            .srcCv(UUID.randomUUID().toString())
            .srcTb(UUID.randomUUID().toString())
            .srcTtpc(UUID.randomUUID().toString())
            .dgTen(UUID.randomUUID().toString())
            .nhomTen(longCount.incrementAndGet())
            .idVaiTro3(longCount.incrementAndGet());
    }
}
