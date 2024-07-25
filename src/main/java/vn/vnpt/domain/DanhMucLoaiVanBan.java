package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucLoaiVanBan.
 */
@Entity
@Table(name = "danh_muc_loai_van_ban")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiVanBan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_loai_vb")
    private Long idLoaiVb;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "id_loai_hop_dong")
    private Long idLoaiHopDong;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DanhMucLoaiVanBan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoaiVb() {
        return this.idLoaiVb;
    }

    public DanhMucLoaiVanBan idLoaiVb(Long idLoaiVb) {
        this.setIdLoaiVb(idLoaiVb);
        return this;
    }

    public void setIdLoaiVb(Long idLoaiVb) {
        this.idLoaiVb = idLoaiVb;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucLoaiVanBan dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getIdLoaiHopDong() {
        return this.idLoaiHopDong;
    }

    public DanhMucLoaiVanBan idLoaiHopDong(Long idLoaiHopDong) {
        this.setIdLoaiHopDong(idLoaiHopDong);
        return this;
    }

    public void setIdLoaiHopDong(Long idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiVanBan)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucLoaiVanBan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiVanBan{" +
            "id=" + getId() +
            ", idLoaiVb=" + getIdLoaiVb() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idLoaiHopDong=" + getIdLoaiHopDong() +
            "}";
    }
}
