package org.adhes.hemophilie.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.adhes.hemophilie.domain.enumeration.EvolutionSNC;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;

/**
 * A DTO for the {@link org.adhes.hemophilie.domain.SaignementSNC} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SaignementSNCDTO implements Serializable {

    private Long id;

    private OuiNonNP reponse;

    private EvolutionSNC evolution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OuiNonNP getReponse() {
        return reponse;
    }

    public void setReponse(OuiNonNP reponse) {
        this.reponse = reponse;
    }

    public EvolutionSNC getEvolution() {
        return evolution;
    }

    public void setEvolution(EvolutionSNC evolution) {
        this.evolution = evolution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SaignementSNCDTO)) {
            return false;
        }

        SaignementSNCDTO saignementSNCDTO = (SaignementSNCDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, saignementSNCDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SaignementSNCDTO{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", evolution='" + getEvolution() + "'" +
            "}";
    }
}
