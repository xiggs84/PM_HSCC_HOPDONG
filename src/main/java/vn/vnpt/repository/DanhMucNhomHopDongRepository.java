package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucNhomHopDong;

/**
 * Spring Data JPA repository for the DanhMucNhomHopDong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucNhomHopDongRepository extends JpaRepository<DanhMucNhomHopDong, Long> {}
