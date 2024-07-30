package vn.vnpt.repository;

import java.time.LocalDate;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhSachHopDong;

/**
 * Spring Data JPA repository for the DanhSachHopDong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhSachHopDongRepository extends JpaRepository<DanhSachHopDong, Long> {
    @Query("SELECT COUNT(d) FROM DanhSachHopDong d WHERE d.idDonVi = :idDonVi AND d.ngayLapHd = :ngayLapHd")
    long countByIdDonViAndNgayLapHd(@Param("idDonVi") Long idDonVi, @Param("ngayLapHd") LocalDate ngayLapHd);
}
