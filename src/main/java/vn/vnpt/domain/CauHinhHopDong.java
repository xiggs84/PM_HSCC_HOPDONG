package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CauHinhHopDong.
 */
@Entity
@Table(name = "cau_hinh_hop_dong")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CauHinhHopDong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_loai_hop_dong")
    private Long idLoaiHopDong;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "chieu_dai")
    private Long chieuDai;

    @Column(name = "tien_to")
    private String tienTo;

    @Column(name = "phuong_thuc_thanh_toan")
    private String phuongThucThanhToan;

    @Column(name = "thong_tin_them")
    private String thongTinThem;

    @Column(name = "gia_tri")
    private Long giaTri;

    @Column(name = "hien_thi")
    private String hienThi;

    @Column(name = "trang_thai")
    private Long trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CauHinhHopDong id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoaiHopDong() {
        return this.idLoaiHopDong;
    }

    public CauHinhHopDong idLoaiHopDong(Long idLoaiHopDong) {
        this.setIdLoaiHopDong(idLoaiHopDong);
        return this;
    }

    public void setIdLoaiHopDong(Long idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public CauHinhHopDong idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getChieuDai() {
        return this.chieuDai;
    }

    public CauHinhHopDong chieuDai(Long chieuDai) {
        this.setChieuDai(chieuDai);
        return this;
    }

    public void setChieuDai(Long chieuDai) {
        this.chieuDai = chieuDai;
    }

    public String getTienTo() {
        return this.tienTo;
    }

    public CauHinhHopDong tienTo(String tienTo) {
        this.setTienTo(tienTo);
        return this;
    }

    public void setTienTo(String tienTo) {
        this.tienTo = tienTo;
    }

    public String getPhuongThucThanhToan() {
        return this.phuongThucThanhToan;
    }

    public CauHinhHopDong phuongThucThanhToan(String phuongThucThanhToan) {
        this.setPhuongThucThanhToan(phuongThucThanhToan);
        return this;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getThongTinThem() {
        return this.thongTinThem;
    }

    public CauHinhHopDong thongTinThem(String thongTinThem) {
        this.setThongTinThem(thongTinThem);
        return this;
    }

    public void setThongTinThem(String thongTinThem) {
        this.thongTinThem = thongTinThem;
    }

    public Long getGiaTri() {
        return this.giaTri;
    }

    public CauHinhHopDong giaTri(Long giaTri) {
        this.setGiaTri(giaTri);
        return this;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
    }

    public String getHienThi() {
        return this.hienThi;
    }

    public CauHinhHopDong hienThi(String hienThi) {
        this.setHienThi(hienThi);
        return this;
    }

    public void setHienThi(String hienThi) {
        this.hienThi = hienThi;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public CauHinhHopDong trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHinhHopDong)) {
            return false;
        }
        return getId() != null && getId().equals(((CauHinhHopDong) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHinhHopDong{" +
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
