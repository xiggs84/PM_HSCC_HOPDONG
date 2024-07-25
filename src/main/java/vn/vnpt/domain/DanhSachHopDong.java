package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhSachHopDong.
 */
@Entity
@Table(name = "danh_sach_hop_dong")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhSachHopDong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ngay_lap_hd")
    private LocalDate ngayLapHd;

    @Column(name = "nguoi_lap_hd")
    private Long nguoiLapHd;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "id_loai_hd")
    private Long idLoaiHd;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Column(name = "nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Column(name = "src_hop_dong")
    private String srcHopDong;

    @Column(name = "so_cong_chung")
    private String soCongChung;

    @Column(name = "cong_chung_vien")
    private Long congChungVien;

    @Column(name = "so_tien_rut_trich")
    private Long soTienRutTrich;

    @Column(name = "hd_thu_cong")
    private Long hdThuCong;

    @Column(name = "trang_thai_rut_trich")
    private Long trangThaiRutTrich;

    @Column(name = "chu_ky_ngoai_tru_so")
    private Long chuKyNgoaiTruSo;

    @Column(name = "str_search")
    private String strSearch;

    @Column(name = "ngay_text")
    private String ngayText;

    @Column(name = "ngay_rut_trich_text")
    private String ngayRutTrichText;

    @Column(name = "ngay_thao_tac_rut_trich")
    private LocalDate ngayThaoTacRutTrich;

    @Column(name = "thu_lao_cong_chung")
    private Long thuLaoCongChung;

    @Column(name = "quyen_lai_st")
    private String quyenLaiSt;

    @Column(name = "so_lai_st")
    private String soLaiSt;

    @Column(name = "quyen_lai_tl")
    private String quyenLaiTl;

    @Column(name = "so_lai_tl")
    private String soLaiTl;

    @Column(name = "src_ky_so_pdf")
    private String srcKySoPdf;

    @Column(name = "src_ky_so_pdf_signed")
    private String srcKySoPdfSigned;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DanhSachHopDong id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayLapHd() {
        return this.ngayLapHd;
    }

    public DanhSachHopDong ngayLapHd(LocalDate ngayLapHd) {
        this.setNgayLapHd(ngayLapHd);
        return this;
    }

    public void setNgayLapHd(LocalDate ngayLapHd) {
        this.ngayLapHd = ngayLapHd;
    }

    public Long getNguoiLapHd() {
        return this.nguoiLapHd;
    }

    public DanhSachHopDong nguoiLapHd(Long nguoiLapHd) {
        this.setNguoiLapHd(nguoiLapHd);
        return this;
    }

    public void setNguoiLapHd(Long nguoiLapHd) {
        this.nguoiLapHd = nguoiLapHd;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhSachHopDong trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getIdLoaiHd() {
        return this.idLoaiHd;
    }

    public DanhSachHopDong idLoaiHd(Long idLoaiHd) {
        this.setIdLoaiHd(idLoaiHd);
        return this;
    }

    public void setIdLoaiHd(Long idLoaiHd) {
        this.idLoaiHd = idLoaiHd;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public DanhSachHopDong idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public DanhSachHopDong ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public DanhSachHopDong nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public String getSrcHopDong() {
        return this.srcHopDong;
    }

    public DanhSachHopDong srcHopDong(String srcHopDong) {
        this.setSrcHopDong(srcHopDong);
        return this;
    }

    public void setSrcHopDong(String srcHopDong) {
        this.srcHopDong = srcHopDong;
    }

    public String getSoCongChung() {
        return this.soCongChung;
    }

    public DanhSachHopDong soCongChung(String soCongChung) {
        this.setSoCongChung(soCongChung);
        return this;
    }

    public void setSoCongChung(String soCongChung) {
        this.soCongChung = soCongChung;
    }

    public Long getCongChungVien() {
        return this.congChungVien;
    }

    public DanhSachHopDong congChungVien(Long congChungVien) {
        this.setCongChungVien(congChungVien);
        return this;
    }

    public void setCongChungVien(Long congChungVien) {
        this.congChungVien = congChungVien;
    }

    public Long getSoTienRutTrich() {
        return this.soTienRutTrich;
    }

    public DanhSachHopDong soTienRutTrich(Long soTienRutTrich) {
        this.setSoTienRutTrich(soTienRutTrich);
        return this;
    }

    public void setSoTienRutTrich(Long soTienRutTrich) {
        this.soTienRutTrich = soTienRutTrich;
    }

    public Long getHdThuCong() {
        return this.hdThuCong;
    }

    public DanhSachHopDong hdThuCong(Long hdThuCong) {
        this.setHdThuCong(hdThuCong);
        return this;
    }

    public void setHdThuCong(Long hdThuCong) {
        this.hdThuCong = hdThuCong;
    }

    public Long getTrangThaiRutTrich() {
        return this.trangThaiRutTrich;
    }

    public DanhSachHopDong trangThaiRutTrich(Long trangThaiRutTrich) {
        this.setTrangThaiRutTrich(trangThaiRutTrich);
        return this;
    }

    public void setTrangThaiRutTrich(Long trangThaiRutTrich) {
        this.trangThaiRutTrich = trangThaiRutTrich;
    }

    public Long getChuKyNgoaiTruSo() {
        return this.chuKyNgoaiTruSo;
    }

    public DanhSachHopDong chuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.setChuKyNgoaiTruSo(chuKyNgoaiTruSo);
        return this;
    }

    public void setChuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.chuKyNgoaiTruSo = chuKyNgoaiTruSo;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public DanhSachHopDong strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public String getNgayText() {
        return this.ngayText;
    }

    public DanhSachHopDong ngayText(String ngayText) {
        this.setNgayText(ngayText);
        return this;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public String getNgayRutTrichText() {
        return this.ngayRutTrichText;
    }

    public DanhSachHopDong ngayRutTrichText(String ngayRutTrichText) {
        this.setNgayRutTrichText(ngayRutTrichText);
        return this;
    }

    public void setNgayRutTrichText(String ngayRutTrichText) {
        this.ngayRutTrichText = ngayRutTrichText;
    }

    public LocalDate getNgayThaoTacRutTrich() {
        return this.ngayThaoTacRutTrich;
    }

    public DanhSachHopDong ngayThaoTacRutTrich(LocalDate ngayThaoTacRutTrich) {
        this.setNgayThaoTacRutTrich(ngayThaoTacRutTrich);
        return this;
    }

    public void setNgayThaoTacRutTrich(LocalDate ngayThaoTacRutTrich) {
        this.ngayThaoTacRutTrich = ngayThaoTacRutTrich;
    }

    public Long getThuLaoCongChung() {
        return this.thuLaoCongChung;
    }

    public DanhSachHopDong thuLaoCongChung(Long thuLaoCongChung) {
        this.setThuLaoCongChung(thuLaoCongChung);
        return this;
    }

    public void setThuLaoCongChung(Long thuLaoCongChung) {
        this.thuLaoCongChung = thuLaoCongChung;
    }

    public String getQuyenLaiSt() {
        return this.quyenLaiSt;
    }

    public DanhSachHopDong quyenLaiSt(String quyenLaiSt) {
        this.setQuyenLaiSt(quyenLaiSt);
        return this;
    }

    public void setQuyenLaiSt(String quyenLaiSt) {
        this.quyenLaiSt = quyenLaiSt;
    }

    public String getSoLaiSt() {
        return this.soLaiSt;
    }

    public DanhSachHopDong soLaiSt(String soLaiSt) {
        this.setSoLaiSt(soLaiSt);
        return this;
    }

    public void setSoLaiSt(String soLaiSt) {
        this.soLaiSt = soLaiSt;
    }

    public String getQuyenLaiTl() {
        return this.quyenLaiTl;
    }

    public DanhSachHopDong quyenLaiTl(String quyenLaiTl) {
        this.setQuyenLaiTl(quyenLaiTl);
        return this;
    }

    public void setQuyenLaiTl(String quyenLaiTl) {
        this.quyenLaiTl = quyenLaiTl;
    }

    public String getSoLaiTl() {
        return this.soLaiTl;
    }

    public DanhSachHopDong soLaiTl(String soLaiTl) {
        this.setSoLaiTl(soLaiTl);
        return this;
    }

    public void setSoLaiTl(String soLaiTl) {
        this.soLaiTl = soLaiTl;
    }

    public String getSrcKySoPdf() {
        return this.srcKySoPdf;
    }

    public DanhSachHopDong srcKySoPdf(String srcKySoPdf) {
        this.setSrcKySoPdf(srcKySoPdf);
        return this;
    }

    public void setSrcKySoPdf(String srcKySoPdf) {
        this.srcKySoPdf = srcKySoPdf;
    }

    public String getSrcKySoPdfSigned() {
        return this.srcKySoPdfSigned;
    }

    public DanhSachHopDong srcKySoPdfSigned(String srcKySoPdfSigned) {
        this.setSrcKySoPdfSigned(srcKySoPdfSigned);
        return this;
    }

    public void setSrcKySoPdfSigned(String srcKySoPdfSigned) {
        this.srcKySoPdfSigned = srcKySoPdfSigned;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhSachHopDong)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhSachHopDong) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhSachHopDong{" +
            "id=" + getId() +
            ", ngayLapHd='" + getNgayLapHd() + "'" +
            ", nguoiLapHd=" + getNguoiLapHd() +
            ", trangThai=" + getTrangThai() +
            ", idLoaiHd=" + getIdLoaiHd() +
            ", idDonVi=" + getIdDonVi() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", srcHopDong='" + getSrcHopDong() + "'" +
            ", soCongChung='" + getSoCongChung() + "'" +
            ", congChungVien=" + getCongChungVien() +
            ", soTienRutTrich=" + getSoTienRutTrich() +
            ", hdThuCong=" + getHdThuCong() +
            ", trangThaiRutTrich=" + getTrangThaiRutTrich() +
            ", chuKyNgoaiTruSo=" + getChuKyNgoaiTruSo() +
            ", strSearch='" + getStrSearch() + "'" +
            ", ngayText='" + getNgayText() + "'" +
            ", ngayRutTrichText='" + getNgayRutTrichText() + "'" +
            ", ngayThaoTacRutTrich='" + getNgayThaoTacRutTrich() + "'" +
            ", thuLaoCongChung=" + getThuLaoCongChung() +
            ", quyenLaiSt='" + getQuyenLaiSt() + "'" +
            ", soLaiSt='" + getSoLaiSt() + "'" +
            ", quyenLaiTl='" + getQuyenLaiTl() + "'" +
            ", soLaiTl='" + getSoLaiTl() + "'" +
            ", srcKySoPdf='" + getSrcKySoPdf() + "'" +
            ", srcKySoPdfSigned='" + getSrcKySoPdfSigned() + "'" +
            "}";
    }
}
