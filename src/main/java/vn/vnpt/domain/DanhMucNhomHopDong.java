package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucNhomHopDong.
 */
@Entity
@Table(name = "danh_muc_nhom_hop_dong")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucNhomHopDong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_nhom")
    private Long idNhom;

    @Column(name = "dien_giai")
    private String dienGiai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DanhMucNhomHopDong id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdNhom() {
        return this.idNhom;
    }

    public DanhMucNhomHopDong idNhom(Long idNhom) {
        this.setIdNhom(idNhom);
        return this;
    }

    public void setIdNhom(Long idNhom) {
        this.idNhom = idNhom;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucNhomHopDong dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucNhomHopDong)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucNhomHopDong) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucNhomHopDong{" +
            "id=" + getId() +
            ", idNhom=" + getIdNhom() +
            ", dienGiai='" + getDienGiai() + "'" +
            "}";
    }
}
