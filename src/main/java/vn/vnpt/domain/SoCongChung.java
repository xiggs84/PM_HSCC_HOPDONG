package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SoCongChung.
 */
@Entity
@Table(name = "so_cong_chung")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoCongChung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Column(name = "id_so")
    private Long idSo;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "ten_so")
    private String tenSo;

    @Column(name = "gia_tri")
    private Long giaTri;

    @Column(name = "nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "id_loai_so")
    private Long idLoaiSo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SoCongChung id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public SoCongChung ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getIdSo() {
        return this.idSo;
    }

    public SoCongChung idSo(Long idSo) {
        this.setIdSo(idSo);
        return this;
    }

    public void setIdSo(Long idSo) {
        this.idSo = idSo;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public SoCongChung idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getTenSo() {
        return this.tenSo;
    }

    public SoCongChung tenSo(String tenSo) {
        this.setTenSo(tenSo);
        return this;
    }

    public void setTenSo(String tenSo) {
        this.tenSo = tenSo;
    }

    public Long getGiaTri() {
        return this.giaTri;
    }

    public SoCongChung giaTri(Long giaTri) {
        this.setGiaTri(giaTri);
        return this;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public SoCongChung nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public SoCongChung trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getIdLoaiSo() {
        return this.idLoaiSo;
    }

    public SoCongChung idLoaiSo(Long idLoaiSo) {
        this.setIdLoaiSo(idLoaiSo);
        return this;
    }

    public void setIdLoaiSo(Long idLoaiSo) {
        this.idLoaiSo = idLoaiSo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoCongChung)) {
            return false;
        }
        return getId() != null && getId().equals(((SoCongChung) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoCongChung{" +
            "id=" + getId() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", idSo=" + getIdSo() +
            ", idDonVi=" + getIdDonVi() +
            ", tenSo='" + getTenSo() + "'" +
            ", giaTri=" + getGiaTri() +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", trangThai=" + getTrangThai() +
            ", idLoaiSo=" + getIdLoaiSo() +
            "}";
    }
}
