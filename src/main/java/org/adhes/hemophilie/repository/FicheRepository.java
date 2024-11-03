package org.adhes.hemophilie.repository;

import java.util.List;
import org.adhes.hemophilie.domain.Fiche;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Fiche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheRepository extends JpaRepository<Fiche, Long> {
    @Query("select fiche from Fiche fiche where fiche.user.login = ?#{authentication.name}")
    List<Fiche> findByUserIsCurrentUser();
}
