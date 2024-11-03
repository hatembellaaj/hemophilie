package org.adhes.hemophilie.web.rest;

import static org.adhes.hemophilie.domain.HemorragieVisceresAsserts.*;
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
import org.adhes.hemophilie.domain.HemorragieVisceres;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.OuiNonNP;
import org.adhes.hemophilie.domain.enumeration.TypeHemorragieVisceres;
import org.adhes.hemophilie.repository.HemorragieVisceresRepository;
import org.adhes.hemophilie.service.dto.HemorragieVisceresDTO;
import org.adhes.hemophilie.service.mapper.HemorragieVisceresMapper;
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
 * Integration tests for the {@link HemorragieVisceresResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HemorragieVisceresResourceIT {

    private static final OuiNonNP DEFAULT_REPONSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_REPONSE = OuiNonNP.NON;

    private static final TypeHemorragieVisceres DEFAULT_TYPE = TypeHemorragieVisceres.HEMATEMESE;
    private static final TypeHemorragieVisceres UPDATED_TYPE = TypeHemorragieVisceres.RECTORRAGIE;

    private static final OuiNonNP DEFAULT_EXPLORATION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EXPLORATION = OuiNonNP.NON;

    private static final String DEFAULT_EXAMEN_LESION = "AAAAAAAAAA";
    private static final String UPDATED_EXAMEN_LESION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/hemorragie-visceres";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HemorragieVisceresRepository hemorragieVisceresRepository;

    @Autowired
    private HemorragieVisceresMapper hemorragieVisceresMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHemorragieVisceresMockMvc;

    private HemorragieVisceres hemorragieVisceres;

    private HemorragieVisceres insertedHemorragieVisceres;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HemorragieVisceres createEntity() {
        return new HemorragieVisceres()
            .reponse(DEFAULT_REPONSE)
            .type(DEFAULT_TYPE)
            .exploration(DEFAULT_EXPLORATION)
            .examenLesion(DEFAULT_EXAMEN_LESION);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HemorragieVisceres createUpdatedEntity() {
        return new HemorragieVisceres()
            .reponse(UPDATED_REPONSE)
            .type(UPDATED_TYPE)
            .exploration(UPDATED_EXPLORATION)
            .examenLesion(UPDATED_EXAMEN_LESION);
    }

    @BeforeEach
    public void initTest() {
        hemorragieVisceres = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedHemorragieVisceres != null) {
            hemorragieVisceresRepository.delete(insertedHemorragieVisceres);
            insertedHemorragieVisceres = null;
        }
    }

    @Test
    @Transactional
    void createHemorragieVisceres() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the HemorragieVisceres
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(hemorragieVisceres);
        var returnedHemorragieVisceresDTO = om.readValue(
            restHemorragieVisceresMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemorragieVisceresDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HemorragieVisceresDTO.class
        );

        // Validate the HemorragieVisceres in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedHemorragieVisceres = hemorragieVisceresMapper.toEntity(returnedHemorragieVisceresDTO);
        assertHemorragieVisceresUpdatableFieldsEquals(
            returnedHemorragieVisceres,
            getPersistedHemorragieVisceres(returnedHemorragieVisceres)
        );

        insertedHemorragieVisceres = returnedHemorragieVisceres;
    }

    @Test
    @Transactional
    void createHemorragieVisceresWithExistingId() throws Exception {
        // Create the HemorragieVisceres with an existing ID
        hemorragieVisceres.setId(1L);
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(hemorragieVisceres);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHemorragieVisceresMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemorragieVisceresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HemorragieVisceres in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHemorragieVisceres() throws Exception {
        // Initialize the database
        insertedHemorragieVisceres = hemorragieVisceresRepository.saveAndFlush(hemorragieVisceres);

        // Get all the hemorragieVisceresList
        restHemorragieVisceresMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hemorragieVisceres.getId().intValue())))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].exploration").value(hasItem(DEFAULT_EXPLORATION.toString())))
            .andExpect(jsonPath("$.[*].examenLesion").value(hasItem(DEFAULT_EXAMEN_LESION)));
    }

    @Test
    @Transactional
    void getHemorragieVisceres() throws Exception {
        // Initialize the database
        insertedHemorragieVisceres = hemorragieVisceresRepository.saveAndFlush(hemorragieVisceres);

        // Get the hemorragieVisceres
        restHemorragieVisceresMockMvc
            .perform(get(ENTITY_API_URL_ID, hemorragieVisceres.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hemorragieVisceres.getId().intValue()))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.exploration").value(DEFAULT_EXPLORATION.toString()))
            .andExpect(jsonPath("$.examenLesion").value(DEFAULT_EXAMEN_LESION));
    }

    @Test
    @Transactional
    void getNonExistingHemorragieVisceres() throws Exception {
        // Get the hemorragieVisceres
        restHemorragieVisceresMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHemorragieVisceres() throws Exception {
        // Initialize the database
        insertedHemorragieVisceres = hemorragieVisceresRepository.saveAndFlush(hemorragieVisceres);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemorragieVisceres
        HemorragieVisceres updatedHemorragieVisceres = hemorragieVisceresRepository.findById(hemorragieVisceres.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHemorragieVisceres are not directly saved in db
        em.detach(updatedHemorragieVisceres);
        updatedHemorragieVisceres
            .reponse(UPDATED_REPONSE)
            .type(UPDATED_TYPE)
            .exploration(UPDATED_EXPLORATION)
            .examenLesion(UPDATED_EXAMEN_LESION);
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(updatedHemorragieVisceres);

        restHemorragieVisceresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hemorragieVisceresDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemorragieVisceresDTO))
            )
            .andExpect(status().isOk());

        // Validate the HemorragieVisceres in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHemorragieVisceresToMatchAllProperties(updatedHemorragieVisceres);
    }

    @Test
    @Transactional
    void putNonExistingHemorragieVisceres() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragieVisceres.setId(longCount.incrementAndGet());

        // Create the HemorragieVisceres
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(hemorragieVisceres);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHemorragieVisceresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hemorragieVisceresDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemorragieVisceresDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragieVisceres in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHemorragieVisceres() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragieVisceres.setId(longCount.incrementAndGet());

        // Create the HemorragieVisceres
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(hemorragieVisceres);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemorragieVisceresMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hemorragieVisceresDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragieVisceres in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHemorragieVisceres() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragieVisceres.setId(longCount.incrementAndGet());

        // Create the HemorragieVisceres
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(hemorragieVisceres);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemorragieVisceresMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hemorragieVisceresDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HemorragieVisceres in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHemorragieVisceresWithPatch() throws Exception {
        // Initialize the database
        insertedHemorragieVisceres = hemorragieVisceresRepository.saveAndFlush(hemorragieVisceres);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemorragieVisceres using partial update
        HemorragieVisceres partialUpdatedHemorragieVisceres = new HemorragieVisceres();
        partialUpdatedHemorragieVisceres.setId(hemorragieVisceres.getId());

        restHemorragieVisceresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHemorragieVisceres.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHemorragieVisceres))
            )
            .andExpect(status().isOk());

        // Validate the HemorragieVisceres in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHemorragieVisceresUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHemorragieVisceres, hemorragieVisceres),
            getPersistedHemorragieVisceres(hemorragieVisceres)
        );
    }

    @Test
    @Transactional
    void fullUpdateHemorragieVisceresWithPatch() throws Exception {
        // Initialize the database
        insertedHemorragieVisceres = hemorragieVisceresRepository.saveAndFlush(hemorragieVisceres);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hemorragieVisceres using partial update
        HemorragieVisceres partialUpdatedHemorragieVisceres = new HemorragieVisceres();
        partialUpdatedHemorragieVisceres.setId(hemorragieVisceres.getId());

        partialUpdatedHemorragieVisceres
            .reponse(UPDATED_REPONSE)
            .type(UPDATED_TYPE)
            .exploration(UPDATED_EXPLORATION)
            .examenLesion(UPDATED_EXAMEN_LESION);

        restHemorragieVisceresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHemorragieVisceres.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHemorragieVisceres))
            )
            .andExpect(status().isOk());

        // Validate the HemorragieVisceres in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHemorragieVisceresUpdatableFieldsEquals(
            partialUpdatedHemorragieVisceres,
            getPersistedHemorragieVisceres(partialUpdatedHemorragieVisceres)
        );
    }

    @Test
    @Transactional
    void patchNonExistingHemorragieVisceres() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragieVisceres.setId(longCount.incrementAndGet());

        // Create the HemorragieVisceres
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(hemorragieVisceres);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHemorragieVisceresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hemorragieVisceresDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hemorragieVisceresDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragieVisceres in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHemorragieVisceres() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragieVisceres.setId(longCount.incrementAndGet());

        // Create the HemorragieVisceres
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(hemorragieVisceres);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemorragieVisceresMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hemorragieVisceresDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HemorragieVisceres in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHemorragieVisceres() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hemorragieVisceres.setId(longCount.incrementAndGet());

        // Create the HemorragieVisceres
        HemorragieVisceresDTO hemorragieVisceresDTO = hemorragieVisceresMapper.toDto(hemorragieVisceres);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHemorragieVisceresMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hemorragieVisceresDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HemorragieVisceres in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHemorragieVisceres() throws Exception {
        // Initialize the database
        insertedHemorragieVisceres = hemorragieVisceresRepository.saveAndFlush(hemorragieVisceres);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hemorragieVisceres
        restHemorragieVisceresMockMvc
            .perform(delete(ENTITY_API_URL_ID, hemorragieVisceres.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hemorragieVisceresRepository.count();
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

    protected HemorragieVisceres getPersistedHemorragieVisceres(HemorragieVisceres hemorragieVisceres) {
        return hemorragieVisceresRepository.findById(hemorragieVisceres.getId()).orElseThrow();
    }

    protected void assertPersistedHemorragieVisceresToMatchAllProperties(HemorragieVisceres expectedHemorragieVisceres) {
        assertHemorragieVisceresAllPropertiesEquals(expectedHemorragieVisceres, getPersistedHemorragieVisceres(expectedHemorragieVisceres));
    }

    protected void assertPersistedHemorragieVisceresToMatchUpdatableProperties(HemorragieVisceres expectedHemorragieVisceres) {
        assertHemorragieVisceresAllUpdatablePropertiesEquals(
            expectedHemorragieVisceres,
            getPersistedHemorragieVisceres(expectedHemorragieVisceres)
        );
    }
}
