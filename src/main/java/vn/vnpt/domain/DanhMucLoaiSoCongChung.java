package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucLoaiSoCongChung.
 */
@Entity
@Table(name = "danh_muc_loai_so_cong_chung")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiSoCongChung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_loai")
    private Long idLoai;

    @Column(name = "ten_loai")
    private String tenLoai;

    @Column(name = "trang_thai")
    private Long trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DanhMucLoaiSoCongChung id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoai() {
        return this.idLoai;
    }

    public DanhMucLoaiSoCongChung idLoai(Long idLoai) {
        this.setIdLoai(idLoai);
        return this;
    }

    public void setIdLoai(Long idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoai() {
        return this.tenLoai;
    }

    public DanhMucLoaiSoCongChung tenLoai(String tenLoai) {
        this.setTenLoai(tenLoai);
        return this;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucLoaiSoCongChung trangThai(Long trangThai) {
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
        if (!(o instanceof DanhMucLoaiSoCongChung)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucLoaiSoCongChung) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiSoCongChung{" +
            "id=" + getId() +
            ", idLoai=" + getIdLoai() +
            ", tenLoai='" + getTenLoai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
