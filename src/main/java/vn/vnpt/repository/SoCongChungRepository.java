package vn.vnpt.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.vnpt.domain.SoCongChung;

/**
 * Spring Data JPA repository for the SoCongChung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoCongChungRepository extends JpaRepository<SoCongChung, Long> {}
