package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucLoaiHopDong} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiHopDongDTO implements Serializable {

    private Long id;

    private Long idLoaiHd;

    private String dienGiai;

    private Long idVaiTro1;

    private Long idVaiTro2;

    private String fileHopDong;

    private String srcHopDong;

    private String dieuKhoan;

    private Long idDonVi;

    private Long trangThai;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private String srcLoiChung;

    private Long idNhom;

    private String fileLoiChung;

    private Long chuyenTaiSan;

    private Long loaiSuaDoi;

    private Long loaiHuyBo;

    private Long trangThaiDuyet;

    private Long idPhanLoaiHopDong;

    private String srcCv;

    private String srcTb;

    private String srcTtpc;

    private String dgTen;

    private Long nhomTen;

    private Long idVaiTro3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoaiHd() {
        return idLoaiHd;
    }

    public void setIdLoaiHd(Long idLoaiHd) {
        this.idLoaiHd = idLoaiHd;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getIdVaiTro1() {
        return idVaiTro1;
    }

    public void setIdVaiTro1(Long idVaiTro1) {
        this.idVaiTro1 = idVaiTro1;
    }

    public Long getIdVaiTro2() {
        return idVaiTro2;
    }

    public void setIdVaiTro2(Long idVaiTro2) {
        this.idVaiTro2 = idVaiTro2;
    }

    public String getFileHopDong() {
        return fileHopDong;
    }

    public void setFileHopDong(String fileHopDong) {
        this.fileHopDong = fileHopDong;
    }

    public String getSrcHopDong() {
        return srcHopDong;
    }

    public void setSrcHopDong(String srcHopDong) {
        this.srcHopDong = srcHopDong;
    }

    public String getDieuKhoan() {
        return dieuKhoan;
    }

    public void setDieuKhoan(String dieuKhoan) {
        this.dieuKhoan = dieuKhoan;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public String getSrcLoiChung() {
        return srcLoiChung;
    }

    public void setSrcLoiChung(String srcLoiChung) {
        this.srcLoiChung = srcLoiChung;
    }

    public Long getIdNhom() {
        return idNhom;
    }

    public void setIdNhom(Long idNhom) {
        this.idNhom = idNhom;
    }

    public String getFileLoiChung() {
        return fileLoiChung;
    }

    public void setFileLoiChung(String fileLoiChung) {
        this.fileLoiChung = fileLoiChung;
    }

    public Long getChuyenTaiSan() {
        return chuyenTaiSan;
    }

    public void setChuyenTaiSan(Long chuyenTaiSan) {
        this.chuyenTaiSan = chuyenTaiSan;
    }

    public Long getLoaiSuaDoi() {
        return loaiSuaDoi;
    }

    public void setLoaiSuaDoi(Long loaiSuaDoi) {
        this.loaiSuaDoi = loaiSuaDoi;
    }

    public Long getLoaiHuyBo() {
        return loaiHuyBo;
    }

    public void setLoaiHuyBo(Long loaiHuyBo) {
        this.loaiHuyBo = loaiHuyBo;
    }

    public Long getTrangThaiDuyet() {
        return trangThaiDuyet;
    }

    public void setTrangThaiDuyet(Long trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public Long getIdPhanLoaiHopDong() {
        return idPhanLoaiHopDong;
    }

    public void setIdPhanLoaiHopDong(Long idPhanLoaiHopDong) {
        this.idPhanLoaiHopDong = idPhanLoaiHopDong;
    }

    public String getSrcCv() {
        return srcCv;
    }

    public void setSrcCv(String srcCv) {
        this.srcCv = srcCv;
    }

    public String getSrcTb() {
        return srcTb;
    }

    public void setSrcTb(String srcTb) {
        this.srcTb = srcTb;
    }

    public String getSrcTtpc() {
        return srcTtpc;
    }

    public void setSrcTtpc(String srcTtpc) {
        this.srcTtpc = srcTtpc;
    }

    public String getDgTen() {
        return dgTen;
    }

    public void setDgTen(String dgTen) {
        this.dgTen = dgTen;
    }

    public Long getNhomTen() {
        return nhomTen;
    }

    public void setNhomTen(Long nhomTen) {
        this.nhomTen = nhomTen;
    }

    public Long getIdVaiTro3() {
        return idVaiTro3;
    }

    public void setIdVaiTro3(Long idVaiTro3) {
        this.idVaiTro3 = idVaiTro3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiHopDongDTO)) {
            return false;
        }

        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = (DanhMucLoaiHopDongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhMucLoaiHopDongDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiHopDongDTO{" +
            "id=" + getId() +
            ", idLoaiHd=" + getIdLoaiHd() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idVaiTro1=" + getIdVaiTro1() +
            ", idVaiTro2=" + getIdVaiTro2() +
            ", fileHopDong='" + getFileHopDong() + "'" +
            ", srcHopDong='" + getSrcHopDong() + "'" +
            ", dieuKhoan='" + getDieuKhoan() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", trangThai=" + getTrangThai() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", srcLoiChung='" + getSrcLoiChung() + "'" +
            ", idNhom=" + getIdNhom() +
            ", fileLoiChung='" + getFileLoiChung() + "'" +
            ", chuyenTaiSan=" + getChuyenTaiSan() +
            ", loaiSuaDoi=" + getLoaiSuaDoi() +
            ", loaiHuyBo=" + getLoaiHuyBo() +
            ", trangThaiDuyet=" + getTrangThaiDuyet() +
            ", idPhanLoaiHopDong=" + getIdPhanLoaiHopDong() +
            ", srcCv='" + getSrcCv() + "'" +
            ", srcTb='" + getSrcTb() + "'" +
            ", srcTtpc='" + getSrcTtpc() + "'" +
            ", dgTen='" + getDgTen() + "'" +
            ", nhomTen=" + getNhomTen() +
            ", idVaiTro3=" + getIdVaiTro3() +
            "}";
    }
}
