package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.PhanLoaiHopDong;

/**
 * Spring Data JPA repository for the PhanLoaiHopDong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhanLoaiHopDongRepository extends JpaRepository<PhanLoaiHopDong, Long> {}
