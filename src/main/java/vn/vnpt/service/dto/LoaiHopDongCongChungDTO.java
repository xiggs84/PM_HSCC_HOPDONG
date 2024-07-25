package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.LoaiHopDongCongChung} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiHopDongCongChungDTO implements Serializable {

    private Long id;

    private Long idLoaiHopDongCongChung;

    private String dienGiai;

    private Long giaTri;

    private Long trangThai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoaiHopDongCongChung() {
        return idLoaiHopDongCongChung;
    }

    public void setIdLoaiHopDongCongChung(Long idLoaiHopDongCongChung) {
        this.idLoaiHopDongCongChung = idLoaiHopDongCongChung;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
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
        if (!(o instanceof LoaiHopDongCongChungDTO)) {
            return false;
        }

        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = (LoaiHopDongCongChungDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, loaiHopDongCongChungDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiHopDongCongChungDTO{" +
            "id=" + getId() +
            ", idLoaiHopDongCongChung=" + getIdLoaiHopDongCongChung() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", giaTri=" + getGiaTri() +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
