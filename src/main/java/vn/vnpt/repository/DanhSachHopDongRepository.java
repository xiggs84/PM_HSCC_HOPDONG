package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhSachHopDong;

/**
 * Spring Data JPA repository for the DanhSachHopDong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhSachHopDongRepository extends JpaRepository<DanhSachHopDong, Long> {}
