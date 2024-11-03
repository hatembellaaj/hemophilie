package org.adhes.hemophilie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Fiche.
 */
@Entity
@Table(name = "fiche")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fiche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "dossier_number", nullable = false)
    private String dossierNumber;

    @Column(name = "ordre_number")
    private String ordreNumber;

    @Column(name = "index_number")
    private String indexNumber;

    @Column(name = "annee_diagnostic")
    private Integer anneeDiagnostic;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "diagnostic", nullable = false)
    private DiagnosticType diagnostic;

    @Column(name = "autre_diagnostic")
    private String autreDiagnostic;

    @Column(name = "date_enregistrement_registre")
    private LocalDate dateEnregistrementRegistre;

    @Column(name = "consentement_registre")
    private Boolean consentementRegistre;

    @Enumerated(EnumType.STRING)
    @Column(name = "parents_consanguins")
    private OuiNonNP parentsConsanguins;

    @Column(name = "degre_parente_consanguins")
    private String degreParenteConsanguins;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_similaires_famille")
    private OuiNonNP casSimilairesFamille;

    @Column(name = "nbre_cas_similaires")
    private Integer nbreCasSimilaires;

    @Column(name = "degre_parente_cas_similaires")
    private String degreParenteCasSimilaires;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_deces_syndrome_hemorragique")
    private OuiNonNP casDecesSyndromeHemorragique;

    @Column(name = "nbre_cas_deces")
    private Integer nbreCasDeces;

    @Enumerated(EnumType.STRING)
    @Column(name = "forme_hemophilie")
    private FormeHemophilie formeHemophilie;

    @Column(name = "nbre_freres")
    private Integer nbreFreres;

    @Column(name = "nbre_soeurs")
    private Integer nbreSoeurs;

    @Column(name = "date_test_confirmation")
    private LocalDate dateTestConfirmation;

    @Column(name = "hemogramme_hb")
    private Double hemogrammeHb;

    @Column(name = "hemogramme_ht")
    private Double hemogrammeHt;

    @Column(name = "plaquettes")
    private Integer plaquettes;

    @Column(name = "tp")
    private Double tp;

    @Column(name = "fibrinogene")
    private Double fibrinogene;

    @Column(name = "tca_mt")
    private Double tcaMT;

    @Column(name = "tca_mt_2_h")
    private Double tcaMT2h;

    @Column(name = "tca_temoin_2_h")
    private Double tcaTemoin2h;

    @Enumerated(EnumType.STRING)
    @Column(name = "ts")
    private TestStatus ts;

    @Enumerated(EnumType.STRING)
    @Column(name = "circumstance_decouverte")
    private CircumstanceDecouverte circumstanceDecouverte;

    @Column(name = "date_1_er_signe_clinique")
    private LocalDate date1erSigneClinique;

    @Column(name = "age_diagnostic")
    private Integer ageDiagnostic;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "prise_en_charge", nullable = false)
    private PriseEnChargeType priseEnCharge;

    @Column(name = "cause_prise_en_charge")
    private String causePriseEnCharge;

    @Column(name = "dose_prophylaxie")
    private String doseProphylaxie;

    @Column(name = "frequence_prophylaxie")
    private Integer frequenceProphylaxie;

    @Column(name = "debut_prophylaxie")
    private LocalDate debutProphylaxie;

    @Enumerated(EnumType.STRING)
    @Column(name = "modalite_injection")
    private InjectionType modaliteInjection;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_traitement_substitutif")
    private TraitementType typeTraitementSubstitutif;

    @Column(name = "age_1_ere_substitution")
    private Integer age1ereSubstitution;

    @Enumerated(EnumType.STRING)
    @Column(name = "psl")
    private OuiNonNP psl;

    @Enumerated(EnumType.STRING)
    @Column(name = "plasma_frais_congele")
    private OuiNonNP plasmaFraisCongele;

    @Enumerated(EnumType.STRING)
    @Column(name = "cryoprecipite")
    private OuiNonNP cryoprecipite;

    @Enumerated(EnumType.STRING)
    @Column(name = "complications_orthopediques")
    private OuiNonNP complicationsOrthopediques;

    @Enumerated(EnumType.STRING)
    @Column(name = "complication_inhibiteurs")
    private OuiNonNP complicationInhibiteurs;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_recuperation_fah")
    private TestStatus testRecuperationFAH;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultat_test_recuperation")
    private TestResult resultatTestRecuperation;

    @Enumerated(EnumType.STRING)
    @Column(name = "vie_sociale")
    private VieSocialeStatus vieSociale;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_marital")
    private OuiNonNP etatMarital;

    @Enumerated(EnumType.STRING)
    @Column(name = "enfants")
    private OuiNonNP enfants;

    @Enumerated(EnumType.STRING)
    @Column(name = "handicap")
    private OuiNonNP handicap;

    @Column(name = "type_handicap")
    private String typeHandicap;

    @Enumerated(EnumType.STRING)
    @Column(name = "activite_sportive")
    private OuiNonNP activiteSportive;

    @Column(name = "type_activite_sportive")
    private String typeActiviteSportive;

    @Enumerated(EnumType.STRING)
    @Column(name = "decede")
    private OuiNonNP decede;

    @Column(name = "cause_date_deces")
    private String causeDateDeces;

    @JsonIgnoreProperties(value = { "fiche" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Hemarthrose hemarthrose;

    @JsonIgnoreProperties(value = { "fiche" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private HematomeSuperficiel hematomeSuperficiel;

    @JsonIgnoreProperties(value = { "fiche" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private HematomePsoas hematomePsoas;

    @JsonIgnoreProperties(value = { "fiche" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses;

    @JsonIgnoreProperties(value = { "fiche" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private HemorragieVisceres hemorragieVisceres;

    @JsonIgnoreProperties(value = { "fiche" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private SaignementSNC saignementSNC;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "fiches" }, allowSetters = true)
    private Patient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Fiche id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return this.dossierNumber;
    }

    public Fiche dossierNumber(String dossierNumber) {
        this.setDossierNumber(dossierNumber);
        return this;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return this.ordreNumber;
    }

    public Fiche ordreNumber(String ordreNumber) {
        this.setOrdreNumber(ordreNumber);
        return this;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

    public Fiche indexNumber(String indexNumber) {
        this.setIndexNumber(indexNumber);
        return this;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Integer getAnneeDiagnostic() {
        return this.anneeDiagnostic;
    }

    public Fiche anneeDiagnostic(Integer anneeDiagnostic) {
        this.setAnneeDiagnostic(anneeDiagnostic);
        return this;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public DiagnosticType getDiagnostic() {
        return this.diagnostic;
    }

    public Fiche diagnostic(DiagnosticType diagnostic) {
        this.setDiagnostic(diagnostic);
        return this;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return this.autreDiagnostic;
    }

    public Fiche autreDiagnostic(String autreDiagnostic) {
        this.setAutreDiagnostic(autreDiagnostic);
        return this;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return this.dateEnregistrementRegistre;
    }

    public Fiche dateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.setDateEnregistrementRegistre(dateEnregistrementRegistre);
        return this;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public Boolean getConsentementRegistre() {
        return this.consentementRegistre;
    }

    public Fiche consentementRegistre(Boolean consentementRegistre) {
        this.setConsentementRegistre(consentementRegistre);
        return this;
    }

    public void setConsentementRegistre(Boolean consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public OuiNonNP getParentsConsanguins() {
        return this.parentsConsanguins;
    }

    public Fiche parentsConsanguins(OuiNonNP parentsConsanguins) {
        this.setParentsConsanguins(parentsConsanguins);
        return this;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public String getDegreParenteConsanguins() {
        return this.degreParenteConsanguins;
    }

    public Fiche degreParenteConsanguins(String degreParenteConsanguins) {
        this.setDegreParenteConsanguins(degreParenteConsanguins);
        return this;
    }

    public void setDegreParenteConsanguins(String degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilairesFamille() {
        return this.casSimilairesFamille;
    }

    public Fiche casSimilairesFamille(OuiNonNP casSimilairesFamille) {
        this.setCasSimilairesFamille(casSimilairesFamille);
        return this;
    }

    public void setCasSimilairesFamille(OuiNonNP casSimilairesFamille) {
        this.casSimilairesFamille = casSimilairesFamille;
    }

    public Integer getNbreCasSimilaires() {
        return this.nbreCasSimilaires;
    }

    public Fiche nbreCasSimilaires(Integer nbreCasSimilaires) {
        this.setNbreCasSimilaires(nbreCasSimilaires);
        return this;
    }

    public void setNbreCasSimilaires(Integer nbreCasSimilaires) {
        this.nbreCasSimilaires = nbreCasSimilaires;
    }

    public String getDegreParenteCasSimilaires() {
        return this.degreParenteCasSimilaires;
    }

    public Fiche degreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.setDegreParenteCasSimilaires(degreParenteCasSimilaires);
        return this;
    }

    public void setDegreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
    }

    public OuiNonNP getCasDecesSyndromeHemorragique() {
        return this.casDecesSyndromeHemorragique;
    }

    public Fiche casDecesSyndromeHemorragique(OuiNonNP casDecesSyndromeHemorragique) {
        this.setCasDecesSyndromeHemorragique(casDecesSyndromeHemorragique);
        return this;
    }

    public void setCasDecesSyndromeHemorragique(OuiNonNP casDecesSyndromeHemorragique) {
        this.casDecesSyndromeHemorragique = casDecesSyndromeHemorragique;
    }

    public Integer getNbreCasDeces() {
        return this.nbreCasDeces;
    }

    public Fiche nbreCasDeces(Integer nbreCasDeces) {
        this.setNbreCasDeces(nbreCasDeces);
        return this;
    }

    public void setNbreCasDeces(Integer nbreCasDeces) {
        this.nbreCasDeces = nbreCasDeces;
    }

    public FormeHemophilie getFormeHemophilie() {
        return this.formeHemophilie;
    }

    public Fiche formeHemophilie(FormeHemophilie formeHemophilie) {
        this.setFormeHemophilie(formeHemophilie);
        return this;
    }

    public void setFormeHemophilie(FormeHemophilie formeHemophilie) {
        this.formeHemophilie = formeHemophilie;
    }

    public Integer getNbreFreres() {
        return this.nbreFreres;
    }

    public Fiche nbreFreres(Integer nbreFreres) {
        this.setNbreFreres(nbreFreres);
        return this;
    }

    public void setNbreFreres(Integer nbreFreres) {
        this.nbreFreres = nbreFreres;
    }

    public Integer getNbreSoeurs() {
        return this.nbreSoeurs;
    }

    public Fiche nbreSoeurs(Integer nbreSoeurs) {
        this.setNbreSoeurs(nbreSoeurs);
        return this;
    }

    public void setNbreSoeurs(Integer nbreSoeurs) {
        this.nbreSoeurs = nbreSoeurs;
    }

    public LocalDate getDateTestConfirmation() {
        return this.dateTestConfirmation;
    }

    public Fiche dateTestConfirmation(LocalDate dateTestConfirmation) {
        this.setDateTestConfirmation(dateTestConfirmation);
        return this;
    }

    public void setDateTestConfirmation(LocalDate dateTestConfirmation) {
        this.dateTestConfirmation = dateTestConfirmation;
    }

    public Double getHemogrammeHb() {
        return this.hemogrammeHb;
    }

    public Fiche hemogrammeHb(Double hemogrammeHb) {
        this.setHemogrammeHb(hemogrammeHb);
        return this;
    }

    public void setHemogrammeHb(Double hemogrammeHb) {
        this.hemogrammeHb = hemogrammeHb;
    }

    public Double getHemogrammeHt() {
        return this.hemogrammeHt;
    }

    public Fiche hemogrammeHt(Double hemogrammeHt) {
        this.setHemogrammeHt(hemogrammeHt);
        return this;
    }

    public void setHemogrammeHt(Double hemogrammeHt) {
        this.hemogrammeHt = hemogrammeHt;
    }

    public Integer getPlaquettes() {
        return this.plaquettes;
    }

    public Fiche plaquettes(Integer plaquettes) {
        this.setPlaquettes(plaquettes);
        return this;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Double getTp() {
        return this.tp;
    }

    public Fiche tp(Double tp) {
        this.setTp(tp);
        return this;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getFibrinogene() {
        return this.fibrinogene;
    }

    public Fiche fibrinogene(Double fibrinogene) {
        this.setFibrinogene(fibrinogene);
        return this;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public Double getTcaMT() {
        return this.tcaMT;
    }

    public Fiche tcaMT(Double tcaMT) {
        this.setTcaMT(tcaMT);
        return this;
    }

    public void setTcaMT(Double tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Double getTcaMT2h() {
        return this.tcaMT2h;
    }

    public Fiche tcaMT2h(Double tcaMT2h) {
        this.setTcaMT2h(tcaMT2h);
        return this;
    }

    public void setTcaMT2h(Double tcaMT2h) {
        this.tcaMT2h = tcaMT2h;
    }

    public Double getTcaTemoin2h() {
        return this.tcaTemoin2h;
    }

    public Fiche tcaTemoin2h(Double tcaTemoin2h) {
        this.setTcaTemoin2h(tcaTemoin2h);
        return this;
    }

    public void setTcaTemoin2h(Double tcaTemoin2h) {
        this.tcaTemoin2h = tcaTemoin2h;
    }

    public TestStatus getTs() {
        return this.ts;
    }

    public Fiche ts(TestStatus ts) {
        this.setTs(ts);
        return this;
    }

    public void setTs(TestStatus ts) {
        this.ts = ts;
    }

    public CircumstanceDecouverte getCircumstanceDecouverte() {
        return this.circumstanceDecouverte;
    }

    public Fiche circumstanceDecouverte(CircumstanceDecouverte circumstanceDecouverte) {
        this.setCircumstanceDecouverte(circumstanceDecouverte);
        return this;
    }

    public void setCircumstanceDecouverte(CircumstanceDecouverte circumstanceDecouverte) {
        this.circumstanceDecouverte = circumstanceDecouverte;
    }

    public LocalDate getDate1erSigneClinique() {
        return this.date1erSigneClinique;
    }

    public Fiche date1erSigneClinique(LocalDate date1erSigneClinique) {
        this.setDate1erSigneClinique(date1erSigneClinique);
        return this;
    }

    public void setDate1erSigneClinique(LocalDate date1erSigneClinique) {
        this.date1erSigneClinique = date1erSigneClinique;
    }

    public Integer getAgeDiagnostic() {
        return this.ageDiagnostic;
    }

    public Fiche ageDiagnostic(Integer ageDiagnostic) {
        this.setAgeDiagnostic(ageDiagnostic);
        return this;
    }

    public void setAgeDiagnostic(Integer ageDiagnostic) {
        this.ageDiagnostic = ageDiagnostic;
    }

    public PriseEnChargeType getPriseEnCharge() {
        return this.priseEnCharge;
    }

    public Fiche priseEnCharge(PriseEnChargeType priseEnCharge) {
        this.setPriseEnCharge(priseEnCharge);
        return this;
    }

    public void setPriseEnCharge(PriseEnChargeType priseEnCharge) {
        this.priseEnCharge = priseEnCharge;
    }

    public String getCausePriseEnCharge() {
        return this.causePriseEnCharge;
    }

    public Fiche causePriseEnCharge(String causePriseEnCharge) {
        this.setCausePriseEnCharge(causePriseEnCharge);
        return this;
    }

    public void setCausePriseEnCharge(String causePriseEnCharge) {
        this.causePriseEnCharge = causePriseEnCharge;
    }

    public String getDoseProphylaxie() {
        return this.doseProphylaxie;
    }

    public Fiche doseProphylaxie(String doseProphylaxie) {
        this.setDoseProphylaxie(doseProphylaxie);
        return this;
    }

    public void setDoseProphylaxie(String doseProphylaxie) {
        this.doseProphylaxie = doseProphylaxie;
    }

    public Integer getFrequenceProphylaxie() {
        return this.frequenceProphylaxie;
    }

    public Fiche frequenceProphylaxie(Integer frequenceProphylaxie) {
        this.setFrequenceProphylaxie(frequenceProphylaxie);
        return this;
    }

    public void setFrequenceProphylaxie(Integer frequenceProphylaxie) {
        this.frequenceProphylaxie = frequenceProphylaxie;
    }

    public LocalDate getDebutProphylaxie() {
        return this.debutProphylaxie;
    }

    public Fiche debutProphylaxie(LocalDate debutProphylaxie) {
        this.setDebutProphylaxie(debutProphylaxie);
        return this;
    }

    public void setDebutProphylaxie(LocalDate debutProphylaxie) {
        this.debutProphylaxie = debutProphylaxie;
    }

    public InjectionType getModaliteInjection() {
        return this.modaliteInjection;
    }

    public Fiche modaliteInjection(InjectionType modaliteInjection) {
        this.setModaliteInjection(modaliteInjection);
        return this;
    }

    public void setModaliteInjection(InjectionType modaliteInjection) {
        this.modaliteInjection = modaliteInjection;
    }

    public TraitementType getTypeTraitementSubstitutif() {
        return this.typeTraitementSubstitutif;
    }

    public Fiche typeTraitementSubstitutif(TraitementType typeTraitementSubstitutif) {
        this.setTypeTraitementSubstitutif(typeTraitementSubstitutif);
        return this;
    }

    public void setTypeTraitementSubstitutif(TraitementType typeTraitementSubstitutif) {
        this.typeTraitementSubstitutif = typeTraitementSubstitutif;
    }

    public Integer getAge1ereSubstitution() {
        return this.age1ereSubstitution;
    }

    public Fiche age1ereSubstitution(Integer age1ereSubstitution) {
        this.setAge1ereSubstitution(age1ereSubstitution);
        return this;
    }

    public void setAge1ereSubstitution(Integer age1ereSubstitution) {
        this.age1ereSubstitution = age1ereSubstitution;
    }

    public OuiNonNP getPsl() {
        return this.psl;
    }

    public Fiche psl(OuiNonNP psl) {
        this.setPsl(psl);
        return this;
    }

    public void setPsl(OuiNonNP psl) {
        this.psl = psl;
    }

    public OuiNonNP getPlasmaFraisCongele() {
        return this.plasmaFraisCongele;
    }

    public Fiche plasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.setPlasmaFraisCongele(plasmaFraisCongele);
        return this;
    }

    public void setPlasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.plasmaFraisCongele = plasmaFraisCongele;
    }

    public OuiNonNP getCryoprecipite() {
        return this.cryoprecipite;
    }

    public Fiche cryoprecipite(OuiNonNP cryoprecipite) {
        this.setCryoprecipite(cryoprecipite);
        return this;
    }

    public void setCryoprecipite(OuiNonNP cryoprecipite) {
        this.cryoprecipite = cryoprecipite;
    }

    public OuiNonNP getComplicationsOrthopediques() {
        return this.complicationsOrthopediques;
    }

    public Fiche complicationsOrthopediques(OuiNonNP complicationsOrthopediques) {
        this.setComplicationsOrthopediques(complicationsOrthopediques);
        return this;
    }

    public void setComplicationsOrthopediques(OuiNonNP complicationsOrthopediques) {
        this.complicationsOrthopediques = complicationsOrthopediques;
    }

    public OuiNonNP getComplicationInhibiteurs() {
        return this.complicationInhibiteurs;
    }

    public Fiche complicationInhibiteurs(OuiNonNP complicationInhibiteurs) {
        this.setComplicationInhibiteurs(complicationInhibiteurs);
        return this;
    }

    public void setComplicationInhibiteurs(OuiNonNP complicationInhibiteurs) {
        this.complicationInhibiteurs = complicationInhibiteurs;
    }

    public TestStatus getTestRecuperationFAH() {
        return this.testRecuperationFAH;
    }

    public Fiche testRecuperationFAH(TestStatus testRecuperationFAH) {
        this.setTestRecuperationFAH(testRecuperationFAH);
        return this;
    }

    public void setTestRecuperationFAH(TestStatus testRecuperationFAH) {
        this.testRecuperationFAH = testRecuperationFAH;
    }

    public TestResult getResultatTestRecuperation() {
        return this.resultatTestRecuperation;
    }

    public Fiche resultatTestRecuperation(TestResult resultatTestRecuperation) {
        this.setResultatTestRecuperation(resultatTestRecuperation);
        return this;
    }

    public void setResultatTestRecuperation(TestResult resultatTestRecuperation) {
        this.resultatTestRecuperation = resultatTestRecuperation;
    }

    public VieSocialeStatus getVieSociale() {
        return this.vieSociale;
    }

    public Fiche vieSociale(VieSocialeStatus vieSociale) {
        this.setVieSociale(vieSociale);
        return this;
    }

    public void setVieSociale(VieSocialeStatus vieSociale) {
        this.vieSociale = vieSociale;
    }

    public OuiNonNP getEtatMarital() {
        return this.etatMarital;
    }

    public Fiche etatMarital(OuiNonNP etatMarital) {
        this.setEtatMarital(etatMarital);
        return this;
    }

    public void setEtatMarital(OuiNonNP etatMarital) {
        this.etatMarital = etatMarital;
    }

    public OuiNonNP getEnfants() {
        return this.enfants;
    }

    public Fiche enfants(OuiNonNP enfants) {
        this.setEnfants(enfants);
        return this;
    }

    public void setEnfants(OuiNonNP enfants) {
        this.enfants = enfants;
    }

    public OuiNonNP getHandicap() {
        return this.handicap;
    }

    public Fiche handicap(OuiNonNP handicap) {
        this.setHandicap(handicap);
        return this;
    }

    public void setHandicap(OuiNonNP handicap) {
        this.handicap = handicap;
    }

    public String getTypeHandicap() {
        return this.typeHandicap;
    }

    public Fiche typeHandicap(String typeHandicap) {
        this.setTypeHandicap(typeHandicap);
        return this;
    }

    public void setTypeHandicap(String typeHandicap) {
        this.typeHandicap = typeHandicap;
    }

    public OuiNonNP getActiviteSportive() {
        return this.activiteSportive;
    }

    public Fiche activiteSportive(OuiNonNP activiteSportive) {
        this.setActiviteSportive(activiteSportive);
        return this;
    }

    public void setActiviteSportive(OuiNonNP activiteSportive) {
        this.activiteSportive = activiteSportive;
    }

    public String getTypeActiviteSportive() {
        return this.typeActiviteSportive;
    }

    public Fiche typeActiviteSportive(String typeActiviteSportive) {
        this.setTypeActiviteSportive(typeActiviteSportive);
        return this;
    }

    public void setTypeActiviteSportive(String typeActiviteSportive) {
        this.typeActiviteSportive = typeActiviteSportive;
    }

    public OuiNonNP getDecede() {
        return this.decede;
    }

    public Fiche decede(OuiNonNP decede) {
        this.setDecede(decede);
        return this;
    }

    public void setDecede(OuiNonNP decede) {
        this.decede = decede;
    }

    public String getCauseDateDeces() {
        return this.causeDateDeces;
    }

    public Fiche causeDateDeces(String causeDateDeces) {
        this.setCauseDateDeces(causeDateDeces);
        return this;
    }

    public void setCauseDateDeces(String causeDateDeces) {
        this.causeDateDeces = causeDateDeces;
    }

    public Hemarthrose getHemarthrose() {
        return this.hemarthrose;
    }

    public void setHemarthrose(Hemarthrose hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public Fiche hemarthrose(Hemarthrose hemarthrose) {
        this.setHemarthrose(hemarthrose);
        return this;
    }

    public HematomeSuperficiel getHematomeSuperficiel() {
        return this.hematomeSuperficiel;
    }

    public void setHematomeSuperficiel(HematomeSuperficiel hematomeSuperficiel) {
        this.hematomeSuperficiel = hematomeSuperficiel;
    }

    public Fiche hematomeSuperficiel(HematomeSuperficiel hematomeSuperficiel) {
        this.setHematomeSuperficiel(hematomeSuperficiel);
        return this;
    }

    public HematomePsoas getHematomePsoas() {
        return this.hematomePsoas;
    }

    public void setHematomePsoas(HematomePsoas hematomePsoas) {
        this.hematomePsoas = hematomePsoas;
    }

    public Fiche hematomePsoas(HematomePsoas hematomePsoas) {
        this.setHematomePsoas(hematomePsoas);
        return this;
    }

    public HemorragiesCutaneoMuqueuses getHemorragiesCutaneoMuqueuses() {
        return this.hemorragiesCutaneoMuqueuses;
    }

    public void setHemorragiesCutaneoMuqueuses(HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses) {
        this.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;
    }

    public Fiche hemorragiesCutaneoMuqueuses(HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses) {
        this.setHemorragiesCutaneoMuqueuses(hemorragiesCutaneoMuqueuses);
        return this;
    }

    public HemorragieVisceres getHemorragieVisceres() {
        return this.hemorragieVisceres;
    }

    public void setHemorragieVisceres(HemorragieVisceres hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public Fiche hemorragieVisceres(HemorragieVisceres hemorragieVisceres) {
        this.setHemorragieVisceres(hemorragieVisceres);
        return this;
    }

    public SaignementSNC getSaignementSNC() {
        return this.saignementSNC;
    }

    public void setSaignementSNC(SaignementSNC saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public Fiche saignementSNC(SaignementSNC saignementSNC) {
        this.setSaignementSNC(saignementSNC);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Fiche user(User user) {
        this.setUser(user);
        return this;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Fiche patient(Patient patient) {
        this.setPatient(patient);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fiche)) {
            return false;
        }
        return getId() != null && getId().equals(((Fiche) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fiche{" +
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
            "}";
    }
}
