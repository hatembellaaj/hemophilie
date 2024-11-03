package org.adhes.hemophilie.repository;

import org.adhes.hemophilie.domain.SaignementSNC;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SaignementSNC entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SaignementSNCRepository extends JpaRepository<SaignementSNC, Long> {}
