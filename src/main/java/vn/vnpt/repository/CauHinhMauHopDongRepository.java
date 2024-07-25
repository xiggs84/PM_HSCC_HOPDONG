package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.CauHinhMauHopDong;

/**
 * Spring Data JPA repository for the CauHinhMauHopDong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CauHinhMauHopDongRepository extends JpaRepository<CauHinhMauHopDong, Long> {}
