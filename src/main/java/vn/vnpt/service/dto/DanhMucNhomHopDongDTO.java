package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucNhomHopDong} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucNhomHopDongDTO implements Serializable {

    private Long id;

    private Long idNhom;

    private String dienGiai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdNhom() {
        return idNhom;
    }

    public void setIdNhom(Long idNhom) {
        this.idNhom = idNhom;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucNhomHopDongDTO)) {
            return false;
        }

        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = (DanhMucNhomHopDongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhMucNhomHopDongDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucNhomHopDongDTO{" +
            "id=" + getId() +
            ", idNhom=" + getIdNhom() +
            ", dienGiai='" + getDienGiai() + "'" +
            "}";
    }
}
