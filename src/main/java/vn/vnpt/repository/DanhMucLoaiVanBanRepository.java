package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucLoaiVanBan;

/**
 * Spring Data JPA repository for the DanhMucLoaiVanBan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucLoaiVanBanRepository extends JpaRepository<DanhMucLoaiVanBan, Long> {}
