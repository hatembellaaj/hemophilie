package org.adhes.hemophilie.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeHemorragieVisceres;

/**
 * A DTO for the {@link org.adhes.hemophilie.domain.HemorragieVisceres} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HemorragieVisceresDTO implements Serializable {

    private Long id;

    private OuiNonNP reponse;

    private TypeHemorragieVisceres type;

    private OuiNonNP exploration;

    private String examenLesion;

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

    public TypeHemorragieVisceres getType() {
        return type;
    }

    public void setType(TypeHemorragieVisceres type) {
        this.type = type;
    }

    public OuiNonNP getExploration() {
        return exploration;
    }

    public void setExploration(OuiNonNP exploration) {
        this.exploration = exploration;
    }

    public String getExamenLesion() {
        return examenLesion;
    }

    public void setExamenLesion(String examenLesion) {
        this.examenLesion = examenLesion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HemorragieVisceresDTO)) {
            return false;
        }

        HemorragieVisceresDTO hemorragieVisceresDTO = (HemorragieVisceresDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hemorragieVisceresDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HemorragieVisceresDTO{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", exploration='" + getExploration() + "'" +
            ", examenLesion='" + getExamenLesion() + "'" +
            "}";
    }
}
