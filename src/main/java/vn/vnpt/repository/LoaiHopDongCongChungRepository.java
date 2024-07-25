package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.LoaiHopDongCongChung;

/**
 * Spring Data JPA repository for the LoaiHopDongCongChung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiHopDongCongChungRepository extends JpaRepository<LoaiHopDongCongChung, Long> {}
