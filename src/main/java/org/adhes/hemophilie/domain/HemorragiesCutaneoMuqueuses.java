package org.adhes.hemophilie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeHemorragie;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HemorragiesCutaneoMuqueuses.
 */
@Entity
@Table(name = "hemorragies_cutaneo_muqueuses")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HemorragiesCutaneoMuqueuses implements Serializable {

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
    private TypeHemorragie type;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hemorragiesCutaneoMuqueuses")
    private Fiche fiche;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HemorragiesCutaneoMuqueuses id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OuiNonNP getReponse() {
        return this.reponse;
    }

    public HemorragiesCutaneoMuqueuses reponse(OuiNonNP reponse) {
        this.setReponse(reponse);
        return this;
    }

    public void setReponse(OuiNonNP reponse) {
        this.reponse = reponse;
    }

    public TypeHemorragie getType() {
        return this.type;
    }

    public HemorragiesCutaneoMuqueuses type(TypeHemorragie type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeHemorragie type) {
        this.type = type;
    }

    public Integer getFrequencePerAn() {
        return this.frequencePerAn;
    }

    public HemorragiesCutaneoMuqueuses frequencePerAn(Integer frequencePerAn) {
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
            this.fiche.setHemorragiesCutaneoMuqueuses(null);
        }
        if (fiche != null) {
            fiche.setHemorragiesCutaneoMuqueuses(this);
        }
        this.fiche = fiche;
    }

    public HemorragiesCutaneoMuqueuses fiche(Fiche fiche) {
        this.setFiche(fiche);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HemorragiesCutaneoMuqueuses)) {
            return false;
        }
        return getId() != null && getId().equals(((HemorragiesCutaneoMuqueuses) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HemorragiesCutaneoMuqueuses{" +
            "id=" + getId() +
            ", reponse='" + getReponse() + "'" +
            ", type='" + getType() + "'" +
            ", frequencePerAn=" + getFrequencePerAn() +
            "}";
    }
}
