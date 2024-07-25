package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LoaiHopDongCongChung.
 */
@Entity
@Table(name = "loai_hop_dong_cong_chung")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiHopDongCongChung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_loai_hop_dong_cong_chung")
    private Long idLoaiHopDongCongChung;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "gia_tri")
    private Long giaTri;

    @Column(name = "trang_thai")
    private Long trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public LoaiHopDongCongChung id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoaiHopDongCongChung() {
        return this.idLoaiHopDongCongChung;
    }

    public LoaiHopDongCongChung idLoaiHopDongCongChung(Long idLoaiHopDongCongChung) {
        this.setIdLoaiHopDongCongChung(idLoaiHopDongCongChung);
        return this;
    }

    public void setIdLoaiHopDongCongChung(Long idLoaiHopDongCongChung) {
        this.idLoaiHopDongCongChung = idLoaiHopDongCongChung;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public LoaiHopDongCongChung dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getGiaTri() {
        return this.giaTri;
    }

    public LoaiHopDongCongChung giaTri(Long giaTri) {
        this.setGiaTri(giaTri);
        return this;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public LoaiHopDongCongChung trangThai(Long trangThai) {
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
        if (!(o instanceof LoaiHopDongCongChung)) {
            return false;
        }
        return getId() != null && getId().equals(((LoaiHopDongCongChung) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiHopDongCongChung{" +
            "id=" + getId() +
            ", idLoaiHopDongCongChung=" + getIdLoaiHopDongCongChung() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", giaTri=" + getGiaTri() +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
