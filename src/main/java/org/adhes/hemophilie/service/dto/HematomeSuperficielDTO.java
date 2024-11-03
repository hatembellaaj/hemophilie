package org.adhes.hemophilie.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeManifestation;

/**
 * A DTO for the {@link org.adhes.hemophilie.domain.HematomeSuperficiel} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HematomeSuperficielDTO implements Serializable {

    private Long id;

    private OuiNonNP reponse;

    private TypeManifestation type;

    private String siege;

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

    public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HematomeSuperficielDTO)) {
            return false;
        }

        HematomeSuperficielDTO hematomeSuperficielDTO = (HematomeSuperficielDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hematomeSuperficielDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HematomeSuperficielDTO{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", siege='" + getSiege() + "'" +
            "}";
    }
}
