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

    long countByIdDonViAndNgayLapHdBetween(Long idDonVi, LocalDate startDate, LocalDate endDate);

    @Query("SELECT COUNT(d) FROM DanhSachHopDong d WHERE MONTH(d.ngayLapHd) = :month AND YEAR(d.ngayLapHd) = :year")
    long countByMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT COUNT(d) FROM DanhSachHopDong d WHERE MONTH(d.ngayLapHd) = :month AND YEAR(d.ngayLapHd) = :year AND d.idDonVi = :idDonVi")
    Long countByMonthAndIdDonVi(@Param("month") int month, @Param("year") int year, @Param("idDonVi") Long idDonVi);
}

