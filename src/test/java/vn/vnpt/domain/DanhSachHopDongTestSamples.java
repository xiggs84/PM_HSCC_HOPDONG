package vn.vnpt.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DanhSachHopDongTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DanhSachHopDong getDanhSachHopDongSample1() {
        return new DanhSachHopDong()
            .id(1L)
            .nguoiLapHd(1L)
            .trangThai(1L)
            .idLoaiHd(1L)
            .idDonVi(1L)
            .nguoiThaoTac(1L)
            .srcHopDong("srcHopDong1")
            .soCongChung("soCongChung1")
            .congChungVien(1L)
            .soTienRutTrich(1L)
            .hdThuCong(1L)
            .trangThaiRutTrich(1L)
            .chuKyNgoaiTruSo(1L)
            .strSearch("strSearch1")
            .ngayText("ngayText1")
            .ngayRutTrichText("ngayRutTrichText1")
            .thuLaoCongChung(1L)
            .quyenLaiSt("quyenLaiSt1")
            .soLaiSt("soLaiSt1")
            .quyenLaiTl("quyenLaiTl1")
            .soLaiTl("soLaiTl1")
            .srcKySoPdf("srcKySoPdf1")
            .srcKySoPdfSigned("srcKySoPdfSigned1");
    }

    public static DanhSachHopDong getDanhSachHopDongSample2() {
        return new DanhSachHopDong()
            .id(2L)
            .nguoiLapHd(2L)
            .trangThai(2L)
            .idLoaiHd(2L)
            .idDonVi(2L)
            .nguoiThaoTac(2L)
            .srcHopDong("srcHopDong2")
            .soCongChung("soCongChung2")
            .congChungVien(2L)
            .soTienRutTrich(2L)
            .hdThuCong(2L)
            .trangThaiRutTrich(2L)
            .chuKyNgoaiTruSo(2L)
            .strSearch("strSearch2")
            .ngayText("ngayText2")
            .ngayRutTrichText("ngayRutTrichText2")
            .thuLaoCongChung(2L)
            .quyenLaiSt("quyenLaiSt2")
            .soLaiSt("soLaiSt2")
            .quyenLaiTl("quyenLaiTl2")
            .soLaiTl("soLaiTl2")
            .srcKySoPdf("srcKySoPdf2")
            .srcKySoPdfSigned("srcKySoPdfSigned2");
    }

    public static DanhSachHopDong getDanhSachHopDongRandomSampleGenerator() {
        return new DanhSachHopDong()
            .id(longCount.incrementAndGet())
            .nguoiLapHd(longCount.incrementAndGet())
            .trangThai(longCount.incrementAndGet())
            .idLoaiHd(longCount.incrementAndGet())
            .idDonVi(longCount.incrementAndGet())
            .nguoiThaoTac(longCount.incrementAndGet())
            .srcHopDong(UUID.randomUUID().toString())
            .soCongChung(UUID.randomUUID().toString())
            .congChungVien(longCount.incrementAndGet())
            .soTienRutTrich(longCount.incrementAndGet())
            .hdThuCong(longCount.incrementAndGet())
            .trangThaiRutTrich(longCount.incrementAndGet())
            .chuKyNgoaiTruSo(longCount.incrementAndGet())
            .strSearch(UUID.randomUUID().toString())
            .ngayText(UUID.randomUUID().toString())
            .ngayRutTrichText(UUID.randomUUID().toString())
            .thuLaoCongChung(longCount.incrementAndGet())
            .quyenLaiSt(UUID.randomUUID().toString())
            .soLaiSt(UUID.randomUUID().toString())
            .quyenLaiTl(UUID.randomUUID().toString())
            .soLaiTl(UUID.randomUUID().toString())
            .srcKySoPdf(UUID.randomUUID().toString())
            .srcKySoPdfSigned(UUID.randomUUID().toString());
    }
}
