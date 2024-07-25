package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucLoaiSoCongChungAsserts.*;
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
import vn.vnpt.domain.DanhMucLoaiSoCongChung;
import vn.vnpt.repository.DanhMucLoaiSoCongChungRepository;
import vn.vnpt.service.dto.DanhMucLoaiSoCongChungDTO;
import vn.vnpt.service.mapper.DanhMucLoaiSoCongChungMapper;

/**
 * Integration tests for the {@link DanhMucLoaiSoCongChungResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucLoaiSoCongChungResourceIT {

    private static final Long DEFAULT_ID_LOAI = 1L;
    private static final Long UPDATED_ID_LOAI = 2L;

    private static final String DEFAULT_TEN_LOAI = "AAAAAAAAAA";
    private static final String UPDATED_TEN_LOAI = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String ENTITY_API_URL = "/api/danh-muc-loai-so-cong-chungs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucLoaiSoCongChungRepository danhMucLoaiSoCongChungRepository;

    @Autowired
    private DanhMucLoaiSoCongChungMapper danhMucLoaiSoCongChungMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucLoaiSoCongChungMockMvc;

    private DanhMucLoaiSoCongChung danhMucLoaiSoCongChung;

    private DanhMucLoaiSoCongChung insertedDanhMucLoaiSoCongChung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiSoCongChung createEntity(EntityManager em) {
        DanhMucLoaiSoCongChung danhMucLoaiSoCongChung = new DanhMucLoaiSoCongChung()
            .idLoai(DEFAULT_ID_LOAI)
            .tenLoai(DEFAULT_TEN_LOAI)
            .trangThai(DEFAULT_TRANG_THAI);
        return danhMucLoaiSoCongChung;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiSoCongChung createUpdatedEntity(EntityManager em) {
        DanhMucLoaiSoCongChung danhMucLoaiSoCongChung = new DanhMucLoaiSoCongChung()
            .idLoai(UPDATED_ID_LOAI)
            .tenLoai(UPDATED_TEN_LOAI)
            .trangThai(UPDATED_TRANG_THAI);
        return danhMucLoaiSoCongChung;
    }

    @BeforeEach
    public void initTest() {
        danhMucLoaiSoCongChung = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucLoaiSoCongChung != null) {
            danhMucLoaiSoCongChungRepository.delete(insertedDanhMucLoaiSoCongChung);
            insertedDanhMucLoaiSoCongChung = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);
        var returnedDanhMucLoaiSoCongChungDTO = om.readValue(
            restDanhMucLoaiSoCongChungMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucLoaiSoCongChungDTO.class
        );

        // Validate the DanhMucLoaiSoCongChung in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungMapper.toEntity(returnedDanhMucLoaiSoCongChungDTO);
        assertDanhMucLoaiSoCongChungUpdatableFieldsEquals(
            returnedDanhMucLoaiSoCongChung,
            getPersistedDanhMucLoaiSoCongChung(returnedDanhMucLoaiSoCongChung)
        );

        insertedDanhMucLoaiSoCongChung = returnedDanhMucLoaiSoCongChung;
    }

    @Test
    @Transactional
    void createDanhMucLoaiSoCongChungWithExistingId() throws Exception {
        // Create the DanhMucLoaiSoCongChung with an existing ID
        danhMucLoaiSoCongChung.setId(1L);
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucLoaiSoCongChungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiSoCongChungs() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.saveAndFlush(danhMucLoaiSoCongChung);

        // Get all the danhMucLoaiSoCongChungList
        restDanhMucLoaiSoCongChungMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucLoaiSoCongChung.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLoai").value(hasItem(DEFAULT_ID_LOAI.intValue())))
            .andExpect(jsonPath("$.[*].tenLoai").value(hasItem(DEFAULT_TEN_LOAI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    @Transactional
    void getDanhMucLoaiSoCongChung() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.saveAndFlush(danhMucLoaiSoCongChung);

        // Get the danhMucLoaiSoCongChung
        restDanhMucLoaiSoCongChungMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucLoaiSoCongChung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucLoaiSoCongChung.getId().intValue()))
            .andExpect(jsonPath("$.idLoai").value(DEFAULT_ID_LOAI.intValue()))
            .andExpect(jsonPath("$.tenLoai").value(DEFAULT_TEN_LOAI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucLoaiSoCongChung() throws Exception {
        // Get the danhMucLoaiSoCongChung
        restDanhMucLoaiSoCongChungMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucLoaiSoCongChung() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.saveAndFlush(danhMucLoaiSoCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiSoCongChung
        DanhMucLoaiSoCongChung updatedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository
            .findById(danhMucLoaiSoCongChung.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucLoaiSoCongChung are not directly saved in db
        em.detach(updatedDanhMucLoaiSoCongChung);
        updatedDanhMucLoaiSoCongChung.idLoai(UPDATED_ID_LOAI).tenLoai(UPDATED_TEN_LOAI).trangThai(UPDATED_TRANG_THAI);
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(updatedDanhMucLoaiSoCongChung);

        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiSoCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucLoaiSoCongChungToMatchAllProperties(updatedDanhMucLoaiSoCongChung);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiSoCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucLoaiSoCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.saveAndFlush(danhMucLoaiSoCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiSoCongChung using partial update
        DanhMucLoaiSoCongChung partialUpdatedDanhMucLoaiSoCongChung = new DanhMucLoaiSoCongChung();
        partialUpdatedDanhMucLoaiSoCongChung.setId(danhMucLoaiSoCongChung.getId());

        partialUpdatedDanhMucLoaiSoCongChung.idLoai(UPDATED_ID_LOAI).trangThai(UPDATED_TRANG_THAI);

        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiSoCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiSoCongChung))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiSoCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiSoCongChungUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucLoaiSoCongChung, danhMucLoaiSoCongChung),
            getPersistedDanhMucLoaiSoCongChung(danhMucLoaiSoCongChung)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucLoaiSoCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.saveAndFlush(danhMucLoaiSoCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiSoCongChung using partial update
        DanhMucLoaiSoCongChung partialUpdatedDanhMucLoaiSoCongChung = new DanhMucLoaiSoCongChung();
        partialUpdatedDanhMucLoaiSoCongChung.setId(danhMucLoaiSoCongChung.getId());

        partialUpdatedDanhMucLoaiSoCongChung.idLoai(UPDATED_ID_LOAI).tenLoai(UPDATED_TEN_LOAI).trangThai(UPDATED_TRANG_THAI);

        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiSoCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiSoCongChung))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiSoCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiSoCongChungUpdatableFieldsEquals(
            partialUpdatedDanhMucLoaiSoCongChung,
            getPersistedDanhMucLoaiSoCongChung(partialUpdatedDanhMucLoaiSoCongChung)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucLoaiSoCongChungDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucLoaiSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiSoCongChung.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiSoCongChung
        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucLoaiSoCongChungDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiSoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucLoaiSoCongChung() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.saveAndFlush(danhMucLoaiSoCongChung);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucLoaiSoCongChung
        restDanhMucLoaiSoCongChungMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucLoaiSoCongChung.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucLoaiSoCongChungRepository.count();
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

    protected DanhMucLoaiSoCongChung getPersistedDanhMucLoaiSoCongChung(DanhMucLoaiSoCongChung danhMucLoaiSoCongChung) {
        return danhMucLoaiSoCongChungRepository.findById(danhMucLoaiSoCongChung.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucLoaiSoCongChungToMatchAllProperties(DanhMucLoaiSoCongChung expectedDanhMucLoaiSoCongChung) {
        assertDanhMucLoaiSoCongChungAllPropertiesEquals(
            expectedDanhMucLoaiSoCongChung,
            getPersistedDanhMucLoaiSoCongChung(expectedDanhMucLoaiSoCongChung)
        );
    }

    protected void assertPersistedDanhMucLoaiSoCongChungToMatchUpdatableProperties(DanhMucLoaiSoCongChung expectedDanhMucLoaiSoCongChung) {
        assertDanhMucLoaiSoCongChungAllUpdatablePropertiesEquals(
            expectedDanhMucLoaiSoCongChung,
            getPersistedDanhMucLoaiSoCongChung(expectedDanhMucLoaiSoCongChung)
        );
    }
}
