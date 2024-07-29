package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.CauHinhHopDong} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CauHinhHopDongDTO implements Serializable {

    private Long id;

    private Long idLoaiHopDong;

    private Long idDonVi;

    private Long chieuDai;

    private String tienTo;

    private String phuongThucThanhToan;

    private String thongTinThem;

    private Long giaTri;

    private String hienThi;

    private Long trangThai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoaiHopDong() {
        return idLoaiHopDong;
    }

    public void setIdLoaiHopDong(Long idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getChieuDai() {
        return chieuDai;
    }

    public void setChieuDai(Long chieuDai) {
        this.chieuDai = chieuDai;
    }

    public String getTienTo() {
        return tienTo;
    }

    public void setTienTo(String tienTo) {
        this.tienTo = tienTo;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getThongTinThem() {
        return thongTinThem;
    }

    public void setThongTinThem(String thongTinThem) {
        this.thongTinThem = thongTinThem;
    }

    public Long getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
    }

    public String getHienThi() {
        return hienThi;
    }

    public void setHienThi(String hienThi) {
        this.hienThi = hienThi;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHinhHopDongDTO)) {
            return false;
        }

        CauHinhHopDongDTO cauHinhHopDongDTO = (CauHinhHopDongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cauHinhHopDongDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHinhHopDongDTO{" +
            "id=" + getId() +
            ", idLoaiHopDong=" + getIdLoaiHopDong() +
            ", idDonVi=" + getIdDonVi() +
            ", chieuDai=" + getChieuDai() +
            ", tienTo='" + getTienTo() + "'" +
            ", phuongThucThanhToan='" + getPhuongThucThanhToan() + "'" +
            ", thongTinThem='" + getThongTinThem() + "'" +
            ", giaTri=" + getGiaTri() +
            ", hienThi='" + getHienThi() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
