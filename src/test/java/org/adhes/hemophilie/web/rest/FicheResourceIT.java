package org.adhes.hemophilie.web.rest;

import static org.adhes.hemophilie.domain.FicheAsserts.*;
import static org.adhes.hemophilie.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.adhes.hemophilie.IntegrationTest;
import org.adhes.hemophilie.domain.Fiche;
import org.adhes.hemophilie.domain.enumeration.CircumstanceDecouverte;
import org.adhes.hemophilie.domain.enumeration.DiagnosticType;
import org.adhes.hemophilie.domain.enumeration.FormeHemophilie;
import org.adhes.hemophilie.domain.enumeration.InjectionType;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.PriseEnChargeType;
import org.adhes.hemophilie.domain.enumeration.TestResult;
import org.adhes.hemophilie.domain.enumeration.TestStatus;
import org.adhes.hemophilie.domain.enumeration.TestStatus;
import org.adhes.hemophilie.domain.enumeration.TraitementType;
import org.adhes.hemophilie.domain.enumeration.VieSocialeStatus;
import org.adhes.hemophilie.repository.FicheRepository;
import org.adhes.hemophilie.repository.UserRepository;
import org.adhes.hemophilie.service.dto.FicheDTO;
import org.adhes.hemophilie.service.mapper.FicheMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link FicheResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheResourceIT {

    private static final String DEFAULT_DOSSIER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOSSIER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ORDRE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ORDRE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_INDEX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INDEX_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNEE_DIAGNOSTIC = 1;
    private static final Integer UPDATED_ANNEE_DIAGNOSTIC = 2;

    private static final DiagnosticType DEFAULT_DIAGNOSTIC = DiagnosticType.HEMOPHILIE;
    private static final DiagnosticType UPDATED_DIAGNOSTIC = DiagnosticType.WILLEBRAND;

    private static final String DEFAULT_AUTRE_DIAGNOSTIC = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_DIAGNOSTIC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_ENREGISTREMENT_REGISTRE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ENREGISTREMENT_REGISTRE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_CONSENTEMENT_REGISTRE = false;
    private static final Boolean UPDATED_CONSENTEMENT_REGISTRE = true;

    private static final OuiNonNP DEFAULT_PARENTS_CONSANGUINS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PARENTS_CONSANGUINS = OuiNonNP.NON;

    private static final String DEFAULT_DEGRE_PARENTE_CONSANGUINS = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CONSANGUINS = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CAS_SIMILAIRES_FAMILLE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CAS_SIMILAIRES_FAMILLE = OuiNonNP.NON;

    private static final Integer DEFAULT_NBRE_CAS_SIMILAIRES = 1;
    private static final Integer UPDATED_NBRE_CAS_SIMILAIRES = 2;

    private static final String DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CAS_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.NON;

    private static final Integer DEFAULT_NBRE_CAS_DECES = 1;
    private static final Integer UPDATED_NBRE_CAS_DECES = 2;

    private static final FormeHemophilie DEFAULT_FORME_HEMOPHILIE = FormeHemophilie.SPORADIQUE;
    private static final FormeHemophilie UPDATED_FORME_HEMOPHILIE = FormeHemophilie.HEREDITAIRE;

    private static final Integer DEFAULT_NBRE_FRERES = 1;
    private static final Integer UPDATED_NBRE_FRERES = 2;

    private static final Integer DEFAULT_NBRE_SOEURS = 1;
    private static final Integer UPDATED_NBRE_SOEURS = 2;

    private static final LocalDate DEFAULT_DATE_TEST_CONFIRMATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TEST_CONFIRMATION = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_HEMOGRAMME_HB = 1D;
    private static final Double UPDATED_HEMOGRAMME_HB = 2D;

    private static final Double DEFAULT_HEMOGRAMME_HT = 1D;
    private static final Double UPDATED_HEMOGRAMME_HT = 2D;

    private static final Integer DEFAULT_PLAQUETTES = 1;
    private static final Integer UPDATED_PLAQUETTES = 2;

    private static final Double DEFAULT_TP = 1D;
    private static final Double UPDATED_TP = 2D;

    private static final Double DEFAULT_FIBRINOGENE = 1D;
    private static final Double UPDATED_FIBRINOGENE = 2D;

    private static final Double DEFAULT_TCA_MT = 1D;
    private static final Double UPDATED_TCA_MT = 2D;

    private static final Double DEFAULT_TCA_MT_2_H = 1D;
    private static final Double UPDATED_TCA_MT_2_H = 2D;

    private static final Double DEFAULT_TCA_TEMOIN_2_H = 1D;
    private static final Double UPDATED_TCA_TEMOIN_2_H = 2D;

    private static final TestStatus DEFAULT_TS = TestStatus.FAIT;
    private static final TestStatus UPDATED_TS = TestStatus.NON_FAIT;

    private static final CircumstanceDecouverte DEFAULT_CIRCUMSTANCE_DECOUVERTE = CircumstanceDecouverte.FORTUITE;
    private static final CircumstanceDecouverte UPDATED_CIRCUMSTANCE_DECOUVERTE = CircumstanceDecouverte.FAMILIALE;

    private static final LocalDate DEFAULT_DATE_1_ER_SIGNE_CLINIQUE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_1_ER_SIGNE_CLINIQUE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE_DIAGNOSTIC = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC = 2;

    private static final PriseEnChargeType DEFAULT_PRISE_EN_CHARGE = PriseEnChargeType.A_LA_DEMANDE;
    private static final PriseEnChargeType UPDATED_PRISE_EN_CHARGE = PriseEnChargeType.PROPHYLAXIE;

    private static final String DEFAULT_CAUSE_PRISE_EN_CHARGE = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_PRISE_EN_CHARGE = "BBBBBBBBBB";

    private static final String DEFAULT_DOSE_PROPHYLAXIE = "AAAAAAAAAA";
    private static final String UPDATED_DOSE_PROPHYLAXIE = "BBBBBBBBBB";

    private static final Integer DEFAULT_FREQUENCE_PROPHYLAXIE = 1;
    private static final Integer UPDATED_FREQUENCE_PROPHYLAXIE = 2;

    private static final LocalDate DEFAULT_DEBUT_PROPHYLAXIE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEBUT_PROPHYLAXIE = LocalDate.now(ZoneId.systemDefault());

    private static final InjectionType DEFAULT_MODALITE_INJECTION = InjectionType.AUTO_INJECTION;
    private static final InjectionType UPDATED_MODALITE_INJECTION = InjectionType.INJECTION_PARENT;

    private static final TraitementType DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF = TraitementType.PLASMATIQUE;
    private static final TraitementType UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF = TraitementType.RECOMBINANT;

    private static final Integer DEFAULT_AGE_1_ERE_SUBSTITUTION = 1;
    private static final Integer UPDATED_AGE_1_ERE_SUBSTITUTION = 2;

    private static final OuiNonNP DEFAULT_PSL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PSL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_PLASMA_FRAIS_CONGELE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PLASMA_FRAIS_CONGELE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CRYOPRECIPITE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CRYOPRECIPITE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_COMPLICATIONS_ORTHOPEDIQUES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_COMPLICATIONS_ORTHOPEDIQUES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_COMPLICATION_INHIBITEURS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_COMPLICATION_INHIBITEURS = OuiNonNP.NON;

    private static final TestStatus DEFAULT_TEST_RECUPERATION_FAH = TestStatus.FAIT;
    private static final TestStatus UPDATED_TEST_RECUPERATION_FAH = TestStatus.NON_FAIT;

    private static final TestResult DEFAULT_RESULTAT_TEST_RECUPERATION = TestResult.NORMAL;
    private static final TestResult UPDATED_RESULTAT_TEST_RECUPERATION = TestResult.DIMINUE;

    private static final VieSocialeStatus DEFAULT_VIE_SOCIALE = VieSocialeStatus.PRESCOLAIRE;
    private static final VieSocialeStatus UPDATED_VIE_SOCIALE = VieSocialeStatus.SCOLARISE;

    private static final OuiNonNP DEFAULT_ETAT_MARITAL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ETAT_MARITAL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ENFANTS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ENFANTS = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_HANDICAP = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HANDICAP = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_HANDICAP = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HANDICAP = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ACTIVITE_SPORTIVE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ACTIVITE_SPORTIVE = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_ACTIVITE_SPORTIVE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ACTIVITE_SPORTIVE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_DECEDE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DECEDE = OuiNonNP.NON;

    private static final String DEFAULT_CAUSE_DATE_DECES = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_DATE_DECES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fiches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FicheRepository ficheRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FicheMapper ficheMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheMockMvc;

    private Fiche fiche;

    private Fiche insertedFiche;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fiche createEntity() {
        return new Fiche()
            .dossierNumber(DEFAULT_DOSSIER_NUMBER)
            .ordreNumber(DEFAULT_ORDRE_NUMBER)
            .indexNumber(DEFAULT_INDEX_NUMBER)
            .anneeDiagnostic(DEFAULT_ANNEE_DIAGNOSTIC)
            .diagnostic(DEFAULT_DIAGNOSTIC)
            .autreDiagnostic(DEFAULT_AUTRE_DIAGNOSTIC)
            .dateEnregistrementRegistre(DEFAULT_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(DEFAULT_CONSENTEMENT_REGISTRE)
            .parentsConsanguins(DEFAULT_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(DEFAULT_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(DEFAULT_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(DEFAULT_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(DEFAULT_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(DEFAULT_NBRE_CAS_DECES)
            .formeHemophilie(DEFAULT_FORME_HEMOPHILIE)
            .nbreFreres(DEFAULT_NBRE_FRERES)
            .nbreSoeurs(DEFAULT_NBRE_SOEURS)
            .dateTestConfirmation(DEFAULT_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(DEFAULT_HEMOGRAMME_HB)
            .hemogrammeHt(DEFAULT_HEMOGRAMME_HT)
            .plaquettes(DEFAULT_PLAQUETTES)
            .tp(DEFAULT_TP)
            .fibrinogene(DEFAULT_FIBRINOGENE)
            .tcaMT(DEFAULT_TCA_MT)
            .tcaMT2h(DEFAULT_TCA_MT_2_H)
            .tcaTemoin2h(DEFAULT_TCA_TEMOIN_2_H)
            .ts(DEFAULT_TS)
            .circumstanceDecouverte(DEFAULT_CIRCUMSTANCE_DECOUVERTE)
            .date1erSigneClinique(DEFAULT_DATE_1_ER_SIGNE_CLINIQUE)
            .ageDiagnostic(DEFAULT_AGE_DIAGNOSTIC)
            .priseEnCharge(DEFAULT_PRISE_EN_CHARGE)
            .causePriseEnCharge(DEFAULT_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(DEFAULT_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(DEFAULT_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(DEFAULT_DEBUT_PROPHYLAXIE)
            .modaliteInjection(DEFAULT_MODALITE_INJECTION)
            .typeTraitementSubstitutif(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF)
            .age1ereSubstitution(DEFAULT_AGE_1_ERE_SUBSTITUTION)
            .psl(DEFAULT_PSL)
            .plasmaFraisCongele(DEFAULT_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(DEFAULT_CRYOPRECIPITE)
            .complicationsOrthopediques(DEFAULT_COMPLICATIONS_ORTHOPEDIQUES)
            .complicationInhibiteurs(DEFAULT_COMPLICATION_INHIBITEURS)
            .testRecuperationFAH(DEFAULT_TEST_RECUPERATION_FAH)
            .resultatTestRecuperation(DEFAULT_RESULTAT_TEST_RECUPERATION)
            .vieSociale(DEFAULT_VIE_SOCIALE)
            .etatMarital(DEFAULT_ETAT_MARITAL)
            .enfants(DEFAULT_ENFANTS)
            .handicap(DEFAULT_HANDICAP)
            .typeHandicap(DEFAULT_TYPE_HANDICAP)
            .activiteSportive(DEFAULT_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(DEFAULT_TYPE_ACTIVITE_SPORTIVE)
            .decede(DEFAULT_DECEDE)
            .causeDateDeces(DEFAULT_CAUSE_DATE_DECES);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fiche createUpdatedEntity() {
        return new Fiche()
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .anneeDiagnostic(UPDATED_ANNEE_DIAGNOSTIC)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(UPDATED_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(UPDATED_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(UPDATED_NBRE_CAS_DECES)
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .nbreFreres(UPDATED_NBRE_FRERES)
            .nbreSoeurs(UPDATED_NBRE_SOEURS)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(UPDATED_HEMOGRAMME_HB)
            .hemogrammeHt(UPDATED_HEMOGRAMME_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMT2h(UPDATED_TCA_MT_2_H)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .ts(UPDATED_TS)
            .circumstanceDecouverte(UPDATED_CIRCUMSTANCE_DECOUVERTE)
            .date1erSigneClinique(UPDATED_DATE_1_ER_SIGNE_CLINIQUE)
            .ageDiagnostic(UPDATED_AGE_DIAGNOSTIC)
            .priseEnCharge(UPDATED_PRISE_EN_CHARGE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(UPDATED_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(UPDATED_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(UPDATED_DEBUT_PROPHYLAXIE)
            .modaliteInjection(UPDATED_MODALITE_INJECTION)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .age1ereSubstitution(UPDATED_AGE_1_ERE_SUBSTITUTION)
            .psl(UPDATED_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .complicationsOrthopediques(UPDATED_COMPLICATIONS_ORTHOPEDIQUES)
            .complicationInhibiteurs(UPDATED_COMPLICATION_INHIBITEURS)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .resultatTestRecuperation(UPDATED_RESULTAT_TEST_RECUPERATION)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .etatMarital(UPDATED_ETAT_MARITAL)
            .enfants(UPDATED_ENFANTS)
            .handicap(UPDATED_HANDICAP)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .activiteSportive(UPDATED_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .decede(UPDATED_DECEDE)
            .causeDateDeces(UPDATED_CAUSE_DATE_DECES);
    }

    @BeforeEach
    public void initTest() {
        fiche = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFiche != null) {
            ficheRepository.delete(insertedFiche);
            insertedFiche = null;
        }
    }

    @Test
    @Transactional
    void createFiche() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);
        var returnedFicheDTO = om.readValue(
            restFicheMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FicheDTO.class
        );

        // Validate the Fiche in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFiche = ficheMapper.toEntity(returnedFicheDTO);
        assertFicheUpdatableFieldsEquals(returnedFiche, getPersistedFiche(returnedFiche));

        insertedFiche = returnedFiche;
    }

    @Test
    @Transactional
    void createFicheWithExistingId() throws Exception {
        // Create the Fiche with an existing ID
        fiche.setId(1L);
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDossierNumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fiche.setDossierNumber(null);

        // Create the Fiche, which fails.
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        restFicheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiagnosticIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fiche.setDiagnostic(null);

        // Create the Fiche, which fails.
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        restFicheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPriseEnChargeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        fiche.setPriseEnCharge(null);

        // Create the Fiche, which fails.
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        restFicheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFiches() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        // Get all the ficheList
        restFicheMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fiche.getId().intValue())))
            .andExpect(jsonPath("$.[*].dossierNumber").value(hasItem(DEFAULT_DOSSIER_NUMBER)))
            .andExpect(jsonPath("$.[*].ordreNumber").value(hasItem(DEFAULT_ORDRE_NUMBER)))
            .andExpect(jsonPath("$.[*].indexNumber").value(hasItem(DEFAULT_INDEX_NUMBER)))
            .andExpect(jsonPath("$.[*].anneeDiagnostic").value(hasItem(DEFAULT_ANNEE_DIAGNOSTIC)))
            .andExpect(jsonPath("$.[*].diagnostic").value(hasItem(DEFAULT_DIAGNOSTIC.toString())))
            .andExpect(jsonPath("$.[*].autreDiagnostic").value(hasItem(DEFAULT_AUTRE_DIAGNOSTIC)))
            .andExpect(jsonPath("$.[*].dateEnregistrementRegistre").value(hasItem(DEFAULT_DATE_ENREGISTREMENT_REGISTRE.toString())))
            .andExpect(jsonPath("$.[*].consentementRegistre").value(hasItem(DEFAULT_CONSENTEMENT_REGISTRE.booleanValue())))
            .andExpect(jsonPath("$.[*].parentsConsanguins").value(hasItem(DEFAULT_PARENTS_CONSANGUINS.toString())))
            .andExpect(jsonPath("$.[*].degreParenteConsanguins").value(hasItem(DEFAULT_DEGRE_PARENTE_CONSANGUINS)))
            .andExpect(jsonPath("$.[*].casSimilairesFamille").value(hasItem(DEFAULT_CAS_SIMILAIRES_FAMILLE.toString())))
            .andExpect(jsonPath("$.[*].nbreCasSimilaires").value(hasItem(DEFAULT_NBRE_CAS_SIMILAIRES)))
            .andExpect(jsonPath("$.[*].degreParenteCasSimilaires").value(hasItem(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES)))
            .andExpect(jsonPath("$.[*].casDecesSyndromeHemorragique").value(hasItem(DEFAULT_CAS_DECES_SYNDROME_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].nbreCasDeces").value(hasItem(DEFAULT_NBRE_CAS_DECES)))
            .andExpect(jsonPath("$.[*].formeHemophilie").value(hasItem(DEFAULT_FORME_HEMOPHILIE.toString())))
            .andExpect(jsonPath("$.[*].nbreFreres").value(hasItem(DEFAULT_NBRE_FRERES)))
            .andExpect(jsonPath("$.[*].nbreSoeurs").value(hasItem(DEFAULT_NBRE_SOEURS)))
            .andExpect(jsonPath("$.[*].dateTestConfirmation").value(hasItem(DEFAULT_DATE_TEST_CONFIRMATION.toString())))
            .andExpect(jsonPath("$.[*].hemogrammeHb").value(hasItem(DEFAULT_HEMOGRAMME_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].hemogrammeHt").value(hasItem(DEFAULT_HEMOGRAMME_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].plaquettes").value(hasItem(DEFAULT_PLAQUETTES)))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].fibrinogene").value(hasItem(DEFAULT_FIBRINOGENE.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT").value(hasItem(DEFAULT_TCA_MT.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT2h").value(hasItem(DEFAULT_TCA_MT_2_H.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaTemoin2h").value(hasItem(DEFAULT_TCA_TEMOIN_2_H.doubleValue())))
            .andExpect(jsonPath("$.[*].ts").value(hasItem(DEFAULT_TS.toString())))
            .andExpect(jsonPath("$.[*].circumstanceDecouverte").value(hasItem(DEFAULT_CIRCUMSTANCE_DECOUVERTE.toString())))
            .andExpect(jsonPath("$.[*].date1erSigneClinique").value(hasItem(DEFAULT_DATE_1_ER_SIGNE_CLINIQUE.toString())))
            .andExpect(jsonPath("$.[*].ageDiagnostic").value(hasItem(DEFAULT_AGE_DIAGNOSTIC)))
            .andExpect(jsonPath("$.[*].priseEnCharge").value(hasItem(DEFAULT_PRISE_EN_CHARGE.toString())))
            .andExpect(jsonPath("$.[*].causePriseEnCharge").value(hasItem(DEFAULT_CAUSE_PRISE_EN_CHARGE)))
            .andExpect(jsonPath("$.[*].doseProphylaxie").value(hasItem(DEFAULT_DOSE_PROPHYLAXIE)))
            .andExpect(jsonPath("$.[*].frequenceProphylaxie").value(hasItem(DEFAULT_FREQUENCE_PROPHYLAXIE)))
            .andExpect(jsonPath("$.[*].debutProphylaxie").value(hasItem(DEFAULT_DEBUT_PROPHYLAXIE.toString())))
            .andExpect(jsonPath("$.[*].modaliteInjection").value(hasItem(DEFAULT_MODALITE_INJECTION.toString())))
            .andExpect(jsonPath("$.[*].typeTraitementSubstitutif").value(hasItem(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF.toString())))
            .andExpect(jsonPath("$.[*].age1ereSubstitution").value(hasItem(DEFAULT_AGE_1_ERE_SUBSTITUTION)))
            .andExpect(jsonPath("$.[*].psl").value(hasItem(DEFAULT_PSL.toString())))
            .andExpect(jsonPath("$.[*].plasmaFraisCongele").value(hasItem(DEFAULT_PLASMA_FRAIS_CONGELE.toString())))
            .andExpect(jsonPath("$.[*].cryoprecipite").value(hasItem(DEFAULT_CRYOPRECIPITE.toString())))
            .andExpect(jsonPath("$.[*].complicationsOrthopediques").value(hasItem(DEFAULT_COMPLICATIONS_ORTHOPEDIQUES.toString())))
            .andExpect(jsonPath("$.[*].complicationInhibiteurs").value(hasItem(DEFAULT_COMPLICATION_INHIBITEURS.toString())))
            .andExpect(jsonPath("$.[*].testRecuperationFAH").value(hasItem(DEFAULT_TEST_RECUPERATION_FAH.toString())))
            .andExpect(jsonPath("$.[*].resultatTestRecuperation").value(hasItem(DEFAULT_RESULTAT_TEST_RECUPERATION.toString())))
            .andExpect(jsonPath("$.[*].vieSociale").value(hasItem(DEFAULT_VIE_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].etatMarital").value(hasItem(DEFAULT_ETAT_MARITAL.toString())))
            .andExpect(jsonPath("$.[*].enfants").value(hasItem(DEFAULT_ENFANTS.toString())))
            .andExpect(jsonPath("$.[*].handicap").value(hasItem(DEFAULT_HANDICAP.toString())))
            .andExpect(jsonPath("$.[*].typeHandicap").value(hasItem(DEFAULT_TYPE_HANDICAP)))
            .andExpect(jsonPath("$.[*].activiteSportive").value(hasItem(DEFAULT_ACTIVITE_SPORTIVE.toString())))
            .andExpect(jsonPath("$.[*].typeActiviteSportive").value(hasItem(DEFAULT_TYPE_ACTIVITE_SPORTIVE)))
            .andExpect(jsonPath("$.[*].decede").value(hasItem(DEFAULT_DECEDE.toString())))
            .andExpect(jsonPath("$.[*].causeDateDeces").value(hasItem(DEFAULT_CAUSE_DATE_DECES)));
    }

    @Test
    @Transactional
    void getFiche() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        // Get the fiche
        restFicheMockMvc
            .perform(get(ENTITY_API_URL_ID, fiche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fiche.getId().intValue()))
            .andExpect(jsonPath("$.dossierNumber").value(DEFAULT_DOSSIER_NUMBER))
            .andExpect(jsonPath("$.ordreNumber").value(DEFAULT_ORDRE_NUMBER))
            .andExpect(jsonPath("$.indexNumber").value(DEFAULT_INDEX_NUMBER))
            .andExpect(jsonPath("$.anneeDiagnostic").value(DEFAULT_ANNEE_DIAGNOSTIC))
            .andExpect(jsonPath("$.diagnostic").value(DEFAULT_DIAGNOSTIC.toString()))
            .andExpect(jsonPath("$.autreDiagnostic").value(DEFAULT_AUTRE_DIAGNOSTIC))
            .andExpect(jsonPath("$.dateEnregistrementRegistre").value(DEFAULT_DATE_ENREGISTREMENT_REGISTRE.toString()))
            .andExpect(jsonPath("$.consentementRegistre").value(DEFAULT_CONSENTEMENT_REGISTRE.booleanValue()))
            .andExpect(jsonPath("$.parentsConsanguins").value(DEFAULT_PARENTS_CONSANGUINS.toString()))
            .andExpect(jsonPath("$.degreParenteConsanguins").value(DEFAULT_DEGRE_PARENTE_CONSANGUINS))
            .andExpect(jsonPath("$.casSimilairesFamille").value(DEFAULT_CAS_SIMILAIRES_FAMILLE.toString()))
            .andExpect(jsonPath("$.nbreCasSimilaires").value(DEFAULT_NBRE_CAS_SIMILAIRES))
            .andExpect(jsonPath("$.degreParenteCasSimilaires").value(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES))
            .andExpect(jsonPath("$.casDecesSyndromeHemorragique").value(DEFAULT_CAS_DECES_SYNDROME_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.nbreCasDeces").value(DEFAULT_NBRE_CAS_DECES))
            .andExpect(jsonPath("$.formeHemophilie").value(DEFAULT_FORME_HEMOPHILIE.toString()))
            .andExpect(jsonPath("$.nbreFreres").value(DEFAULT_NBRE_FRERES))
            .andExpect(jsonPath("$.nbreSoeurs").value(DEFAULT_NBRE_SOEURS))
            .andExpect(jsonPath("$.dateTestConfirmation").value(DEFAULT_DATE_TEST_CONFIRMATION.toString()))
            .andExpect(jsonPath("$.hemogrammeHb").value(DEFAULT_HEMOGRAMME_HB.doubleValue()))
            .andExpect(jsonPath("$.hemogrammeHt").value(DEFAULT_HEMOGRAMME_HT.doubleValue()))
            .andExpect(jsonPath("$.plaquettes").value(DEFAULT_PLAQUETTES))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.fibrinogene").value(DEFAULT_FIBRINOGENE.doubleValue()))
            .andExpect(jsonPath("$.tcaMT").value(DEFAULT_TCA_MT.doubleValue()))
            .andExpect(jsonPath("$.tcaMT2h").value(DEFAULT_TCA_MT_2_H.doubleValue()))
            .andExpect(jsonPath("$.tcaTemoin2h").value(DEFAULT_TCA_TEMOIN_2_H.doubleValue()))
            .andExpect(jsonPath("$.ts").value(DEFAULT_TS.toString()))
            .andExpect(jsonPath("$.circumstanceDecouverte").value(DEFAULT_CIRCUMSTANCE_DECOUVERTE.toString()))
            .andExpect(jsonPath("$.date1erSigneClinique").value(DEFAULT_DATE_1_ER_SIGNE_CLINIQUE.toString()))
            .andExpect(jsonPath("$.ageDiagnostic").value(DEFAULT_AGE_DIAGNOSTIC))
            .andExpect(jsonPath("$.priseEnCharge").value(DEFAULT_PRISE_EN_CHARGE.toString()))
            .andExpect(jsonPath("$.causePriseEnCharge").value(DEFAULT_CAUSE_PRISE_EN_CHARGE))
            .andExpect(jsonPath("$.doseProphylaxie").value(DEFAULT_DOSE_PROPHYLAXIE))
            .andExpect(jsonPath("$.frequenceProphylaxie").value(DEFAULT_FREQUENCE_PROPHYLAXIE))
            .andExpect(jsonPath("$.debutProphylaxie").value(DEFAULT_DEBUT_PROPHYLAXIE.toString()))
            .andExpect(jsonPath("$.modaliteInjection").value(DEFAULT_MODALITE_INJECTION.toString()))
            .andExpect(jsonPath("$.typeTraitementSubstitutif").value(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF.toString()))
            .andExpect(jsonPath("$.age1ereSubstitution").value(DEFAULT_AGE_1_ERE_SUBSTITUTION))
            .andExpect(jsonPath("$.psl").value(DEFAULT_PSL.toString()))
            .andExpect(jsonPath("$.plasmaFraisCongele").value(DEFAULT_PLASMA_FRAIS_CONGELE.toString()))
            .andExpect(jsonPath("$.cryoprecipite").value(DEFAULT_CRYOPRECIPITE.toString()))
            .andExpect(jsonPath("$.complicationsOrthopediques").value(DEFAULT_COMPLICATIONS_ORTHOPEDIQUES.toString()))
            .andExpect(jsonPath("$.complicationInhibiteurs").value(DEFAULT_COMPLICATION_INHIBITEURS.toString()))
            .andExpect(jsonPath("$.testRecuperationFAH").value(DEFAULT_TEST_RECUPERATION_FAH.toString()))
            .andExpect(jsonPath("$.resultatTestRecuperation").value(DEFAULT_RESULTAT_TEST_RECUPERATION.toString()))
            .andExpect(jsonPath("$.vieSociale").value(DEFAULT_VIE_SOCIALE.toString()))
            .andExpect(jsonPath("$.etatMarital").value(DEFAULT_ETAT_MARITAL.toString()))
            .andExpect(jsonPath("$.enfants").value(DEFAULT_ENFANTS.toString()))
            .andExpect(jsonPath("$.handicap").value(DEFAULT_HANDICAP.toString()))
            .andExpect(jsonPath("$.typeHandicap").value(DEFAULT_TYPE_HANDICAP))
            .andExpect(jsonPath("$.activiteSportive").value(DEFAULT_ACTIVITE_SPORTIVE.toString()))
            .andExpect(jsonPath("$.typeActiviteSportive").value(DEFAULT_TYPE_ACTIVITE_SPORTIVE))
            .andExpect(jsonPath("$.decede").value(DEFAULT_DECEDE.toString()))
            .andExpect(jsonPath("$.causeDateDeces").value(DEFAULT_CAUSE_DATE_DECES));
    }

    @Test
    @Transactional
    void getNonExistingFiche() throws Exception {
        // Get the fiche
        restFicheMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFiche() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fiche
        Fiche updatedFiche = ficheRepository.findById(fiche.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFiche are not directly saved in db
        em.detach(updatedFiche);
        updatedFiche
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .anneeDiagnostic(UPDATED_ANNEE_DIAGNOSTIC)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(UPDATED_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(UPDATED_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(UPDATED_NBRE_CAS_DECES)
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .nbreFreres(UPDATED_NBRE_FRERES)
            .nbreSoeurs(UPDATED_NBRE_SOEURS)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(UPDATED_HEMOGRAMME_HB)
            .hemogrammeHt(UPDATED_HEMOGRAMME_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMT2h(UPDATED_TCA_MT_2_H)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .ts(UPDATED_TS)
            .circumstanceDecouverte(UPDATED_CIRCUMSTANCE_DECOUVERTE)
            .date1erSigneClinique(UPDATED_DATE_1_ER_SIGNE_CLINIQUE)
            .ageDiagnostic(UPDATED_AGE_DIAGNOSTIC)
            .priseEnCharge(UPDATED_PRISE_EN_CHARGE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(UPDATED_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(UPDATED_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(UPDATED_DEBUT_PROPHYLAXIE)
            .modaliteInjection(UPDATED_MODALITE_INJECTION)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .age1ereSubstitution(UPDATED_AGE_1_ERE_SUBSTITUTION)
            .psl(UPDATED_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .complicationsOrthopediques(UPDATED_COMPLICATIONS_ORTHOPEDIQUES)
            .complicationInhibiteurs(UPDATED_COMPLICATION_INHIBITEURS)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .resultatTestRecuperation(UPDATED_RESULTAT_TEST_RECUPERATION)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .etatMarital(UPDATED_ETAT_MARITAL)
            .enfants(UPDATED_ENFANTS)
            .handicap(UPDATED_HANDICAP)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .activiteSportive(UPDATED_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .decede(UPDATED_DECEDE)
            .causeDateDeces(UPDATED_CAUSE_DATE_DECES);
        FicheDTO ficheDTO = ficheMapper.toDto(updatedFiche);

        restFicheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheDTO))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFicheToMatchAllProperties(updatedFiche);
    }

    @Test
    @Transactional
    void putNonExistingFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheWithPatch() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fiche using partial update
        Fiche partialUpdatedFiche = new Fiche();
        partialUpdatedFiche.setId(fiche.getId());

        partialUpdatedFiche
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(UPDATED_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(UPDATED_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .nbreCasDeces(UPDATED_NBRE_CAS_DECES)
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(UPDATED_HEMOGRAMME_HB)
            .hemogrammeHt(UPDATED_HEMOGRAMME_HT)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT2h(UPDATED_TCA_MT_2_H)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .date1erSigneClinique(UPDATED_DATE_1_ER_SIGNE_CLINIQUE)
            .ageDiagnostic(UPDATED_AGE_DIAGNOSTIC)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .debutProphylaxie(UPDATED_DEBUT_PROPHYLAXIE)
            .modaliteInjection(UPDATED_MODALITE_INJECTION)
            .psl(UPDATED_PSL)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .decede(UPDATED_DECEDE);

        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiche.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFiche))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedFiche, fiche), getPersistedFiche(fiche));
    }

    @Test
    @Transactional
    void fullUpdateFicheWithPatch() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fiche using partial update
        Fiche partialUpdatedFiche = new Fiche();
        partialUpdatedFiche.setId(fiche.getId());

        partialUpdatedFiche
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .anneeDiagnostic(UPDATED_ANNEE_DIAGNOSTIC)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(UPDATED_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(UPDATED_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(UPDATED_NBRE_CAS_DECES)
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .nbreFreres(UPDATED_NBRE_FRERES)
            .nbreSoeurs(UPDATED_NBRE_SOEURS)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(UPDATED_HEMOGRAMME_HB)
            .hemogrammeHt(UPDATED_HEMOGRAMME_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMT2h(UPDATED_TCA_MT_2_H)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .ts(UPDATED_TS)
            .circumstanceDecouverte(UPDATED_CIRCUMSTANCE_DECOUVERTE)
            .date1erSigneClinique(UPDATED_DATE_1_ER_SIGNE_CLINIQUE)
            .ageDiagnostic(UPDATED_AGE_DIAGNOSTIC)
            .priseEnCharge(UPDATED_PRISE_EN_CHARGE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(UPDATED_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(UPDATED_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(UPDATED_DEBUT_PROPHYLAXIE)
            .modaliteInjection(UPDATED_MODALITE_INJECTION)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .age1ereSubstitution(UPDATED_AGE_1_ERE_SUBSTITUTION)
            .psl(UPDATED_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .complicationsOrthopediques(UPDATED_COMPLICATIONS_ORTHOPEDIQUES)
            .complicationInhibiteurs(UPDATED_COMPLICATION_INHIBITEURS)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .resultatTestRecuperation(UPDATED_RESULTAT_TEST_RECUPERATION)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .etatMarital(UPDATED_ETAT_MARITAL)
            .enfants(UPDATED_ENFANTS)
            .handicap(UPDATED_HANDICAP)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .activiteSportive(UPDATED_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .decede(UPDATED_DECEDE)
            .causeDateDeces(UPDATED_CAUSE_DATE_DECES);

        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiche.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFiche))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheUpdatableFieldsEquals(partialUpdatedFiche, getPersistedFiche(partialUpdatedFiche));
    }

    @Test
    @Transactional
    void patchNonExistingFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ficheDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // Create the Fiche
        FicheDTO ficheDTO = ficheMapper.toDto(fiche);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(ficheDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFiche() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fiche
        restFicheMockMvc
            .perform(delete(ENTITY_API_URL_ID, fiche.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return ficheRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Fiche getPersistedFiche(Fiche fiche) {
        return ficheRepository.findById(fiche.getId()).orElseThrow();
    }

    protected void assertPersistedFicheToMatchAllProperties(Fiche expectedFiche) {
        assertFicheAllPropertiesEquals(expectedFiche, getPersistedFiche(expectedFiche));
    }

    protected void assertPersistedFicheToMatchUpdatableProperties(Fiche expectedFiche) {
        assertFicheAllUpdatablePropertiesEquals(expectedFiche, getPersistedFiche(expectedFiche));
    }
}
