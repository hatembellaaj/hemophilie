package org.adhes.hemophilie.repository;

import org.adhes.hemophilie.domain.Hemarthrose;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Hemarthrose entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HemarthroseRepository extends JpaRepository<Hemarthrose, Long> {}
