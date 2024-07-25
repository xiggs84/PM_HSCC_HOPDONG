package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucLoaiHopDong;

/**
 * Spring Data JPA repository for the DanhMucLoaiHopDong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucLoaiHopDongRepository extends JpaRepository<DanhMucLoaiHopDong, Long> {}
