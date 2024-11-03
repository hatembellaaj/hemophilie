package org.adhes.hemophilie.web.rest;

import static org.adhes.hemophilie.domain.SaignementSNCAsserts.*;
import static org.adhes.hemophilie.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.adhes.hemophilie.IntegrationTest;
import org.adhes.hemophilie.domain.SaignementSNC;
import org.adhes.hemophilie.domain.enumeration.EvolutionSNC;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.repository.SaignementSNCRepository;
import org.adhes.hemophilie.service.dto.SaignementSNCDTO;
import org.adhes.hemophilie.service.mapper.SaignementSNCMapper;
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
 * Integration tests for the {@link SaignementSNCResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SaignementSNCResourceIT {

    private static final OuiNonNP DEFAULT_REPONSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_REPONSE = OuiNonNP.NON;

    private static final EvolutionSNC DEFAULT_EVOLUTION = EvolutionSNC.REGRESSION;
    private static final EvolutionSNC UPDATED_EVOLUTION = EvolutionSNC.DECES;

    private static final String ENTITY_API_URL = "/api/saignement-sncs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SaignementSNCRepository saignementSNCRepository;

    @Autowired
    private SaignementSNCMapper saignementSNCMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSaignementSNCMockMvc;

    private SaignementSNC saignementSNC;

    private SaignementSNC insertedSaignementSNC;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SaignementSNC createEntity() {
        return new SaignementSNC().reponse(DEFAULT_REPONSE).evolution(DEFAULT_EVOLUTION);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SaignementSNC createUpdatedEntity() {
        return new SaignementSNC().reponse(UPDATED_REPONSE).evolution(UPDATED_EVOLUTION);
    }

    @BeforeEach
    public void initTest() {
        saignementSNC = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedSaignementSNC != null) {
            saignementSNCRepository.delete(insertedSaignementSNC);
            insertedSaignementSNC = null;
        }
    }

    @Test
    @Transactional
    void createSaignementSNC() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SaignementSNC
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(saignementSNC);
        var returnedSaignementSNCDTO = om.readValue(
            restSaignementSNCMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(saignementSNCDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SaignementSNCDTO.class
        );

        // Validate the SaignementSNC in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSaignementSNC = saignementSNCMapper.toEntity(returnedSaignementSNCDTO);
        assertSaignementSNCUpdatableFieldsEquals(returnedSaignementSNC, getPersistedSaignementSNC(returnedSaignementSNC));

        insertedSaignementSNC = returnedSaignementSNC;
    }

    @Test
    @Transactional
    void createSaignementSNCWithExistingId() throws Exception {
        // Create the SaignementSNC with an existing ID
        saignementSNC.setId(1L);
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(saignementSNC);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSaignementSNCMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(saignementSNCDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SaignementSNC in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSaignementSNCS() throws Exception {
        // Initialize the database
        insertedSaignementSNC = saignementSNCRepository.saveAndFlush(saignementSNC);

        // Get all the saignementSNCList
        restSaignementSNCMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(saignementSNC.getId().intValue())))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].evolution").value(hasItem(DEFAULT_EVOLUTION.toString())));
    }

    @Test
    @Transactional
    void getSaignementSNC() throws Exception {
        // Initialize the database
        insertedSaignementSNC = saignementSNCRepository.saveAndFlush(saignementSNC);

        // Get the saignementSNC
        restSaignementSNCMockMvc
            .perform(get(ENTITY_API_URL_ID, saignementSNC.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(saignementSNC.getId().intValue()))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE.toString()))
            .andExpect(jsonPath("$.evolution").value(DEFAULT_EVOLUTION.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSaignementSNC() throws Exception {
        // Get the saignementSNC
        restSaignementSNCMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSaignementSNC() throws Exception {
        // Initialize the database
        insertedSaignementSNC = saignementSNCRepository.saveAndFlush(saignementSNC);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the saignementSNC
        SaignementSNC updatedSaignementSNC = saignementSNCRepository.findById(saignementSNC.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSaignementSNC are not directly saved in db
        em.detach(updatedSaignementSNC);
        updatedSaignementSNC.reponse(UPDATED_REPONSE).evolution(UPDATED_EVOLUTION);
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(updatedSaignementSNC);

        restSaignementSNCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, saignementSNCDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(saignementSNCDTO))
            )
            .andExpect(status().isOk());

        // Validate the SaignementSNC in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSaignementSNCToMatchAllProperties(updatedSaignementSNC);
    }

    @Test
    @Transactional
    void putNonExistingSaignementSNC() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        saignementSNC.setId(longCount.incrementAndGet());

        // Create the SaignementSNC
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(saignementSNC);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSaignementSNCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, saignementSNCDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(saignementSNCDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SaignementSNC in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSaignementSNC() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        saignementSNC.setId(longCount.incrementAndGet());

        // Create the SaignementSNC
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(saignementSNC);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSaignementSNCMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(saignementSNCDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SaignementSNC in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSaignementSNC() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        saignementSNC.setId(longCount.incrementAndGet());

        // Create the SaignementSNC
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(saignementSNC);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSaignementSNCMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(saignementSNCDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SaignementSNC in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSaignementSNCWithPatch() throws Exception {
        // Initialize the database
        insertedSaignementSNC = saignementSNCRepository.saveAndFlush(saignementSNC);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the saignementSNC using partial update
        SaignementSNC partialUpdatedSaignementSNC = new SaignementSNC();
        partialUpdatedSaignementSNC.setId(saignementSNC.getId());

        restSaignementSNCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSaignementSNC.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSaignementSNC))
            )
            .andExpect(status().isOk());

        // Validate the SaignementSNC in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSaignementSNCUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSaignementSNC, saignementSNC),
            getPersistedSaignementSNC(saignementSNC)
        );
    }

    @Test
    @Transactional
    void fullUpdateSaignementSNCWithPatch() throws Exception {
        // Initialize the database
        insertedSaignementSNC = saignementSNCRepository.saveAndFlush(saignementSNC);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the saignementSNC using partial update
        SaignementSNC partialUpdatedSaignementSNC = new SaignementSNC();
        partialUpdatedSaignementSNC.setId(saignementSNC.getId());

        partialUpdatedSaignementSNC.reponse(UPDATED_REPONSE).evolution(UPDATED_EVOLUTION);

        restSaignementSNCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSaignementSNC.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSaignementSNC))
            )
            .andExpect(status().isOk());

        // Validate the SaignementSNC in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSaignementSNCUpdatableFieldsEquals(partialUpdatedSaignementSNC, getPersistedSaignementSNC(partialUpdatedSaignementSNC));
    }

    @Test
    @Transactional
    void patchNonExistingSaignementSNC() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        saignementSNC.setId(longCount.incrementAndGet());

        // Create the SaignementSNC
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(saignementSNC);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSaignementSNCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, saignementSNCDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(saignementSNCDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SaignementSNC in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSaignementSNC() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        saignementSNC.setId(longCount.incrementAndGet());

        // Create the SaignementSNC
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(saignementSNC);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSaignementSNCMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(saignementSNCDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SaignementSNC in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSaignementSNC() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        saignementSNC.setId(longCount.incrementAndGet());

        // Create the SaignementSNC
        SaignementSNCDTO saignementSNCDTO = saignementSNCMapper.toDto(saignementSNC);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSaignementSNCMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(saignementSNCDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SaignementSNC in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSaignementSNC() throws Exception {
        // Initialize the database
        insertedSaignementSNC = saignementSNCRepository.saveAndFlush(saignementSNC);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the saignementSNC
        restSaignementSNCMockMvc
            .perform(delete(ENTITY_API_URL_ID, saignementSNC.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return saignementSNCRepository.count();
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

    protected SaignementSNC getPersistedSaignementSNC(SaignementSNC saignementSNC) {
        return saignementSNCRepository.findById(saignementSNC.getId()).orElseThrow();
    }

    protected void assertPersistedSaignementSNCToMatchAllProperties(SaignementSNC expectedSaignementSNC) {
        assertSaignementSNCAllPropertiesEquals(expectedSaignementSNC, getPersistedSaignementSNC(expectedSaignementSNC));
    }

    protected void assertPersistedSaignementSNCToMatchUpdatableProperties(SaignementSNC expectedSaignementSNC) {
        assertSaignementSNCAllUpdatablePropertiesEquals(expectedSaignementSNC, getPersistedSaignementSNC(expectedSaignementSNC));
    }
}
