package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.PhanLoaiHopDongAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.IntegrationTest;
import vn.vnpt.domain.PhanLoaiHopDong;
import vn.vnpt.repository.PhanLoaiHopDongRepository;
import vn.vnpt.service.dto.PhanLoaiHopDongDTO;
import vn.vnpt.service.mapper.PhanLoaiHopDongMapper;

/**
 * Integration tests for the {@link PhanLoaiHopDongResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PhanLoaiHopDongResourceIT {

    private static final Long DEFAULT_ID_PHAN_LOAI_HOP_DONG = 1L;
    private static final Long UPDATED_ID_PHAN_LOAI_HOP_DONG = 2L;

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/phan-loai-hop-dongs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PhanLoaiHopDongRepository phanLoaiHopDongRepository;

    @Autowired
    private PhanLoaiHopDongMapper phanLoaiHopDongMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPhanLoaiHopDongMockMvc;

    private PhanLoaiHopDong phanLoaiHopDong;

    private PhanLoaiHopDong insertedPhanLoaiHopDong;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhanLoaiHopDong createEntity(EntityManager em) {
        PhanLoaiHopDong phanLoaiHopDong = new PhanLoaiHopDong()
            .idPhanLoaiHopDong(DEFAULT_ID_PHAN_LOAI_HOP_DONG)
            .dienGiai(DEFAULT_DIEN_GIAI);
        return phanLoaiHopDong;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhanLoaiHopDong createUpdatedEntity(EntityManager em) {
        PhanLoaiHopDong phanLoaiHopDong = new PhanLoaiHopDong()
            .idPhanLoaiHopDong(UPDATED_ID_PHAN_LOAI_HOP_DONG)
            .dienGiai(UPDATED_DIEN_GIAI);
        return phanLoaiHopDong;
    }

    @BeforeEach
    public void initTest() {
        phanLoaiHopDong = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedPhanLoaiHopDong != null) {
            phanLoaiHopDongRepository.delete(insertedPhanLoaiHopDong);
            insertedPhanLoaiHopDong = null;
        }
    }

    @Test
    @Transactional
    void createPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);
        var returnedPhanLoaiHopDongDTO = om.readValue(
            restPhanLoaiHopDongMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phanLoaiHopDongDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PhanLoaiHopDongDTO.class
        );

        // Validate the PhanLoaiHopDong in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPhanLoaiHopDong = phanLoaiHopDongMapper.toEntity(returnedPhanLoaiHopDongDTO);
        assertPhanLoaiHopDongUpdatableFieldsEquals(returnedPhanLoaiHopDong, getPersistedPhanLoaiHopDong(returnedPhanLoaiHopDong));

        insertedPhanLoaiHopDong = returnedPhanLoaiHopDong;
    }

    @Test
    @Transactional
    void createPhanLoaiHopDongWithExistingId() throws Exception {
        // Create the PhanLoaiHopDong with an existing ID
        phanLoaiHopDong.setId(1L);
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhanLoaiHopDongMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phanLoaiHopDongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPhanLoaiHopDongs() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.saveAndFlush(phanLoaiHopDong);

        // Get all the phanLoaiHopDongList
        restPhanLoaiHopDongMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phanLoaiHopDong.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPhanLoaiHopDong").value(hasItem(DEFAULT_ID_PHAN_LOAI_HOP_DONG.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)));
    }

    @Test
    @Transactional
    void getPhanLoaiHopDong() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.saveAndFlush(phanLoaiHopDong);

        // Get the phanLoaiHopDong
        restPhanLoaiHopDongMockMvc
            .perform(get(ENTITY_API_URL_ID, phanLoaiHopDong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(phanLoaiHopDong.getId().intValue()))
            .andExpect(jsonPath("$.idPhanLoaiHopDong").value(DEFAULT_ID_PHAN_LOAI_HOP_DONG.intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI));
    }

    @Test
    @Transactional
    void getNonExistingPhanLoaiHopDong() throws Exception {
        // Get the phanLoaiHopDong
        restPhanLoaiHopDongMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPhanLoaiHopDong() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.saveAndFlush(phanLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phanLoaiHopDong
        PhanLoaiHopDong updatedPhanLoaiHopDong = phanLoaiHopDongRepository.findById(phanLoaiHopDong.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPhanLoaiHopDong are not directly saved in db
        em.detach(updatedPhanLoaiHopDong);
        updatedPhanLoaiHopDong.idPhanLoaiHopDong(UPDATED_ID_PHAN_LOAI_HOP_DONG).dienGiai(UPDATED_DIEN_GIAI);
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(updatedPhanLoaiHopDong);

        restPhanLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, phanLoaiHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isOk());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPhanLoaiHopDongToMatchAllProperties(updatedPhanLoaiHopDong);
    }

    @Test
    @Transactional
    void putNonExistingPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, phanLoaiHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(phanLoaiHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePhanLoaiHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.saveAndFlush(phanLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phanLoaiHopDong using partial update
        PhanLoaiHopDong partialUpdatedPhanLoaiHopDong = new PhanLoaiHopDong();
        partialUpdatedPhanLoaiHopDong.setId(phanLoaiHopDong.getId());

        partialUpdatedPhanLoaiHopDong.dienGiai(UPDATED_DIEN_GIAI);

        restPhanLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhanLoaiHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPhanLoaiHopDong))
            )
            .andExpect(status().isOk());

        // Validate the PhanLoaiHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPhanLoaiHopDongUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPhanLoaiHopDong, phanLoaiHopDong),
            getPersistedPhanLoaiHopDong(phanLoaiHopDong)
        );
    }

    @Test
    @Transactional
    void fullUpdatePhanLoaiHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.saveAndFlush(phanLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the phanLoaiHopDong using partial update
        PhanLoaiHopDong partialUpdatedPhanLoaiHopDong = new PhanLoaiHopDong();
        partialUpdatedPhanLoaiHopDong.setId(phanLoaiHopDong.getId());

        partialUpdatedPhanLoaiHopDong.idPhanLoaiHopDong(UPDATED_ID_PHAN_LOAI_HOP_DONG).dienGiai(UPDATED_DIEN_GIAI);

        restPhanLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPhanLoaiHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPhanLoaiHopDong))
            )
            .andExpect(status().isOk());

        // Validate the PhanLoaiHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPhanLoaiHopDongUpdatableFieldsEquals(
            partialUpdatedPhanLoaiHopDong,
            getPersistedPhanLoaiHopDong(partialUpdatedPhanLoaiHopDong)
        );
    }

    @Test
    @Transactional
    void patchNonExistingPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, phanLoaiHopDongDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(phanLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPhanLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        phanLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the PhanLoaiHopDong
        PhanLoaiHopDongDTO phanLoaiHopDongDTO = phanLoaiHopDongMapper.toDto(phanLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPhanLoaiHopDongMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(phanLoaiHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PhanLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePhanLoaiHopDong() throws Exception {
        // Initialize the database
        insertedPhanLoaiHopDong = phanLoaiHopDongRepository.saveAndFlush(phanLoaiHopDong);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the phanLoaiHopDong
        restPhanLoaiHopDongMockMvc
            .perform(delete(ENTITY_API_URL_ID, phanLoaiHopDong.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return phanLoaiHopDongRepository.count();
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

    protected PhanLoaiHopDong getPersistedPhanLoaiHopDong(PhanLoaiHopDong phanLoaiHopDong) {
        return phanLoaiHopDongRepository.findById(phanLoaiHopDong.getId()).orElseThrow();
    }

    protected void assertPersistedPhanLoaiHopDongToMatchAllProperties(PhanLoaiHopDong expectedPhanLoaiHopDong) {
        assertPhanLoaiHopDongAllPropertiesEquals(expectedPhanLoaiHopDong, getPersistedPhanLoaiHopDong(expectedPhanLoaiHopDong));
    }

    protected void assertPersistedPhanLoaiHopDongToMatchUpdatableProperties(PhanLoaiHopDong expectedPhanLoaiHopDong) {
        assertPhanLoaiHopDongAllUpdatablePropertiesEquals(expectedPhanLoaiHopDong, getPersistedPhanLoaiHopDong(expectedPhanLoaiHopDong));
    }
}
