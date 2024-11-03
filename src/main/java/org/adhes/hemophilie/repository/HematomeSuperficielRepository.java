package org.adhes.hemophilie.repository;

import org.adhes.hemophilie.domain.HematomeSuperficiel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HematomeSuperficiel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HematomeSuperficielRepository extends JpaRepository<HematomeSuperficiel, Long> {}
