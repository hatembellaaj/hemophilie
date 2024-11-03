package org.adhes.hemophilie.web.rest;

import static org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueusesAsserts.*;
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
import org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueuses;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeHemorragie;
import org.adhes.hemophilie.repository.HemorragiesCutaneoMuqueusesRepository;
import org.adhes.hemophilie.service.dto.HemorragiesCutaneoMuqueusesDTO;
import org.adhes.hemophilie.service.mapper.HemorragiesCutaneoMuqueusesMapper;
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
 * Integration tests for the {@link HemorragiesCutaneoMuqueusesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HemorragiesCutaneoMuqueusesResourceIT {

    private static final OuiNonNP DEFAULT_REPONSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_REPONSE = OuiNonNP.NON;

    private static final TypeHemorragie DEFAULT_TYPE = TypeHemorragie.CHUTE_DENTAIRE;
    private static final TypeHemorragie UPDATED_TYPE = TypeHemorragie.PLAIE_LANGUE;

    private static final Integer DEFAULT_FREQUENCE_PER_AN = 1;
    private static final Integer UPDATED_FREQUENCE_PER_AN = 2;

    private static final String ENTITY_API_URL = "/api/hemorragies-cutaneo-muqueuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HemorragiesCutaneoMuqueusesRepository hemorragiesCutaneoMuqueusesRepository;

    @Autowired
    private HemorragiesCutaneoMuqueusesMapper hemorragiesCutaneoMuqueusesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHemorragiesCutaneoMuqueusesMockMvc;

    private HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses;

    private HemorragiesCutaneoMuqueuses insertedHemorragiesCutaneoMuqueuses;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HemorragiesCutaneoMuqueuses createEntity() {
        return new HemorragiesCutaneoMuqueuses().reponse(DEFAULT_REPONSE).type(DEFAULT_TYPE).frequencePerAn(DEFAULT_FREQUENCE_PER_AN);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HemorragiesCutaneoMuqueuses createUpdatedEntity() {
        return new HemorragiesCutaneoMuqueuses().reponse(UPDATED_REPONSE).type(UPDATED_TYPE).frequencePerAn(UPDATED_FREQUENCE_PER_AN);
    }

    @BeforeEach
    public void initTest() {
        hemorragiesCutaneoMuqueuses = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedHemorragiesCutaneoMuqueuses != null) {
            hemorragiesCutaneoMuqueusesRepository.delete(insertedHemorragiesCutaneoMuqueuses);
            insertedHemorragiesCutaneoMuqueuses = null;
        }
    }

    @Test
    @Transactional
    void createHemorragiesCutaneoMuqueuses() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the HemorragiesCutaneoMuqueuses
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            hemorragiesCutaneoMuqueuses
        );
        var returnedHemorragiesCutaneoMuqueusesDTO = om.readValue(
            restHemorragiesCutaneoMuqueusesMockMvc
                .perform(
                    post(ENTITY_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HemorragiesCutaneoMuqueusesDTO.class
        );

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedHemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesMapper.toEntity(returnedHemorragiesCutaneoMuqueusesDTO);
        assertHemorragiesCutaneoMuqueusesUpdatableFieldsEquals(
            returnedHemorragiesCutaneoMuqueuses,
            getPersistedHemorragiesCutaneoMuqueuses(returnedHemorragiesCutaneoMuqueuses)
        );

        insertedHemorragiesCutaneoMuqueuses = returnedHemorragiesCutaneoMuqueuses;
    }

    @Test
    @Transactional
    void createHemorragiesCutaneoMuqueusesWithExistingId() throws Exception {
        // Create the HemorragiesCutaneoMuqueuses with an existing ID
        hemorragiesCutaneoMuqueuses.setId(1L);
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            hemorragiesCutaneoMuqueuses
        );

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHemorragiesCutaneoMuqueuses() throws Exception {
        // Initialize the database
        insertedHemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository.saveAndFlush(hemorragiesCutaneoMuqueuses);

        // Get all the hemorragiesCutaneoMuqueusesList
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hemorragiesCutaneoMuqueuses.getId().intValue())))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].frequencePerAn").value(hasItem(DEFAULT_FREQUENCE_PER_AN)));
    }

    @Test
    @Transactional
    void getHemorragiesCutaneoMuqueuses() throws Exception {
        // Initialize the database
        insertedHemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository.saveAndFlush(hemorragiesCutaneoMuqueuses);

        // Get the hemorragiesCutaneoMuqueuses
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(get(ENTITY_API_URL_ID, hemorragiesCutaneoMuqueuses.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hemorragiesCutaneoMuqueuses.getId().intValue()))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.frequencePerAn").value(DEFAULT_FREQUENCE_PER_AN));
    }

    @Test
    @Transactional
    void getNonExistingHemorragiesCutaneoMuqueuses() throws Exception {
        // Get the hemorragiesCutaneoMuqueuses
        restHemorragiesCutaneoMuqueusesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHemorragiesCutaneoMuqueuses() throws Exception {
        // Initialize the database
        insertedHemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository.saveAndFlush(hemorragiesCutaneoMuqueuses);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemorragiesCutaneoMuqueuses
        HemorragiesCutaneoMuqueuses updatedHemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository
            .findById(hemorragiesCutaneoMuqueuses.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedHemorragiesCutaneoMuqueuses are not directly saved in db
        em.detach(updatedHemorragiesCutaneoMuqueuses);
        updatedHemorragiesCutaneoMuqueuses.reponse(UPDATED_REPONSE).type(UPDATED_TYPE).frequencePerAn(UPDATED_FREQUENCE_PER_AN);
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            updatedHemorragiesCutaneoMuqueuses
        );

        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hemorragiesCutaneoMuqueusesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
            )
            .andExpect(status().isOk());

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHemorragiesCutaneoMuqueusesToMatchAllProperties(updatedHemorragiesCutaneoMuqueuses);
    }

    @Test
    @Transactional
    void putNonExistingHemorragiesCutaneoMuqueuses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragiesCutaneoMuqueuses.setId(longCount.incrementAndGet());

        // Create the HemorragiesCutaneoMuqueuses
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            hemorragiesCutaneoMuqueuses
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hemorragiesCutaneoMuqueusesDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHemorragiesCutaneoMuqueuses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragiesCutaneoMuqueuses.setId(longCount.incrementAndGet());

        // Create the HemorragiesCutaneoMuqueuses
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            hemorragiesCutaneoMuqueuses
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHemorragiesCutaneoMuqueuses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragiesCutaneoMuqueuses.setId(longCount.incrementAndGet());

        // Create the HemorragiesCutaneoMuqueuses
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            hemorragiesCutaneoMuqueuses
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHemorragiesCutaneoMuqueusesWithPatch() throws Exception {
        // Initialize the database
        insertedHemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository.saveAndFlush(hemorragiesCutaneoMuqueuses);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemorragiesCutaneoMuqueuses using partial update
        HemorragiesCutaneoMuqueuses partialUpdatedHemorragiesCutaneoMuqueuses = new HemorragiesCutaneoMuqueuses();
        partialUpdatedHemorragiesCutaneoMuqueuses.setId(hemorragiesCutaneoMuqueuses.getId());

        partialUpdatedHemorragiesCutaneoMuqueuses.frequencePerAn(UPDATED_FREQUENCE_PER_AN);

        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHemorragiesCutaneoMuqueuses.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHemorragiesCutaneoMuqueuses))
            )
            .andExpect(status().isOk());

        // Validate the HemorragiesCutaneoMuqueuses in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHemorragiesCutaneoMuqueusesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHemorragiesCutaneoMuqueuses, hemorragiesCutaneoMuqueuses),
            getPersistedHemorragiesCutaneoMuqueuses(hemorragiesCutaneoMuqueuses)
        );
    }

    @Test
    @Transactional
    void fullUpdateHemorragiesCutaneoMuqueusesWithPatch() throws Exception {
        // Initialize the database
        insertedHemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository.saveAndFlush(hemorragiesCutaneoMuqueuses);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemorragiesCutaneoMuqueuses using partial update
        HemorragiesCutaneoMuqueuses partialUpdatedHemorragiesCutaneoMuqueuses = new HemorragiesCutaneoMuqueuses();
        partialUpdatedHemorragiesCutaneoMuqueuses.setId(hemorragiesCutaneoMuqueuses.getId());

        partialUpdatedHemorragiesCutaneoMuqueuses.reponse(UPDATED_REPONSE).type(UPDATED_TYPE).frequencePerAn(UPDATED_FREQUENCE_PER_AN);

        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHemorragiesCutaneoMuqueuses.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHemorragiesCutaneoMuqueuses))
            )
            .andExpect(status().isOk());

        // Validate the HemorragiesCutaneoMuqueuses in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHemorragiesCutaneoMuqueusesUpdatableFieldsEquals(
            partialUpdatedHemorragiesCutaneoMuqueuses,
            getPersistedHemorragiesCutaneoMuqueuses(partialUpdatedHemorragiesCutaneoMuqueuses)
        );
    }

    @Test
    @Transactional
    void patchNonExistingHemorragiesCutaneoMuqueuses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragiesCutaneoMuqueuses.setId(longCount.incrementAndGet());

        // Create the HemorragiesCutaneoMuqueuses
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            hemorragiesCutaneoMuqueuses
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hemorragiesCutaneoMuqueusesDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHemorragiesCutaneoMuqueuses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragiesCutaneoMuqueuses.setId(longCount.incrementAndGet());

        // Create the HemorragiesCutaneoMuqueuses
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            hemorragiesCutaneoMuqueuses
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHemorragiesCutaneoMuqueuses() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragiesCutaneoMuqueuses.setId(longCount.incrementAndGet());

        // Create the HemorragiesCutaneoMuqueuses
        HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesMapper.toDto(
            hemorragiesCutaneoMuqueuses
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hemorragiesCutaneoMuqueusesDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the HemorragiesCutaneoMuqueuses in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHemorragiesCutaneoMuqueuses() throws Exception {
        // Initialize the database
        insertedHemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository.saveAndFlush(hemorragiesCutaneoMuqueuses);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hemorragiesCutaneoMuqueuses
        restHemorragiesCutaneoMuqueusesMockMvc
            .perform(delete(ENTITY_API_URL_ID, hemorragiesCutaneoMuqueuses.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hemorragiesCutaneoMuqueusesRepository.count();
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

    protected HemorragiesCutaneoMuqueuses getPersistedHemorragiesCutaneoMuqueuses(HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses) {
        return hemorragiesCutaneoMuqueusesRepository.findById(hemorragiesCutaneoMuqueuses.getId()).orElseThrow();
    }

    protected void assertPersistedHemorragiesCutaneoMuqueusesToMatchAllProperties(
        HemorragiesCutaneoMuqueuses expectedHemorragiesCutaneoMuqueuses
    ) {
        assertHemorragiesCutaneoMuqueusesAllPropertiesEquals(
            expectedHemorragiesCutaneoMuqueuses,
            getPersistedHemorragiesCutaneoMuqueuses(expectedHemorragiesCutaneoMuqueuses)
        );
    }

    protected void assertPersistedHemorragiesCutaneoMuqueusesToMatchUpdatableProperties(
        HemorragiesCutaneoMuqueuses expectedHemorragiesCutaneoMuqueuses
    ) {
        assertHemorragiesCutaneoMuqueusesAllUpdatablePropertiesEquals(
            expectedHemorragiesCutaneoMuqueuses,
            getPersistedHemorragiesCutaneoMuqueuses(expectedHemorragiesCutaneoMuqueuses)
        );
    }
}
