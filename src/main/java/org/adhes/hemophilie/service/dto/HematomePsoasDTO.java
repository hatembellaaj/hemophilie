package org.adhes.hemophilie.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeManifestation;

/**
 * A DTO for the {@link org.adhes.hemophilie.domain.HematomePsoas} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HematomePsoasDTO implements Serializable {

    private Long id;

    private OuiNonNP reponse;

    private TypeManifestation type;

    private OuiNonNP recidive;

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

    public TypeManifestation getType() {
        return type;
    }

    public void setType(TypeManifestation type) {
        this.type = type;
    }

    public OuiNonNP getRecidive() {
        return recidive;
    }

    public void setRecidive(OuiNonNP recidive) {
        this.recidive = recidive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HematomePsoasDTO)) {
            return false;
        }

        HematomePsoasDTO hematomePsoasDTO = (HematomePsoasDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hematomePsoasDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HematomePsoasDTO{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", recidive='" + getRecidive() + "'" +
            "}";
    }
}
