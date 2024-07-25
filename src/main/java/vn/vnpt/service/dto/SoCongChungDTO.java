package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.SoCongChung} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoCongChungDTO implements Serializable {

    private Long id;

    private LocalDate ngayThaoTac;

    private Long idSo;

    private Long idDonVi;

    private String tenSo;

    private Long giaTri;

    private Long nguoiThaoTac;

    private Long trangThai;

    private Long idLoaiSo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getIdSo() {
        return idSo;
    }

    public void setIdSo(Long idSo) {
        this.idSo = idSo;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getTenSo() {
        return tenSo;
    }

    public void setTenSo(String tenSo) {
        this.tenSo = tenSo;
    }

    public Long getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getIdLoaiSo() {
        return idLoaiSo;
    }

    public void setIdLoaiSo(Long idLoaiSo) {
        this.idLoaiSo = idLoaiSo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoCongChungDTO)) {
            return false;
        }

        SoCongChungDTO soCongChungDTO = (SoCongChungDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, soCongChungDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoCongChungDTO{" +
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
