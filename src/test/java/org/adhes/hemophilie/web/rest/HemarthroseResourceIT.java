package org.adhes.hemophilie.web.rest;

import static org.adhes.hemophilie.domain.HemarthroseAsserts.*;
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
import org.adhes.hemophilie.domain.Hemarthrose;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeManifestation;
import org.adhes.hemophilie.repository.HemarthroseRepository;
import org.adhes.hemophilie.service.dto.HemarthroseDTO;
import org.adhes.hemophilie.service.mapper.HemarthroseMapper;
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
 * Integration tests for the {@link HemarthroseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HemarthroseResourceIT {

    private static final OuiNonNP DEFAULT_REPONSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_REPONSE = OuiNonNP.NON;

    private static final TypeManifestation DEFAULT_TYPE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE = TypeManifestation.PROVOQUE;

    private static final String DEFAULT_SIEGE = "AAAAAAAAAA";
    private static final String UPDATED_SIEGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_FREQUENCE_PER_AN = 1;
    private static final Integer UPDATED_FREQUENCE_PER_AN = 2;

    private static final String ENTITY_API_URL = "/api/hemarthroses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HemarthroseRepository hemarthroseRepository;

    @Autowired
    private HemarthroseMapper hemarthroseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHemarthroseMockMvc;

    private Hemarthrose hemarthrose;

    private Hemarthrose insertedHemarthrose;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hemarthrose createEntity() {
        return new Hemarthrose().reponse(DEFAULT_REPONSE).type(DEFAULT_TYPE).siege(DEFAULT_SIEGE).frequencePerAn(DEFAULT_FREQUENCE_PER_AN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hemarthrose createUpdatedEntity() {
        return new Hemarthrose().reponse(UPDATED_REPONSE).type(UPDATED_TYPE).siege(UPDATED_SIEGE).frequencePerAn(UPDATED_FREQUENCE_PER_AN);
    }

    @BeforeEach
    public void initTest() {
        hemarthrose = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedHemarthrose != null) {
            hemarthroseRepository.delete(insertedHemarthrose);
            insertedHemarthrose = null;
        }
    }

    @Test
    @Transactional
    void createHemarthrose() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Hemarthrose
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);
        var returnedHemarthroseDTO = om.readValue(
            restHemarthroseMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemarthroseDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HemarthroseDTO.class
        );

        // Validate the Hemarthrose in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedHemarthrose = hemarthroseMapper.toEntity(returnedHemarthroseDTO);
        assertHemarthroseUpdatableFieldsEquals(returnedHemarthrose, getPersistedHemarthrose(returnedHemarthrose));

        insertedHemarthrose = returnedHemarthrose;
    }

    @Test
    @Transactional
    void createHemarthroseWithExistingId() throws Exception {
        // Create the Hemarthrose with an existing ID
        hemarthrose.setId(1L);
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHemarthroseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemarthroseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hemarthrose in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        hemarthrose.setType(null);

        // Create the Hemarthrose, which fails.
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        restHemarthroseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemarthroseDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSiegeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        hemarthrose.setSiege(null);

        // Create the Hemarthrose, which fails.
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        restHemarthroseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemarthroseDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllHemarthroses() throws Exception {
        // Initialize the database
        insertedHemarthrose = hemarthroseRepository.saveAndFlush(hemarthrose);

        // Get all the hemarthroseList
        restHemarthroseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hemarthrose.getId().intValue())))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].siege").value(hasItem(DEFAULT_SIEGE)))
            .andExpect(jsonPath("$.[*].frequencePerAn").value(hasItem(DEFAULT_FREQUENCE_PER_AN)));
    }

    @Test
    @Transactional
    void getHemarthrose() throws Exception {
        // Initialize the database
        insertedHemarthrose = hemarthroseRepository.saveAndFlush(hemarthrose);

        // Get the hemarthrose
        restHemarthroseMockMvc
            .perform(get(ENTITY_API_URL_ID, hemarthrose.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hemarthrose.getId().intValue()))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.siege").value(DEFAULT_SIEGE))
            .andExpect(jsonPath("$.frequencePerAn").value(DEFAULT_FREQUENCE_PER_AN));
    }

    @Test
    @Transactional
    void getNonExistingHemarthrose() throws Exception {
        // Get the hemarthrose
        restHemarthroseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHemarthrose() throws Exception {
        // Initialize the database
        insertedHemarthrose = hemarthroseRepository.saveAndFlush(hemarthrose);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemarthrose
        Hemarthrose updatedHemarthrose = hemarthroseRepository.findById(hemarthrose.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHemarthrose are not directly saved in db
        em.detach(updatedHemarthrose);
        updatedHemarthrose.reponse(UPDATED_REPONSE).type(UPDATED_TYPE).siege(UPDATED_SIEGE).frequencePerAn(UPDATED_FREQUENCE_PER_AN);
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(updatedHemarthrose);

        restHemarthroseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hemarthroseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemarthroseDTO))
            )
            .andExpect(status().isOk());

        // Validate the Hemarthrose in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHemarthroseToMatchAllProperties(updatedHemarthrose);
    }

    @Test
    @Transactional
    void putNonExistingHemarthrose() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemarthrose.setId(longCount.incrementAndGet());

        // Create the Hemarthrose
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHemarthroseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hemarthroseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemarthroseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hemarthrose in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHemarthrose() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemarthrose.setId(longCount.incrementAndGet());

        // Create the Hemarthrose
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemarthroseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemarthroseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hemarthrose in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHemarthrose() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemarthrose.setId(longCount.incrementAndGet());

        // Create the Hemarthrose
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemarthroseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemarthroseDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hemarthrose in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHemarthroseWithPatch() throws Exception {
        // Initialize the database
        insertedHemarthrose = hemarthroseRepository.saveAndFlush(hemarthrose);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemarthrose using partial update
        Hemarthrose partialUpdatedHemarthrose = new Hemarthrose();
        partialUpdatedHemarthrose.setId(hemarthrose.getId());

        partialUpdatedHemarthrose.type(UPDATED_TYPE);

        restHemarthroseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHemarthrose.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHemarthrose))
            )
            .andExpect(status().isOk());

        // Validate the Hemarthrose in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHemarthroseUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHemarthrose, hemarthrose),
            getPersistedHemarthrose(hemarthrose)
        );
    }

    @Test
    @Transactional
    void fullUpdateHemarthroseWithPatch() throws Exception {
        // Initialize the database
        insertedHemarthrose = hemarthroseRepository.saveAndFlush(hemarthrose);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemarthrose using partial update
        Hemarthrose partialUpdatedHemarthrose = new Hemarthrose();
        partialUpdatedHemarthrose.setId(hemarthrose.getId());

        partialUpdatedHemarthrose.reponse(UPDATED_REPONSE).type(UPDATED_TYPE).siege(UPDATED_SIEGE).frequencePerAn(UPDATED_FREQUENCE_PER_AN);

        restHemarthroseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHemarthrose.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHemarthrose))
            )
            .andExpect(status().isOk());

        // Validate the Hemarthrose in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHemarthroseUpdatableFieldsEquals(partialUpdatedHemarthrose, getPersistedHemarthrose(partialUpdatedHemarthrose));
    }

    @Test
    @Transactional
    void patchNonExistingHemarthrose() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemarthrose.setId(longCount.incrementAndGet());

        // Create the Hemarthrose
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHemarthroseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hemarthroseDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hemarthroseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hemarthrose in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHemarthrose() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemarthrose.setId(longCount.incrementAndGet());

        // Create the Hemarthrose
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemarthroseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hemarthroseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hemarthrose in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHemarthrose() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemarthrose.setId(longCount.incrementAndGet());

        // Create the Hemarthrose
        HemarthroseDTO hemarthroseDTO = hemarthroseMapper.toDto(hemarthrose);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemarthroseMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hemarthroseDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hemarthrose in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHemarthrose() throws Exception {
        // Initialize the database
        insertedHemarthrose = hemarthroseRepository.saveAndFlush(hemarthrose);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hemarthrose
        restHemarthroseMockMvc
            .perform(delete(ENTITY_API_URL_ID, hemarthrose.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hemarthroseRepository.count();
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

    protected Hemarthrose getPersistedHemarthrose(Hemarthrose hemarthrose) {
        return hemarthroseRepository.findById(hemarthrose.getId()).orElseThrow();
    }

    protected void assertPersistedHemarthroseToMatchAllProperties(Hemarthrose expectedHemarthrose) {
        assertHemarthroseAllPropertiesEquals(expectedHemarthrose, getPersistedHemarthrose(expectedHemarthrose));
    }

    protected void assertPersistedHemarthroseToMatchUpdatableProperties(Hemarthrose expectedHemarthrose) {
        assertHemarthroseAllUpdatablePropertiesEquals(expectedHemarthrose, getPersistedHemarthrose(expectedHemarthrose));
    }
}
