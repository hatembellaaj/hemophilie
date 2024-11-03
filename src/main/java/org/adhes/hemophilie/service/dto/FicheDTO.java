package org.adhes.hemophilie.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import org.adhes.hemophilie.domain.enumeration.CircumstanceDecouverte;
import org.adhes.hemophilie.domain.enumeration.DiagnosticType;
import org.adhes.hemophilie.domain.enumeration.FormeHemophilie;
import org.adhes.hemophilie.domain.enumeration.InjectionType;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.PriseEnChargeType;
import org.adhes.hemophilie.domain.enumeration.TestResult;
import org.adhes.hemophilie.domain.enumeration.TestStatus;
import org.adhes.hemophilie.domain.enumeration.TraitementType;
import org.adhes.hemophilie.domain.enumeration.VieSocialeStatus;

/**
 * A DTO for the {@link org.adhes.hemophilie.domain.Fiche} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheDTO implements Serializable {

    private Long id;

    @NotNull
    private String dossierNumber;

    private String ordreNumber;

    private String indexNumber;

    private Integer anneeDiagnostic;

    @NotNull
    private DiagnosticType diagnostic;

    private String autreDiagnostic;

    private LocalDate dateEnregistrementRegistre;

    private Boolean consentementRegistre;

    private OuiNonNP parentsConsanguins;

    private String degreParenteConsanguins;

    private OuiNonNP casSimilairesFamille;

    private Integer nbreCasSimilaires;

    private String degreParenteCasSimilaires;

    private OuiNonNP casDecesSyndromeHemorragique;

    private Integer nbreCasDeces;

    private FormeHemophilie formeHemophilie;

    private Integer nbreFreres;

    private Integer nbreSoeurs;

    private LocalDate dateTestConfirmation;

    private Double hemogrammeHb;

    private Double hemogrammeHt;

    private Integer plaquettes;

    private Double tp;

    private Double fibrinogene;

    private Double tcaMT;

    private Double tcaMT2h;

    private Double tcaTemoin2h;

    private TestStatus ts;

    private CircumstanceDecouverte circumstanceDecouverte;

    private LocalDate date1erSigneClinique;

    private Integer ageDiagnostic;

    @NotNull
    private PriseEnChargeType priseEnCharge;

    private String causePriseEnCharge;

    private String doseProphylaxie;

    private Integer frequenceProphylaxie;

    private LocalDate debutProphylaxie;

    private InjectionType modaliteInjection;

    private TraitementType typeTraitementSubstitutif;

    private Integer age1ereSubstitution;

    private OuiNonNP psl;

    private OuiNonNP plasmaFraisCongele;

    private OuiNonNP cryoprecipite;

    private OuiNonNP complicationsOrthopediques;

    private OuiNonNP complicationInhibiteurs;

    private TestStatus testRecuperationFAH;

    private TestResult resultatTestRecuperation;

    private VieSocialeStatus vieSociale;

    private OuiNonNP etatMarital;

    private OuiNonNP enfants;

    private OuiNonNP handicap;

    private String typeHandicap;

    private OuiNonNP activiteSportive;

    private String typeActiviteSportive;

    private OuiNonNP decede;

    private String causeDateDeces;

    private HemarthroseDTO hemarthrose;

    private HematomeSuperficielDTO hematomeSuperficiel;

    private HematomePsoasDTO hematomePsoas;

    private HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueuses;

    private HemorragieVisceresDTO hemorragieVisceres;

    private SaignementSNCDTO saignementSNC;

    private UserDTO user;

    private PatientDTO patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return dossierNumber;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return ordreNumber;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Integer getAnneeDiagnostic() {
        return anneeDiagnostic;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public DiagnosticType getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return autreDiagnostic;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return dateEnregistrementRegistre;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public Boolean getConsentementRegistre() {
        return consentementRegistre;
    }

    public void setConsentementRegistre(Boolean consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public OuiNonNP getParentsConsanguins() {
        return parentsConsanguins;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public String getDegreParenteConsanguins() {
        return degreParenteConsanguins;
    }

    public void setDegreParenteConsanguins(String degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilairesFamille() {
        return casSimilairesFamille;
    }

    public void setCasSimilairesFamille(OuiNonNP casSimilairesFamille) {
        this.casSimilairesFamille = casSimilairesFamille;
    }

    public Integer getNbreCasSimilaires() {
        return nbreCasSimilaires;
    }

    public void setNbreCasSimilaires(Integer nbreCasSimilaires) {
        this.nbreCasSimilaires = nbreCasSimilaires;
    }

    public String getDegreParenteCasSimilaires() {
        return degreParenteCasSimilaires;
    }

    public void setDegreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
    }

    public OuiNonNP getCasDecesSyndromeHemorragique() {
        return casDecesSyndromeHemorragique;
    }

    public void setCasDecesSyndromeHemorragique(OuiNonNP casDecesSyndromeHemorragique) {
        this.casDecesSyndromeHemorragique = casDecesSyndromeHemorragique;
    }

    public Integer getNbreCasDeces() {
        return nbreCasDeces;
    }

    public void setNbreCasDeces(Integer nbreCasDeces) {
        this.nbreCasDeces = nbreCasDeces;
    }

    public FormeHemophilie getFormeHemophilie() {
        return formeHemophilie;
    }

    public void setFormeHemophilie(FormeHemophilie formeHemophilie) {
        this.formeHemophilie = formeHemophilie;
    }

    public Integer getNbreFreres() {
        return nbreFreres;
    }

    public void setNbreFreres(Integer nbreFreres) {
        this.nbreFreres = nbreFreres;
    }

    public Integer getNbreSoeurs() {
        return nbreSoeurs;
    }

    public void setNbreSoeurs(Integer nbreSoeurs) {
        this.nbreSoeurs = nbreSoeurs;
    }

    public LocalDate getDateTestConfirmation() {
        return dateTestConfirmation;
    }

    public void setDateTestConfirmation(LocalDate dateTestConfirmation) {
        this.dateTestConfirmation = dateTestConfirmation;
    }

    public Double getHemogrammeHb() {
        return hemogrammeHb;
    }

    public void setHemogrammeHb(Double hemogrammeHb) {
        this.hemogrammeHb = hemogrammeHb;
    }

    public Double getHemogrammeHt() {
        return hemogrammeHt;
    }

    public void setHemogrammeHt(Double hemogrammeHt) {
        this.hemogrammeHt = hemogrammeHt;
    }

    public Integer getPlaquettes() {
        return plaquettes;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getFibrinogene() {
        return fibrinogene;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public Double getTcaMT() {
        return tcaMT;
    }

    public void setTcaMT(Double tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Double getTcaMT2h() {
        return tcaMT2h;
    }

    public void setTcaMT2h(Double tcaMT2h) {
        this.tcaMT2h = tcaMT2h;
    }

    public Double getTcaTemoin2h() {
        return tcaTemoin2h;
    }

    public void setTcaTemoin2h(Double tcaTemoin2h) {
        this.tcaTemoin2h = tcaTemoin2h;
    }

    public TestStatus getTs() {
        return ts;
    }

    public void setTs(TestStatus ts) {
        this.ts = ts;
    }

    public CircumstanceDecouverte getCircumstanceDecouverte() {
        return circumstanceDecouverte;
    }

    public void setCircumstanceDecouverte(CircumstanceDecouverte circumstanceDecouverte) {
        this.circumstanceDecouverte = circumstanceDecouverte;
    }

    public LocalDate getDate1erSigneClinique() {
        return date1erSigneClinique;
    }

    public void setDate1erSigneClinique(LocalDate date1erSigneClinique) {
        this.date1erSigneClinique = date1erSigneClinique;
    }

    public Integer getAgeDiagnostic() {
        return ageDiagnostic;
    }

    public void setAgeDiagnostic(Integer ageDiagnostic) {
        this.ageDiagnostic = ageDiagnostic;
    }

    public PriseEnChargeType getPriseEnCharge() {
        return priseEnCharge;
    }

    public void setPriseEnCharge(PriseEnChargeType priseEnCharge) {
        this.priseEnCharge = priseEnCharge;
    }

    public String getCausePriseEnCharge() {
        return causePriseEnCharge;
    }

    public void setCausePriseEnCharge(String causePriseEnCharge) {
        this.causePriseEnCharge = causePriseEnCharge;
    }

    public String getDoseProphylaxie() {
        return doseProphylaxie;
    }

    public void setDoseProphylaxie(String doseProphylaxie) {
        this.doseProphylaxie = doseProphylaxie;
    }

    public Integer getFrequenceProphylaxie() {
        return frequenceProphylaxie;
    }

    public void setFrequenceProphylaxie(Integer frequenceProphylaxie) {
        this.frequenceProphylaxie = frequenceProphylaxie;
    }

    public LocalDate getDebutProphylaxie() {
        return debutProphylaxie;
    }

    public void setDebutProphylaxie(LocalDate debutProphylaxie) {
        this.debutProphylaxie = debutProphylaxie;
    }

    public InjectionType getModaliteInjection() {
        return modaliteInjection;
    }

    public void setModaliteInjection(InjectionType modaliteInjection) {
        this.modaliteInjection = modaliteInjection;
    }

    public TraitementType getTypeTraitementSubstitutif() {
        return typeTraitementSubstitutif;
    }

    public void setTypeTraitementSubstitutif(TraitementType typeTraitementSubstitutif) {
        this.typeTraitementSubstitutif = typeTraitementSubstitutif;
    }

    public Integer getAge1ereSubstitution() {
        return age1ereSubstitution;
    }

    public void setAge1ereSubstitution(Integer age1ereSubstitution) {
        this.age1ereSubstitution = age1ereSubstitution;
    }

    public OuiNonNP getPsl() {
        return psl;
    }

    public void setPsl(OuiNonNP psl) {
        this.psl = psl;
    }

    public OuiNonNP getPlasmaFraisCongele() {
        return plasmaFraisCongele;
    }

    public void setPlasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.plasmaFraisCongele = plasmaFraisCongele;
    }

    public OuiNonNP getCryoprecipite() {
        return cryoprecipite;
    }

    public void setCryoprecipite(OuiNonNP cryoprecipite) {
        this.cryoprecipite = cryoprecipite;
    }

    public OuiNonNP getComplicationsOrthopediques() {
        return complicationsOrthopediques;
    }

    public void setComplicationsOrthopediques(OuiNonNP complicationsOrthopediques) {
        this.complicationsOrthopediques = complicationsOrthopediques;
    }

    public OuiNonNP getComplicationInhibiteurs() {
        return complicationInhibiteurs;
    }

    public void setComplicationInhibiteurs(OuiNonNP complicationInhibiteurs) {
        this.complicationInhibiteurs = complicationInhibiteurs;
    }

    public TestStatus getTestRecuperationFAH() {
        return testRecuperationFAH;
    }

    public void setTestRecuperationFAH(TestStatus testRecuperationFAH) {
        this.testRecuperationFAH = testRecuperationFAH;
    }

    public TestResult getResultatTestRecuperation() {
        return resultatTestRecuperation;
    }

    public void setResultatTestRecuperation(TestResult resultatTestRecuperation) {
        this.resultatTestRecuperation = resultatTestRecuperation;
    }

    public VieSocialeStatus getVieSociale() {
        return vieSociale;
    }

    public void setVieSociale(VieSocialeStatus vieSociale) {
        this.vieSociale = vieSociale;
    }

    public OuiNonNP getEtatMarital() {
        return etatMarital;
    }

    public void setEtatMarital(OuiNonNP etatMarital) {
        this.etatMarital = etatMarital;
    }

    public OuiNonNP getEnfants() {
        return enfants;
    }

    public void setEnfants(OuiNonNP enfants) {
        this.enfants = enfants;
    }

    public OuiNonNP getHandicap() {
        return handicap;
    }

    public void setHandicap(OuiNonNP handicap) {
        this.handicap = handicap;
    }

    public String getTypeHandicap() {
        return typeHandicap;
    }

    public void setTypeHandicap(String typeHandicap) {
        this.typeHandicap = typeHandicap;
    }

    public OuiNonNP getActiviteSportive() {
        return activiteSportive;
    }

    public void setActiviteSportive(OuiNonNP activiteSportive) {
        this.activiteSportive = activiteSportive;
    }

    public String getTypeActiviteSportive() {
        return typeActiviteSportive;
    }

    public void setTypeActiviteSportive(String typeActiviteSportive) {
        this.typeActiviteSportive = typeActiviteSportive;
    }

    public OuiNonNP getDecede() {
        return decede;
    }

    public void setDecede(OuiNonNP decede) {
        this.decede = decede;
    }

    public String getCauseDateDeces() {
        return causeDateDeces;
    }

    public void setCauseDateDeces(String causeDateDeces) {
        this.causeDateDeces = causeDateDeces;
    }

    public HemarthroseDTO getHemarthrose() {
        return hemarthrose;
    }

    public void setHemarthrose(HemarthroseDTO hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public HematomeSuperficielDTO getHematomeSuperficiel() {
        return hematomeSuperficiel;
    }

    public void setHematomeSuperficiel(HematomeSuperficielDTO hematomeSuperficiel) {
        this.hematomeSuperficiel = hematomeSuperficiel;
    }

    public HematomePsoasDTO getHematomePsoas() {
        return hematomePsoas;
    }

    public void setHematomePsoas(HematomePsoasDTO hematomePsoas) {
        this.hematomePsoas = hematomePsoas;
    }

    public HemorragiesCutaneoMuqueusesDTO getHemorragiesCutaneoMuqueuses() {
        return hemorragiesCutaneoMuqueuses;
    }

    public void setHemorragiesCutaneoMuqueuses(HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueuses) {
        this.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;
    }

    public HemorragieVisceresDTO getHemorragieVisceres() {
        return hemorragieVisceres;
    }

    public void setHemorragieVisceres(HemorragieVisceresDTO hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public SaignementSNCDTO getSaignementSNC() {
        return saignementSNC;
    }

    public void setSaignementSNC(SaignementSNCDTO saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheDTO)) {
            return false;
        }

        FicheDTO ficheDTO = (FicheDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ficheDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheDTO{" +
            "id=" + getId() +
            ", dossierNumber='" + getDossierNumber() + "'" +
            ", ordreNumber='" + getOrdreNumber() + "'" +
            ", indexNumber='" + getIndexNumber() + "'" +
            ", anneeDiagnostic=" + getAnneeDiagnostic() +
            ", diagnostic='" + getDiagnostic() + "'" +
            ", autreDiagnostic='" + getAutreDiagnostic() + "'" +
            ", dateEnregistrementRegistre='" + getDateEnregistrementRegistre() + "'" +
            ", consentementRegistre='" + getConsentementRegistre() + "'" +
            ", parentsConsanguins='" + getParentsConsanguins() + "'" +
            ", degreParenteConsanguins='" + getDegreParenteConsanguins() + "'" +
            ", casSimilairesFamille='" + getCasSimilairesFamille() + "'" +
            ", nbreCasSimilaires=" + getNbreCasSimilaires() +
            ", degreParenteCasSimilaires='" + getDegreParenteCasSimilaires() + "'" +
            ", casDecesSyndromeHemorragique='" + getCasDecesSyndromeHemorragique() + "'" +
            ", nbreCasDeces=" + getNbreCasDeces() +
            ", formeHemophilie='" + getFormeHemophilie() + "'" +
            ", nbreFreres=" + getNbreFreres() +
            ", nbreSoeurs=" + getNbreSoeurs() +
            ", dateTestConfirmation='" + getDateTestConfirmation() + "'" +
            ", hemogrammeHb=" + getHemogrammeHb() +
            ", hemogrammeHt=" + getHemogrammeHt() +
            ", plaquettes=" + getPlaquettes() +
            ", tp=" + getTp() +
            ", fibrinogene=" + getFibrinogene() +
            ", tcaMT=" + getTcaMT() +
            ", tcaMT2h=" + getTcaMT2h() +
            ", tcaTemoin2h=" + getTcaTemoin2h() +
            ", ts='" + getTs() + "'" +
            ", circumstanceDecouverte='" + getCircumstanceDecouverte() + "'" +
            ", date1erSigneClinique='" + getDate1erSigneClinique() + "'" +
            ", ageDiagnostic=" + getAgeDiagnostic() +
            ", priseEnCharge='" + getPriseEnCharge() + "'" +
            ", causePriseEnCharge='" + getCausePriseEnCharge() + "'" +
            ", doseProphylaxie='" + getDoseProphylaxie() + "'" +
            ", frequenceProphylaxie=" + getFrequenceProphylaxie() +
            ", debutProphylaxie='" + getDebutProphylaxie() + "'" +
            ", modaliteInjection='" + getModaliteInjection() + "'" +
            ", typeTraitementSubstitutif='" + getTypeTraitementSubstitutif() + "'" +
            ", age1ereSubstitution=" + getAge1ereSubstitution() +
            ", psl='" + getPsl() + "'" +
            ", plasmaFraisCongele='" + getPlasmaFraisCongele() + "'" +
            ", cryoprecipite='" + getCryoprecipite() + "'" +
            ", complicationsOrthopediques='" + getComplicationsOrthopediques() + "'" +
            ", complicationInhibiteurs='" + getComplicationInhibiteurs() + "'" +
            ", testRecuperationFAH='" + getTestRecuperationFAH() + "'" +
            ", resultatTestRecuperation='" + getResultatTestRecuperation() + "'" +
            ", vieSociale='" + getVieSociale() + "'" +
            ", etatMarital='" + getEtatMarital() + "'" +
            ", enfants='" + getEnfants() + "'" +
            ", handicap='" + getHandicap() + "'" +
            ", typeHandicap='" + getTypeHandicap() + "'" +
            ", activiteSportive='" + getActiviteSportive() + "'" +
            ", typeActiviteSportive='" + getTypeActiviteSportive() + "'" +
            ", decede='" + getDecede() + "'" +
            ", causeDateDeces='" + getCauseDateDeces() + "'" +
            ", hemarthrose=" + getHemarthrose() +
            ", hematomeSuperficiel=" + getHematomeSuperficiel() +
            ", hematomePsoas=" + getHematomePsoas() +
            ", hemorragiesCutaneoMuqueuses=" + getHemorragiesCutaneoMuqueuses() +
            ", hemorragieVisceres=" + getHemorragieVisceres() +
            ", saignementSNC=" + getSaignementSNC() +
            ", user=" + getUser() +
            ", patient=" + getPatient() +
            "}";
    }
}
