package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.CauHinhHopDong;

/**
 * Spring Data JPA repository for the CauHinhHopDong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CauHinhHopDongRepository extends JpaRepository<CauHinhHopDong, Long> {}
