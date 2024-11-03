package org.adhes.hemophilie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.adhes.hemophilie.domain.enumeration.EvolutionSNC;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SaignementSNC.
 */
@Entity
@Table(name = "saignement_snc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SaignementSNC implements Serializable {

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
    @Column(name = "evolution")
    private EvolutionSNC evolution;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "saignementSNC")
    private Fiche fiche;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SaignementSNC id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OuiNonNP getReponse() {
        return this.reponse;
    }

    public SaignementSNC reponse(OuiNonNP reponse) {
        this.setReponse(reponse);
        return this;
    }

    public void setReponse(OuiNonNP reponse) {
        this.reponse = reponse;
    }

    public EvolutionSNC getEvolution() {
        return this.evolution;
    }

    public SaignementSNC evolution(EvolutionSNC evolution) {
        this.setEvolution(evolution);
        return this;
    }

    public void setEvolution(EvolutionSNC evolution) {
        this.evolution = evolution;
    }

    public Fiche getFiche() {
        return this.fiche;
    }

    public void setFiche(Fiche fiche) {
        if (this.fiche != null) {
            this.fiche.setSaignementSNC(null);
        }
        if (fiche != null) {
            fiche.setSaignementSNC(this);
        }
        this.fiche = fiche;
    }

    public SaignementSNC fiche(Fiche fiche) {
        this.setFiche(fiche);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SaignementSNC)) {
            return false;
        }
        return getId() != null && getId().equals(((SaignementSNC) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SaignementSNC{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", evolution='" + getEvolution() + "'" +
            "}";
    }
}
