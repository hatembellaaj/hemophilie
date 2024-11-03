package org.adhes.hemophilie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeManifestation;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Hemarthrose.
 */
@Entity
@Table(name = "hemarthrose")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Hemarthrose implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "reponse")
    private OuiNonNP reponse;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeManifestation type;

    @NotNull
    @Column(name = "siege", nullable = false)
    private String siege;

    @Column(name = "frequence_per_an")
    private Integer frequencePerAn;

    @JsonIgnoreProperties(
        value = {
            "hemarthrose",
            "hematomeSuperficiel",
            "hematomePsoas",
            "hemorragiesCutaneoMuqueuses",
            "hemorragieVisceres",
            "saignementSNC",
            "user",
            "patient",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hemarthrose")
    private Fiche fiche;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Hemarthrose id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OuiNonNP getReponse() {
        return this.reponse;
    }

    public Hemarthrose reponse(OuiNonNP reponse) {
        this.setReponse(reponse);
        return this;
    }

    public void setReponse(OuiNonNP reponse) {
        this.reponse = reponse;
    }

    public TypeManifestation getType() {
        return this.type;
    }

    public Hemarthrose type(TypeManifestation type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeManifestation type) {
        this.type = type;
    }

    public String getSiege() {
        return this.siege;
    }

    public Hemarthrose siege(String siege) {
        this.setSiege(siege);
        return this;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    public Integer getFrequencePerAn() {
        return this.frequencePerAn;
    }

    public Hemarthrose frequencePerAn(Integer frequencePerAn) {
        this.setFrequencePerAn(frequencePerAn);
        return this;
    }

    public void setFrequencePerAn(Integer frequencePerAn) {
        this.frequencePerAn = frequencePerAn;
    }

    public Fiche getFiche() {
        return this.fiche;
    }

    public void setFiche(Fiche fiche) {
        if (this.fiche != null) {
            this.fiche.setHemarthrose(null);
        }
        if (fiche != null) {
            fiche.setHemarthrose(this);
        }
        this.fiche = fiche;
    }

    public Hemarthrose fiche(Fiche fiche) {
        this.setFiche(fiche);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hemarthrose)) {
            return false;
        }
        return getId() != null && getId().equals(((Hemarthrose) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Hemarthrose{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", siege='" + getSiege() + "'" +
            ", frequencePerAn=" + getFrequencePerAn() +
            "}";
    }
}
