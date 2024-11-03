package org.adhes.hemophilie.repository;

import org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueuses;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HemorragiesCutaneoMuqueuses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HemorragiesCutaneoMuqueusesRepository extends JpaRepository<HemorragiesCutaneoMuqueuses, Long> {}
