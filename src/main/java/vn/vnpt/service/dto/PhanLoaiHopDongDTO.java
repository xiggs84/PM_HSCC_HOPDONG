package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.PhanLoaiHopDong} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PhanLoaiHopDongDTO implements Serializable {

    private Long id;

    private Long idPhanLoaiHopDong;

    private String dienGiai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPhanLoaiHopDong() {
        return idPhanLoaiHopDong;
    }

    public void setIdPhanLoaiHopDong(Long idPhanLoaiHopDong) {
        this.idPhanLoaiHopDong = idPhanLoaiHopDong;
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
        if (!(o instanceof PhanLoaiHopDongDTO)) {
            return false;
        }

        PhanLoaiHopDongDTO phanLoaiHopDongDTO = (PhanLoaiHopDongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, phanLoaiHopDongDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhanLoaiHopDongDTO{" +
            "id=" + getId() +
            ", idPhanLoaiHopDong=" + getIdPhanLoaiHopDong() +
            ", dienGiai='" + getDienGiai() + "'" +
            "}";
    }
}
