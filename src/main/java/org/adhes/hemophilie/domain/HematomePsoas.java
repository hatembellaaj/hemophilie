package org.adhes.hemophilie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeManifestation;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HematomePsoas.
 */
@Entity
@Table(name = "hematome_psoas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HematomePsoas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "reponse")
    private OuiNonNP reponse;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeManifestation type;

    @Enumerated(EnumType.STRING)
    @Column(name = "recidive")
    private OuiNonNP recidive;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hematomePsoas")
    private Fiche fiche;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HematomePsoas id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OuiNonNP getReponse() {
        return this.reponse;
    }

    public HematomePsoas reponse(OuiNonNP reponse) {
        this.setReponse(reponse);
        return this;
    }

    public void setReponse(OuiNonNP reponse) {
        this.reponse = reponse;
    }

    public TypeManifestation getType() {
        return this.type;
    }

    public HematomePsoas type(TypeManifestation type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeManifestation type) {
        this.type = type;
    }

    public OuiNonNP getRecidive() {
        return this.recidive;
    }

    public HematomePsoas recidive(OuiNonNP recidive) {
        this.setRecidive(recidive);
        return this;
    }

    public void setRecidive(OuiNonNP recidive) {
        this.recidive = recidive;
    }

    public Fiche getFiche() {
        return this.fiche;
    }

    public void setFiche(Fiche fiche) {
        if (this.fiche != null) {
            this.fiche.setHematomePsoas(null);
        }
        if (fiche != null) {
            fiche.setHematomePsoas(this);
        }
        this.fiche = fiche;
    }

    public HematomePsoas fiche(Fiche fiche) {
        this.setFiche(fiche);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HematomePsoas)) {
            return false;
        }
        return getId() != null && getId().equals(((HematomePsoas) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HematomePsoas{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", recidive='" + getRecidive() + "'" +
            "}";
    }
}
