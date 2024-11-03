package org.adhes.hemophilie.repository;

import org.adhes.hemophilie.domain.HematomePsoas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HematomePsoas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HematomePsoasRepository extends JpaRepository<HematomePsoas, Long> {}
