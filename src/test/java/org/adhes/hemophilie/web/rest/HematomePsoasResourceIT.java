package org.adhes.hemophilie.web.rest;

import static org.adhes.hemophilie.domain.HematomePsoasAsserts.*;
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
import org.adhes.hemophilie.domain.HematomePsoas;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeManifestation;
import org.adhes.hemophilie.repository.HematomePsoasRepository;
import org.adhes.hemophilie.service.dto.HematomePsoasDTO;
import org.adhes.hemophilie.service.mapper.HematomePsoasMapper;
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
 * Integration tests for the {@link HematomePsoasResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HematomePsoasResourceIT {

    private static final OuiNonNP DEFAULT_REPONSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_REPONSE = OuiNonNP.NON;

    private static final TypeManifestation DEFAULT_TYPE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE = TypeManifestation.PROVOQUE;

    private static final OuiNonNP DEFAULT_RECIDIVE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_RECIDIVE = OuiNonNP.NON;

    private static final String ENTITY_API_URL = "/api/hematome-psoas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HematomePsoasRepository hematomePsoasRepository;

    @Autowired
    private HematomePsoasMapper hematomePsoasMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHematomePsoasMockMvc;

    private HematomePsoas hematomePsoas;

    private HematomePsoas insertedHematomePsoas;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HematomePsoas createEntity() {
        return new HematomePsoas().reponse(DEFAULT_REPONSE).type(DEFAULT_TYPE).recidive(DEFAULT_RECIDIVE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HematomePsoas createUpdatedEntity() {
        return new HematomePsoas().reponse(UPDATED_REPONSE).type(UPDATED_TYPE).recidive(UPDATED_RECIDIVE);
    }

    @BeforeEach
    public void initTest() {
        hematomePsoas = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedHematomePsoas != null) {
            hematomePsoasRepository.delete(insertedHematomePsoas);
            insertedHematomePsoas = null;
        }
    }

    @Test
    @Transactional
    void createHematomePsoas() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the HematomePsoas
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(hematomePsoas);
        var returnedHematomePsoasDTO = om.readValue(
            restHematomePsoasMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hematomePsoasDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HematomePsoasDTO.class
        );

        // Validate the HematomePsoas in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedHematomePsoas = hematomePsoasMapper.toEntity(returnedHematomePsoasDTO);
        assertHematomePsoasUpdatableFieldsEquals(returnedHematomePsoas, getPersistedHematomePsoas(returnedHematomePsoas));

        insertedHematomePsoas = returnedHematomePsoas;
    }

    @Test
    @Transactional
    void createHematomePsoasWithExistingId() throws Exception {
        // Create the HematomePsoas with an existing ID
        hematomePsoas.setId(1L);
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(hematomePsoas);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHematomePsoasMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hematomePsoasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HematomePsoas in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHematomePsoas() throws Exception {
        // Initialize the database
        insertedHematomePsoas = hematomePsoasRepository.saveAndFlush(hematomePsoas);

        // Get all the hematomePsoasList
        restHematomePsoasMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hematomePsoas.getId().intValue())))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].recidive").value(hasItem(DEFAULT_RECIDIVE.toString())));
    }

    @Test
    @Transactional
    void getHematomePsoas() throws Exception {
        // Initialize the database
        insertedHematomePsoas = hematomePsoasRepository.saveAndFlush(hematomePsoas);

        // Get the hematomePsoas
        restHematomePsoasMockMvc
            .perform(get(ENTITY_API_URL_ID, hematomePsoas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hematomePsoas.getId().intValue()))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.recidive").value(DEFAULT_RECIDIVE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingHematomePsoas() throws Exception {
        // Get the hematomePsoas
        restHematomePsoasMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHematomePsoas() throws Exception {
        // Initialize the database
        insertedHematomePsoas = hematomePsoasRepository.saveAndFlush(hematomePsoas);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hematomePsoas
        HematomePsoas updatedHematomePsoas = hematomePsoasRepository.findById(hematomePsoas.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHematomePsoas are not directly saved in db
        em.detach(updatedHematomePsoas);
        updatedHematomePsoas.reponse(UPDATED_REPONSE).type(UPDATED_TYPE).recidive(UPDATED_RECIDIVE);
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(updatedHematomePsoas);

        restHematomePsoasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hematomePsoasDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hematomePsoasDTO))
            )
            .andExpect(status().isOk());

        // Validate the HematomePsoas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHematomePsoasToMatchAllProperties(updatedHematomePsoas);
    }

    @Test
    @Transactional
    void putNonExistingHematomePsoas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomePsoas.setId(longCount.incrementAndGet());

        // Create the HematomePsoas
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(hematomePsoas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHematomePsoasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hematomePsoasDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hematomePsoasDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HematomePsoas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHematomePsoas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomePsoas.setId(longCount.incrementAndGet());

        // Create the HematomePsoas
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(hematomePsoas);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHematomePsoasMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hematomePsoasDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HematomePsoas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHematomePsoas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomePsoas.setId(longCount.incrementAndGet());

        // Create the HematomePsoas
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(hematomePsoas);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHematomePsoasMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hematomePsoasDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HematomePsoas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHematomePsoasWithPatch() throws Exception {
        // Initialize the database
        insertedHematomePsoas = hematomePsoasRepository.saveAndFlush(hematomePsoas);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hematomePsoas using partial update
        HematomePsoas partialUpdatedHematomePsoas = new HematomePsoas();
        partialUpdatedHematomePsoas.setId(hematomePsoas.getId());

        partialUpdatedHematomePsoas.type(UPDATED_TYPE).recidive(UPDATED_RECIDIVE);

        restHematomePsoasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHematomePsoas.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHematomePsoas))
            )
            .andExpect(status().isOk());

        // Validate the HematomePsoas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHematomePsoasUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHematomePsoas, hematomePsoas),
            getPersistedHematomePsoas(hematomePsoas)
        );
    }

    @Test
    @Transactional
    void fullUpdateHematomePsoasWithPatch() throws Exception {
        // Initialize the database
        insertedHematomePsoas = hematomePsoasRepository.saveAndFlush(hematomePsoas);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hematomePsoas using partial update
        HematomePsoas partialUpdatedHematomePsoas = new HematomePsoas();
        partialUpdatedHematomePsoas.setId(hematomePsoas.getId());

        partialUpdatedHematomePsoas.reponse(UPDATED_REPONSE).type(UPDATED_TYPE).recidive(UPDATED_RECIDIVE);

        restHematomePsoasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHematomePsoas.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHematomePsoas))
            )
            .andExpect(status().isOk());

        // Validate the HematomePsoas in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHematomePsoasUpdatableFieldsEquals(partialUpdatedHematomePsoas, getPersistedHematomePsoas(partialUpdatedHematomePsoas));
    }

    @Test
    @Transactional
    void patchNonExistingHematomePsoas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomePsoas.setId(longCount.incrementAndGet());

        // Create the HematomePsoas
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(hematomePsoas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHematomePsoasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hematomePsoasDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hematomePsoasDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HematomePsoas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHematomePsoas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomePsoas.setId(longCount.incrementAndGet());

        // Create the HematomePsoas
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(hematomePsoas);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHematomePsoasMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hematomePsoasDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HematomePsoas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHematomePsoas() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hematomePsoas.setId(longCount.incrementAndGet());

        // Create the HematomePsoas
        HematomePsoasDTO hematomePsoasDTO = hematomePsoasMapper.toDto(hematomePsoas);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHematomePsoasMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hematomePsoasDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HematomePsoas in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHematomePsoas() throws Exception {
        // Initialize the database
        insertedHematomePsoas = hematomePsoasRepository.saveAndFlush(hematomePsoas);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hematomePsoas
        restHematomePsoasMockMvc
            .perform(delete(ENTITY_API_URL_ID, hematomePsoas.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hematomePsoasRepository.count();
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

    protected HematomePsoas getPersistedHematomePsoas(HematomePsoas hematomePsoas) {
        return hematomePsoasRepository.findById(hematomePsoas.getId()).orElseThrow();
    }

    protected void assertPersistedHematomePsoasToMatchAllProperties(HematomePsoas expectedHematomePsoas) {
        assertHematomePsoasAllPropertiesEquals(expectedHematomePsoas, getPersistedHematomePsoas(expectedHematomePsoas));
    }

    protected void assertPersistedHematomePsoasToMatchUpdatableProperties(HematomePsoas expectedHematomePsoas) {
        assertHematomePsoasAllUpdatablePropertiesEquals(expectedHematomePsoas, getPersistedHematomePsoas(expectedHematomePsoas));
    }
}
