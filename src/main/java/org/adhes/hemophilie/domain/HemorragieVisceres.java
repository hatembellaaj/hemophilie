package org.adhes.hemophilie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeHemorragieVisceres;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HemorragieVisceres.
 */
@Entity
@Table(name = "hemorragie_visceres")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HemorragieVisceres implements Serializable {

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
    private TypeHemorragieVisceres type;

    @Enumerated(EnumType.STRING)
    @Column(name = "exploration")
    private OuiNonNP exploration;

    @Column(name = "examen_lesion")
    private String examenLesion;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hemorragieVisceres")
    private Fiche fiche;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HemorragieVisceres id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OuiNonNP getReponse() {
        return this.reponse;
    }

    public HemorragieVisceres reponse(OuiNonNP reponse) {
        this.setReponse(reponse);
        return this;
    }

    public void setReponse(OuiNonNP reponse) {
        this.reponse = reponse;
    }

    public TypeHemorragieVisceres getType() {
        return this.type;
    }

    public HemorragieVisceres type(TypeHemorragieVisceres type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeHemorragieVisceres type) {
        this.type = type;
    }

    public OuiNonNP getExploration() {
        return this.exploration;
    }

    public HemorragieVisceres exploration(OuiNonNP exploration) {
        this.setExploration(exploration);
        return this;
    }

    public void setExploration(OuiNonNP exploration) {
        this.exploration = exploration;
    }

    public String getExamenLesion() {
        return this.examenLesion;
    }

    public HemorragieVisceres examenLesion(String examenLesion) {
        this.setExamenLesion(examenLesion);
        return this;
    }

    public void setExamenLesion(String examenLesion) {
        this.examenLesion = examenLesion;
    }

    public Fiche getFiche() {
        return this.fiche;
    }

    public void setFiche(Fiche fiche) {
        if (this.fiche != null) {
            this.fiche.setHemorragieVisceres(null);
        }
        if (fiche != null) {
            fiche.setHemorragieVisceres(this);
        }
        this.fiche = fiche;
    }

    public HemorragieVisceres fiche(Fiche fiche) {
        this.setFiche(fiche);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HemorragieVisceres)) {
            return false;
        }
        return getId() != null && getId().equals(((HemorragieVisceres) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HemorragieVisceres{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", exploration='" + getExploration() + "'" +
            ", examenLesion='" + getExamenLesion() + "'" +
            "}";
    }
}
