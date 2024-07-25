package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.DanhMucLoaiSoCongChung;

/**
 * Spring Data JPA repository for the DanhMucLoaiSoCongChung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DanhMucLoaiSoCongChungRepository extends JpaRepository<DanhMucLoaiSoCongChung, Long> {}
