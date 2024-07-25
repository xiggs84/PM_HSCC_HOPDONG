package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.ThongTinChungHopDong;

/**
 * Spring Data JPA repository for the ThongTinChungHopDong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongTinChungHopDongRepository extends JpaRepository<ThongTinChungHopDong, Long> {}
