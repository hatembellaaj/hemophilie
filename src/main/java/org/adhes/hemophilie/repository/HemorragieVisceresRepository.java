package org.adhes.hemophilie.repository;

import org.adhes.hemophilie.domain.HemorragieVisceres;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HemorragieVisceres entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HemorragieVisceresRepository extends JpaRepository<HemorragieVisceres, Long> {}
