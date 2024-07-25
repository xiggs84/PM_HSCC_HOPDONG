package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucLoaiHopDong.
 */
@Entity
@Table(name = "danh_muc_loai_hop_dong")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiHopDong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_loai_hd")
    private Long idLoaiHd;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "id_vai_tro_1")
    private Long idVaiTro1;

    @Column(name = "id_vai_tro_2")
    private Long idVaiTro2;

    @Column(name = "file_hop_dong")
    private String fileHopDong;

    @Column(name = "src_hop_dong")
    private String srcHopDong;

    @Column(name = "dieu_khoan")
    private String dieuKhoan;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Column(name = "nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Column(name = "src_loi_chung")
    private String srcLoiChung;

    @Column(name = "id_nhom")
    private Long idNhom;

    @Column(name = "file_loi_chung")
    private String fileLoiChung;

    @Column(name = "chuyen_tai_san")
    private Long chuyenTaiSan;

    @Column(name = "loai_sua_doi")
    private Long loaiSuaDoi;

    @Column(name = "loai_huy_bo")
    private Long loaiHuyBo;

    @Column(name = "trang_thai_duyet")
    private Long trangThaiDuyet;

    @Column(name = "id_phan_loai_hop_dong")
    private Long idPhanLoaiHopDong;

    @Column(name = "src_cv")
    private String srcCv;

    @Column(name = "src_tb")
    private String srcTb;

    @Column(name = "src_ttpc")
    private String srcTtpc;

    @Column(name = "dg_ten")
    private String dgTen;

    @Column(name = "nhom_ten")
    private Long nhomTen;

    @Column(name = "id_vai_tro_3")
    private Long idVaiTro3;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DanhMucLoaiHopDong id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoaiHd() {
        return this.idLoaiHd;
    }

    public DanhMucLoaiHopDong idLoaiHd(Long idLoaiHd) {
        this.setIdLoaiHd(idLoaiHd);
        return this;
    }

    public void setIdLoaiHd(Long idLoaiHd) {
        this.idLoaiHd = idLoaiHd;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucLoaiHopDong dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getIdVaiTro1() {
        return this.idVaiTro1;
    }

    public DanhMucLoaiHopDong idVaiTro1(Long idVaiTro1) {
        this.setIdVaiTro1(idVaiTro1);
        return this;
    }

    public void setIdVaiTro1(Long idVaiTro1) {
        this.idVaiTro1 = idVaiTro1;
    }

    public Long getIdVaiTro2() {
        return this.idVaiTro2;
    }

    public DanhMucLoaiHopDong idVaiTro2(Long idVaiTro2) {
        this.setIdVaiTro2(idVaiTro2);
        return this;
    }

    public void setIdVaiTro2(Long idVaiTro2) {
        this.idVaiTro2 = idVaiTro2;
    }

    public String getFileHopDong() {
        return this.fileHopDong;
    }

    public DanhMucLoaiHopDong fileHopDong(String fileHopDong) {
        this.setFileHopDong(fileHopDong);
        return this;
    }

    public void setFileHopDong(String fileHopDong) {
        this.fileHopDong = fileHopDong;
    }

    public String getSrcHopDong() {
        return this.srcHopDong;
    }

    public DanhMucLoaiHopDong srcHopDong(String srcHopDong) {
        this.setSrcHopDong(srcHopDong);
        return this;
    }

    public void setSrcHopDong(String srcHopDong) {
        this.srcHopDong = srcHopDong;
    }

    public String getDieuKhoan() {
        return this.dieuKhoan;
    }

    public DanhMucLoaiHopDong dieuKhoan(String dieuKhoan) {
        this.setDieuKhoan(dieuKhoan);
        return this;
    }

    public void setDieuKhoan(String dieuKhoan) {
        this.dieuKhoan = dieuKhoan;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public DanhMucLoaiHopDong idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucLoaiHopDong trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public DanhMucLoaiHopDong ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public DanhMucLoaiHopDong nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public String getSrcLoiChung() {
        return this.srcLoiChung;
    }

    public DanhMucLoaiHopDong srcLoiChung(String srcLoiChung) {
        this.setSrcLoiChung(srcLoiChung);
        return this;
    }

    public void setSrcLoiChung(String srcLoiChung) {
        this.srcLoiChung = srcLoiChung;
    }

    public Long getIdNhom() {
        return this.idNhom;
    }

    public DanhMucLoaiHopDong idNhom(Long idNhom) {
        this.setIdNhom(idNhom);
        return this;
    }

    public void setIdNhom(Long idNhom) {
        this.idNhom = idNhom;
    }

    public String getFileLoiChung() {
        return this.fileLoiChung;
    }

    public DanhMucLoaiHopDong fileLoiChung(String fileLoiChung) {
        this.setFileLoiChung(fileLoiChung);
        return this;
    }

    public void setFileLoiChung(String fileLoiChung) {
        this.fileLoiChung = fileLoiChung;
    }

    public Long getChuyenTaiSan() {
        return this.chuyenTaiSan;
    }

    public DanhMucLoaiHopDong chuyenTaiSan(Long chuyenTaiSan) {
        this.setChuyenTaiSan(chuyenTaiSan);
        return this;
    }

    public void setChuyenTaiSan(Long chuyenTaiSan) {
        this.chuyenTaiSan = chuyenTaiSan;
    }

    public Long getLoaiSuaDoi() {
        return this.loaiSuaDoi;
    }

    public DanhMucLoaiHopDong loaiSuaDoi(Long loaiSuaDoi) {
        this.setLoaiSuaDoi(loaiSuaDoi);
        return this;
    }

    public void setLoaiSuaDoi(Long loaiSuaDoi) {
        this.loaiSuaDoi = loaiSuaDoi;
    }

    public Long getLoaiHuyBo() {
        return this.loaiHuyBo;
    }

    public DanhMucLoaiHopDong loaiHuyBo(Long loaiHuyBo) {
        this.setLoaiHuyBo(loaiHuyBo);
        return this;
    }

    public void setLoaiHuyBo(Long loaiHuyBo) {
        this.loaiHuyBo = loaiHuyBo;
    }

    public Long getTrangThaiDuyet() {
        return this.trangThaiDuyet;
    }

    public DanhMucLoaiHopDong trangThaiDuyet(Long trangThaiDuyet) {
        this.setTrangThaiDuyet(trangThaiDuyet);
        return this;
    }

    public void setTrangThaiDuyet(Long trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public Long getIdPhanLoaiHopDong() {
        return this.idPhanLoaiHopDong;
    }

    public DanhMucLoaiHopDong idPhanLoaiHopDong(Long idPhanLoaiHopDong) {
        this.setIdPhanLoaiHopDong(idPhanLoaiHopDong);
        return this;
    }

    public void setIdPhanLoaiHopDong(Long idPhanLoaiHopDong) {
        this.idPhanLoaiHopDong = idPhanLoaiHopDong;
    }

    public String getSrcCv() {
        return this.srcCv;
    }

    public DanhMucLoaiHopDong srcCv(String srcCv) {
        this.setSrcCv(srcCv);
        return this;
    }

    public void setSrcCv(String srcCv) {
        this.srcCv = srcCv;
    }

    public String getSrcTb() {
        return this.srcTb;
    }

    public DanhMucLoaiHopDong srcTb(String srcTb) {
        this.setSrcTb(srcTb);
        return this;
    }

    public void setSrcTb(String srcTb) {
        this.srcTb = srcTb;
    }

    public String getSrcTtpc() {
        return this.srcTtpc;
    }

    public DanhMucLoaiHopDong srcTtpc(String srcTtpc) {
        this.setSrcTtpc(srcTtpc);
        return this;
    }

    public void setSrcTtpc(String srcTtpc) {
        this.srcTtpc = srcTtpc;
    }

    public String getDgTen() {
        return this.dgTen;
    }

    public DanhMucLoaiHopDong dgTen(String dgTen) {
        this.setDgTen(dgTen);
        return this;
    }

    public void setDgTen(String dgTen) {
        this.dgTen = dgTen;
    }

    public Long getNhomTen() {
        return this.nhomTen;
    }

    public DanhMucLoaiHopDong nhomTen(Long nhomTen) {
        this.setNhomTen(nhomTen);
        return this;
    }

    public void setNhomTen(Long nhomTen) {
        this.nhomTen = nhomTen;
    }

    public Long getIdVaiTro3() {
        return this.idVaiTro3;
    }

    public DanhMucLoaiHopDong idVaiTro3(Long idVaiTro3) {
        this.setIdVaiTro3(idVaiTro3);
        return this;
    }

    public void setIdVaiTro3(Long idVaiTro3) {
        this.idVaiTro3 = idVaiTro3;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiHopDong)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucLoaiHopDong) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiHopDong{" +
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
