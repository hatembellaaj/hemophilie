package org.adhes.hemophilie.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeHemorragie;

/**
 * A DTO for the {@link org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueuses} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HemorragiesCutaneoMuqueusesDTO implements Serializable {

    private Long id;

    private OuiNonNP reponse;

    private TypeHemorragie type;

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

    public TypeHemorragie getType() {
        return type;
    }

    public void setType(TypeHemorragie type) {
        this.type = type;
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
        if (!(o instanceof HemorragiesCutaneoMuqueusesDTO)) {
            return false;
        }

        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = (HemorragiesCutaneoMuqueusesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hemorragiesCutaneoMuqueusesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HemorragiesCutaneoMuqueusesDTO{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", frequencePerAn=" + getFrequencePerAn() +
            "}";
    }
}
