package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucNhomHopDongAsserts.*;
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
import vn.vnpt.domain.DanhMucNhomHopDong;
import vn.vnpt.repository.DanhMucNhomHopDongRepository;
import vn.vnpt.service.dto.DanhMucNhomHopDongDTO;
import vn.vnpt.service.mapper.DanhMucNhomHopDongMapper;

/**
 * Integration tests for the {@link DanhMucNhomHopDongResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucNhomHopDongResourceIT {

    private static final Long DEFAULT_ID_NHOM = 1L;
    private static final Long UPDATED_ID_NHOM = 2L;

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/danh-muc-nhom-hop-dongs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucNhomHopDongRepository danhMucNhomHopDongRepository;

    @Autowired
    private DanhMucNhomHopDongMapper danhMucNhomHopDongMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucNhomHopDongMockMvc;

    private DanhMucNhomHopDong danhMucNhomHopDong;

    private DanhMucNhomHopDong insertedDanhMucNhomHopDong;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucNhomHopDong createEntity(EntityManager em) {
        DanhMucNhomHopDong danhMucNhomHopDong = new DanhMucNhomHopDong().idNhom(DEFAULT_ID_NHOM).dienGiai(DEFAULT_DIEN_GIAI);
        return danhMucNhomHopDong;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucNhomHopDong createUpdatedEntity(EntityManager em) {
        DanhMucNhomHopDong danhMucNhomHopDong = new DanhMucNhomHopDong().idNhom(UPDATED_ID_NHOM).dienGiai(UPDATED_DIEN_GIAI);
        return danhMucNhomHopDong;
    }

    @BeforeEach
    public void initTest() {
        danhMucNhomHopDong = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucNhomHopDong != null) {
            danhMucNhomHopDongRepository.delete(insertedDanhMucNhomHopDong);
            insertedDanhMucNhomHopDong = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucNhomHopDong() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucNhomHopDong
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);
        var returnedDanhMucNhomHopDongDTO = om.readValue(
            restDanhMucNhomHopDongMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucNhomHopDongDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucNhomHopDongDTO.class
        );

        // Validate the DanhMucNhomHopDong in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucNhomHopDong = danhMucNhomHopDongMapper.toEntity(returnedDanhMucNhomHopDongDTO);
        assertDanhMucNhomHopDongUpdatableFieldsEquals(
            returnedDanhMucNhomHopDong,
            getPersistedDanhMucNhomHopDong(returnedDanhMucNhomHopDong)
        );

        insertedDanhMucNhomHopDong = returnedDanhMucNhomHopDong;
    }

    @Test
    @Transactional
    void createDanhMucNhomHopDongWithExistingId() throws Exception {
        // Create the DanhMucNhomHopDong with an existing ID
        danhMucNhomHopDong.setId(1L);
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucNhomHopDongMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucNhomHopDongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucNhomHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucNhomHopDongs() throws Exception {
        // Initialize the database
        insertedDanhMucNhomHopDong = danhMucNhomHopDongRepository.saveAndFlush(danhMucNhomHopDong);

        // Get all the danhMucNhomHopDongList
        restDanhMucNhomHopDongMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucNhomHopDong.getId().intValue())))
            .andExpect(jsonPath("$.[*].idNhom").value(hasItem(DEFAULT_ID_NHOM.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)));
    }

    @Test
    @Transactional
    void getDanhMucNhomHopDong() throws Exception {
        // Initialize the database
        insertedDanhMucNhomHopDong = danhMucNhomHopDongRepository.saveAndFlush(danhMucNhomHopDong);

        // Get the danhMucNhomHopDong
        restDanhMucNhomHopDongMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucNhomHopDong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucNhomHopDong.getId().intValue()))
            .andExpect(jsonPath("$.idNhom").value(DEFAULT_ID_NHOM.intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucNhomHopDong() throws Exception {
        // Get the danhMucNhomHopDong
        restDanhMucNhomHopDongMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucNhomHopDong() throws Exception {
        // Initialize the database
        insertedDanhMucNhomHopDong = danhMucNhomHopDongRepository.saveAndFlush(danhMucNhomHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucNhomHopDong
        DanhMucNhomHopDong updatedDanhMucNhomHopDong = danhMucNhomHopDongRepository.findById(danhMucNhomHopDong.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucNhomHopDong are not directly saved in db
        em.detach(updatedDanhMucNhomHopDong);
        updatedDanhMucNhomHopDong.idNhom(UPDATED_ID_NHOM).dienGiai(UPDATED_DIEN_GIAI);
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(updatedDanhMucNhomHopDong);

        restDanhMucNhomHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucNhomHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucNhomHopDongDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucNhomHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucNhomHopDongToMatchAllProperties(updatedDanhMucNhomHopDong);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucNhomHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucNhomHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucNhomHopDong
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucNhomHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucNhomHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucNhomHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucNhomHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucNhomHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucNhomHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucNhomHopDong
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucNhomHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucNhomHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucNhomHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucNhomHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucNhomHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucNhomHopDong
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucNhomHopDongMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucNhomHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucNhomHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucNhomHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucNhomHopDong = danhMucNhomHopDongRepository.saveAndFlush(danhMucNhomHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucNhomHopDong using partial update
        DanhMucNhomHopDong partialUpdatedDanhMucNhomHopDong = new DanhMucNhomHopDong();
        partialUpdatedDanhMucNhomHopDong.setId(danhMucNhomHopDong.getId());

        partialUpdatedDanhMucNhomHopDong.idNhom(UPDATED_ID_NHOM).dienGiai(UPDATED_DIEN_GIAI);

        restDanhMucNhomHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucNhomHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucNhomHopDong))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucNhomHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucNhomHopDongUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucNhomHopDong, danhMucNhomHopDong),
            getPersistedDanhMucNhomHopDong(danhMucNhomHopDong)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucNhomHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucNhomHopDong = danhMucNhomHopDongRepository.saveAndFlush(danhMucNhomHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucNhomHopDong using partial update
        DanhMucNhomHopDong partialUpdatedDanhMucNhomHopDong = new DanhMucNhomHopDong();
        partialUpdatedDanhMucNhomHopDong.setId(danhMucNhomHopDong.getId());

        partialUpdatedDanhMucNhomHopDong.idNhom(UPDATED_ID_NHOM).dienGiai(UPDATED_DIEN_GIAI);

        restDanhMucNhomHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucNhomHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucNhomHopDong))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucNhomHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucNhomHopDongUpdatableFieldsEquals(
            partialUpdatedDanhMucNhomHopDong,
            getPersistedDanhMucNhomHopDong(partialUpdatedDanhMucNhomHopDong)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucNhomHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucNhomHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucNhomHopDong
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucNhomHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucNhomHopDongDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucNhomHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucNhomHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucNhomHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucNhomHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucNhomHopDong
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucNhomHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucNhomHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucNhomHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucNhomHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucNhomHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucNhomHopDong
        DanhMucNhomHopDongDTO danhMucNhomHopDongDTO = danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucNhomHopDongMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucNhomHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucNhomHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucNhomHopDong() throws Exception {
        // Initialize the database
        insertedDanhMucNhomHopDong = danhMucNhomHopDongRepository.saveAndFlush(danhMucNhomHopDong);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucNhomHopDong
        restDanhMucNhomHopDongMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucNhomHopDong.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucNhomHopDongRepository.count();
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

    protected DanhMucNhomHopDong getPersistedDanhMucNhomHopDong(DanhMucNhomHopDong danhMucNhomHopDong) {
        return danhMucNhomHopDongRepository.findById(danhMucNhomHopDong.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucNhomHopDongToMatchAllProperties(DanhMucNhomHopDong expectedDanhMucNhomHopDong) {
        assertDanhMucNhomHopDongAllPropertiesEquals(expectedDanhMucNhomHopDong, getPersistedDanhMucNhomHopDong(expectedDanhMucNhomHopDong));
    }

    protected void assertPersistedDanhMucNhomHopDongToMatchUpdatableProperties(DanhMucNhomHopDong expectedDanhMucNhomHopDong) {
        assertDanhMucNhomHopDongAllUpdatablePropertiesEquals(
            expectedDanhMucNhomHopDong,
            getPersistedDanhMucNhomHopDong(expectedDanhMucNhomHopDong)
        );
    }
}
