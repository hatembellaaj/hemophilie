package org.adhes.hemophilie.web.rest;

import static org.adhes.hemophilie.domain.HematomeSuperficielAsserts.*;
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
import org.adhes.hemophilie.domain.HematomeSuperficiel;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeManifestation;
import org.adhes.hemophilie.repository.HematomeSuperficielRepository;
import org.adhes.hemophilie.service.dto.HematomeSuperficielDTO;
import org.adhes.hemophilie.service.mapper.HematomeSuperficielMapper;
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
 * Integration tests for the {@link HematomeSuperficielResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HematomeSuperficielResourceIT {

    private static final OuiNonNP DEFAULT_REPONSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_REPONSE = OuiNonNP.NON;

    private static final TypeManifestation DEFAULT_TYPE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE = TypeManifestation.PROVOQUE;

    private static final String DEFAULT_SIEGE = "AAAAAAAAAA";
    private static final String UPDATED_SIEGE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/hematome-superficiels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HematomeSuperficielRepository hematomeSuperficielRepository;

    @Autowired
    private HematomeSuperficielMapper hematomeSuperficielMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHematomeSuperficielMockMvc;

    private HematomeSuperficiel hematomeSuperficiel;

    private HematomeSuperficiel insertedHematomeSuperficiel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HematomeSuperficiel createEntity() {
        return new HematomeSuperficiel().reponse(DEFAULT_REPONSE).type(DEFAULT_TYPE).siege(DEFAULT_SIEGE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HematomeSuperficiel createUpdatedEntity() {
        return new HematomeSuperficiel().reponse(UPDATED_REPONSE).type(UPDATED_TYPE).siege(UPDATED_SIEGE);
    }

    @BeforeEach
    public void initTest() {
        hematomeSuperficiel = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedHematomeSuperficiel != null) {
            hematomeSuperficielRepository.delete(insertedHematomeSuperficiel);
            insertedHematomeSuperficiel = null;
        }
    }

    @Test
    @Transactional
    void createHematomeSuperficiel() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the HematomeSuperficiel
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(hematomeSuperficiel);
        var returnedHematomeSuperficielDTO = om.readValue(
            restHematomeSuperficielMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hematomeSuperficielDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HematomeSuperficielDTO.class
        );

        // Validate the HematomeSuperficiel in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedHematomeSuperficiel = hematomeSuperficielMapper.toEntity(returnedHematomeSuperficielDTO);
        assertHematomeSuperficielUpdatableFieldsEquals(
            returnedHematomeSuperficiel,
            getPersistedHematomeSuperficiel(returnedHematomeSuperficiel)
        );

        insertedHematomeSuperficiel = returnedHematomeSuperficiel;
    }

    @Test
    @Transactional
    void createHematomeSuperficielWithExistingId() throws Exception {
        // Create the HematomeSuperficiel with an existing ID
        hematomeSuperficiel.setId(1L);
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(hematomeSuperficiel);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHematomeSuperficielMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hematomeSuperficielDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HematomeSuperficiel in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHematomeSuperficiels() throws Exception {
        // Initialize the database
        insertedHematomeSuperficiel = hematomeSuperficielRepository.saveAndFlush(hematomeSuperficiel);

        // Get all the hematomeSuperficielList
        restHematomeSuperficielMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hematomeSuperficiel.getId().intValue())))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].siege").value(hasItem(DEFAULT_SIEGE)));
    }

    @Test
    @Transactional
    void getHematomeSuperficiel() throws Exception {
        // Initialize the database
        insertedHematomeSuperficiel = hematomeSuperficielRepository.saveAndFlush(hematomeSuperficiel);

        // Get the hematomeSuperficiel
        restHematomeSuperficielMockMvc
            .perform(get(ENTITY_API_URL_ID, hematomeSuperficiel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hematomeSuperficiel.getId().intValue()))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.siege").value(DEFAULT_SIEGE));
    }

    @Test
    @Transactional
    void getNonExistingHematomeSuperficiel() throws Exception {
        // Get the hematomeSuperficiel
        restHematomeSuperficielMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHematomeSuperficiel() throws Exception {
        // Initialize the database
        insertedHematomeSuperficiel = hematomeSuperficielRepository.saveAndFlush(hematomeSuperficiel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hematomeSuperficiel
        HematomeSuperficiel updatedHematomeSuperficiel = hematomeSuperficielRepository.findById(hematomeSuperficiel.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHematomeSuperficiel are not directly saved in db
        em.detach(updatedHematomeSuperficiel);
        updatedHematomeSuperficiel.reponse(UPDATED_REPONSE).type(UPDATED_TYPE).siege(UPDATED_SIEGE);
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(updatedHematomeSuperficiel);

        restHematomeSuperficielMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hematomeSuperficielDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hematomeSuperficielDTO))
            )
            .andExpect(status().isOk());

        // Validate the HematomeSuperficiel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHematomeSuperficielToMatchAllProperties(updatedHematomeSuperficiel);
    }

    @Test
    @Transactional
    void putNonExistingHematomeSuperficiel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomeSuperficiel.setId(longCount.incrementAndGet());

        // Create the HematomeSuperficiel
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(hematomeSuperficiel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHematomeSuperficielMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hematomeSuperficielDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hematomeSuperficielDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HematomeSuperficiel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHematomeSuperficiel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomeSuperficiel.setId(longCount.incrementAndGet());

        // Create the HematomeSuperficiel
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(hematomeSuperficiel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHematomeSuperficielMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hematomeSuperficielDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HematomeSuperficiel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHematomeSuperficiel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomeSuperficiel.setId(longCount.incrementAndGet());

        // Create the HematomeSuperficiel
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(hematomeSuperficiel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHematomeSuperficielMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hematomeSuperficielDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HematomeSuperficiel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHematomeSuperficielWithPatch() throws Exception {
        // Initialize the database
        insertedHematomeSuperficiel = hematomeSuperficielRepository.saveAndFlush(hematomeSuperficiel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hematomeSuperficiel using partial update
        HematomeSuperficiel partialUpdatedHematomeSuperficiel = new HematomeSuperficiel();
        partialUpdatedHematomeSuperficiel.setId(hematomeSuperficiel.getId());

        partialUpdatedHematomeSuperficiel.reponse(UPDATED_REPONSE).siege(UPDATED_SIEGE);

        restHematomeSuperficielMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHematomeSuperficiel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHematomeSuperficiel))
            )
            .andExpect(status().isOk());

        // Validate the HematomeSuperficiel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHematomeSuperficielUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHematomeSuperficiel, hematomeSuperficiel),
            getPersistedHematomeSuperficiel(hematomeSuperficiel)
        );
    }

    @Test
    @Transactional
    void fullUpdateHematomeSuperficielWithPatch() throws Exception {
        // Initialize the database
        insertedHematomeSuperficiel = hematomeSuperficielRepository.saveAndFlush(hematomeSuperficiel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hematomeSuperficiel using partial update
        HematomeSuperficiel partialUpdatedHematomeSuperficiel = new HematomeSuperficiel();
        partialUpdatedHematomeSuperficiel.setId(hematomeSuperficiel.getId());

        partialUpdatedHematomeSuperficiel.reponse(UPDATED_REPONSE).type(UPDATED_TYPE).siege(UPDATED_SIEGE);

        restHematomeSuperficielMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHematomeSuperficiel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHematomeSuperficiel))
            )
            .andExpect(status().isOk());

        // Validate the HematomeSuperficiel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHematomeSuperficielUpdatableFieldsEquals(
            partialUpdatedHematomeSuperficiel,
            getPersistedHematomeSuperficiel(partialUpdatedHematomeSuperficiel)
        );
    }

    @Test
    @Transactional
    void patchNonExistingHematomeSuperficiel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomeSuperficiel.setId(longCount.incrementAndGet());

        // Create the HematomeSuperficiel
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(hematomeSuperficiel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHematomeSuperficielMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hematomeSuperficielDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hematomeSuperficielDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HematomeSuperficiel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHematomeSuperficiel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomeSuperficiel.setId(longCount.incrementAndGet());

        // Create the HematomeSuperficiel
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(hematomeSuperficiel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHematomeSuperficielMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hematomeSuperficielDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HematomeSuperficiel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHematomeSuperficiel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomeSuperficiel.setId(longCount.incrementAndGet());

        // Create the HematomeSuperficiel
        HematomeSuperficielDTO hematomeSuperficielDTO = hematomeSuperficielMapper.toDto(hematomeSuperficiel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHematomeSuperficielMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hematomeSuperficielDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HematomeSuperficiel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHematomeSuperficiel() throws Exception {
        // Initialize the database
        insertedHematomeSuperficiel = hematomeSuperficielRepository.saveAndFlush(hematomeSuperficiel);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hematomeSuperficiel
        restHematomeSuperficielMockMvc
            .perform(delete(ENTITY_API_URL_ID, hematomeSuperficiel.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hematomeSuperficielRepository.count();
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

    protected HematomeSuperficiel getPersistedHematomeSuperficiel(HematomeSuperficiel hematomeSuperficiel) {
        return hematomeSuperficielRepository.findById(hematomeSuperficiel.getId()).orElseThrow();
    }

    protected void assertPersistedHematomeSuperficielToMatchAllProperties(HematomeSuperficiel expectedHematomeSuperficiel) {
        assertHematomeSuperficielAllPropertiesEquals(
            expectedHematomeSuperficiel,
            getPersistedHematomeSuperficiel(expectedHematomeSuperficiel)
        );
    }

    protected void assertPersistedHematomeSuperficielToMatchUpdatableProperties(HematomeSuperficiel expectedHematomeSuperficiel) {
        assertHematomeSuperficielAllUpdatablePropertiesEquals(
            expectedHematomeSuperficiel,
            getPersistedHematomeSuperficiel(expectedHematomeSuperficiel)
        );
    }
}
