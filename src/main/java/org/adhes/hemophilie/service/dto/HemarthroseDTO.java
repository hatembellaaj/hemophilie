package org.adhes.hemophilie.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeManifestation;

/**
 * A DTO for the {@link org.adhes.hemophilie.domain.Hemarthrose} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HemarthroseDTO implements Serializable {

    private Long id;

    private OuiNonNP reponse;

    @NotNull
    private TypeManifestation type;

    @NotNull
    private String siege;

    private Integer frequencePerAn;

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

    public Integer getFrequencePerAn() {
        return frequencePerAn;
    }

    public void setFrequencePerAn(Integer frequencePerAn) {
        this.frequencePerAn = frequencePerAn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HemarthroseDTO)) {
            return false;
        }

        HemarthroseDTO hemarthroseDTO = (HemarthroseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hemarthroseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HemarthroseDTO{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", siege='" + getSiege() + "'" +
            ", frequencePerAn=" + getFrequencePerAn() +
            "}";
    }
}
