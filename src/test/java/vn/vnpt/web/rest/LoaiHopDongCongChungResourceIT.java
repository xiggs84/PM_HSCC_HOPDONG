package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.LoaiHopDongCongChungAsserts.*;
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
import vn.vnpt.domain.LoaiHopDongCongChung;
import vn.vnpt.repository.LoaiHopDongCongChungRepository;
import vn.vnpt.service.dto.LoaiHopDongCongChungDTO;
import vn.vnpt.service.mapper.LoaiHopDongCongChungMapper;

/**
 * Integration tests for the {@link LoaiHopDongCongChungResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LoaiHopDongCongChungResourceIT {

    private static final Long DEFAULT_ID_LOAI_HOP_DONG_CONG_CHUNG = 1L;
    private static final Long UPDATED_ID_LOAI_HOP_DONG_CONG_CHUNG = 2L;

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_GIA_TRI = 1L;
    private static final Long UPDATED_GIA_TRI = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String ENTITY_API_URL = "/api/loai-hop-dong-cong-chungs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LoaiHopDongCongChungRepository loaiHopDongCongChungRepository;

    @Autowired
    private LoaiHopDongCongChungMapper loaiHopDongCongChungMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoaiHopDongCongChungMockMvc;

    private LoaiHopDongCongChung loaiHopDongCongChung;

    private LoaiHopDongCongChung insertedLoaiHopDongCongChung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiHopDongCongChung createEntity(EntityManager em) {
        LoaiHopDongCongChung loaiHopDongCongChung = new LoaiHopDongCongChung()
            .idLoaiHopDongCongChung(DEFAULT_ID_LOAI_HOP_DONG_CONG_CHUNG)
            .dienGiai(DEFAULT_DIEN_GIAI)
            .giaTri(DEFAULT_GIA_TRI)
            .trangThai(DEFAULT_TRANG_THAI);
        return loaiHopDongCongChung;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LoaiHopDongCongChung createUpdatedEntity(EntityManager em) {
        LoaiHopDongCongChung loaiHopDongCongChung = new LoaiHopDongCongChung()
            .idLoaiHopDongCongChung(UPDATED_ID_LOAI_HOP_DONG_CONG_CHUNG)
            .dienGiai(UPDATED_DIEN_GIAI)
            .giaTri(UPDATED_GIA_TRI)
            .trangThai(UPDATED_TRANG_THAI);
        return loaiHopDongCongChung;
    }

    @BeforeEach
    public void initTest() {
        loaiHopDongCongChung = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedLoaiHopDongCongChung != null) {
            loaiHopDongCongChungRepository.delete(insertedLoaiHopDongCongChung);
            insertedLoaiHopDongCongChung = null;
        }
    }

    @Test
    @Transactional
    void createLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);
        var returnedLoaiHopDongCongChungDTO = om.readValue(
            restLoaiHopDongCongChungMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            LoaiHopDongCongChungDTO.class
        );

        // Validate the LoaiHopDongCongChung in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLoaiHopDongCongChung = loaiHopDongCongChungMapper.toEntity(returnedLoaiHopDongCongChungDTO);
        assertLoaiHopDongCongChungUpdatableFieldsEquals(
            returnedLoaiHopDongCongChung,
            getPersistedLoaiHopDongCongChung(returnedLoaiHopDongCongChung)
        );

        insertedLoaiHopDongCongChung = returnedLoaiHopDongCongChung;
    }

    @Test
    @Transactional
    void createLoaiHopDongCongChungWithExistingId() throws Exception {
        // Create the LoaiHopDongCongChung with an existing ID
        loaiHopDongCongChung.setId(1L);
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoaiHopDongCongChungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiHopDongCongChungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLoaiHopDongCongChungs() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.saveAndFlush(loaiHopDongCongChung);

        // Get all the loaiHopDongCongChungList
        restLoaiHopDongCongChungMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loaiHopDongCongChung.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLoaiHopDongCongChung").value(hasItem(DEFAULT_ID_LOAI_HOP_DONG_CONG_CHUNG.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    @Transactional
    void getLoaiHopDongCongChung() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.saveAndFlush(loaiHopDongCongChung);

        // Get the loaiHopDongCongChung
        restLoaiHopDongCongChungMockMvc
            .perform(get(ENTITY_API_URL_ID, loaiHopDongCongChung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loaiHopDongCongChung.getId().intValue()))
            .andExpect(jsonPath("$.idLoaiHopDongCongChung").value(DEFAULT_ID_LOAI_HOP_DONG_CONG_CHUNG.intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingLoaiHopDongCongChung() throws Exception {
        // Get the loaiHopDongCongChung
        restLoaiHopDongCongChungMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLoaiHopDongCongChung() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.saveAndFlush(loaiHopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiHopDongCongChung
        LoaiHopDongCongChung updatedLoaiHopDongCongChung = loaiHopDongCongChungRepository
            .findById(loaiHopDongCongChung.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedLoaiHopDongCongChung are not directly saved in db
        em.detach(updatedLoaiHopDongCongChung);
        updatedLoaiHopDongCongChung
            .idLoaiHopDongCongChung(UPDATED_ID_LOAI_HOP_DONG_CONG_CHUNG)
            .dienGiai(UPDATED_DIEN_GIAI)
            .giaTri(UPDATED_GIA_TRI)
            .trangThai(UPDATED_TRANG_THAI);
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(updatedLoaiHopDongCongChung);

        restLoaiHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiHopDongCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isOk());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLoaiHopDongCongChungToMatchAllProperties(updatedLoaiHopDongCongChung);
    }

    @Test
    @Transactional
    void putNonExistingLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(longCount.incrementAndGet());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, loaiHopDongCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(longCount.incrementAndGet());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(longCount.incrementAndGet());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(loaiHopDongCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLoaiHopDongCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.saveAndFlush(loaiHopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiHopDongCongChung using partial update
        LoaiHopDongCongChung partialUpdatedLoaiHopDongCongChung = new LoaiHopDongCongChung();
        partialUpdatedLoaiHopDongCongChung.setId(loaiHopDongCongChung.getId());

        partialUpdatedLoaiHopDongCongChung
            .idLoaiHopDongCongChung(UPDATED_ID_LOAI_HOP_DONG_CONG_CHUNG)
            .dienGiai(UPDATED_DIEN_GIAI)
            .giaTri(UPDATED_GIA_TRI)
            .trangThai(UPDATED_TRANG_THAI);

        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiHopDongCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiHopDongCongChung))
            )
            .andExpect(status().isOk());

        // Validate the LoaiHopDongCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiHopDongCongChungUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLoaiHopDongCongChung, loaiHopDongCongChung),
            getPersistedLoaiHopDongCongChung(loaiHopDongCongChung)
        );
    }

    @Test
    @Transactional
    void fullUpdateLoaiHopDongCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.saveAndFlush(loaiHopDongCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the loaiHopDongCongChung using partial update
        LoaiHopDongCongChung partialUpdatedLoaiHopDongCongChung = new LoaiHopDongCongChung();
        partialUpdatedLoaiHopDongCongChung.setId(loaiHopDongCongChung.getId());

        partialUpdatedLoaiHopDongCongChung
            .idLoaiHopDongCongChung(UPDATED_ID_LOAI_HOP_DONG_CONG_CHUNG)
            .dienGiai(UPDATED_DIEN_GIAI)
            .giaTri(UPDATED_GIA_TRI)
            .trangThai(UPDATED_TRANG_THAI);

        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLoaiHopDongCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLoaiHopDongCongChung))
            )
            .andExpect(status().isOk());

        // Validate the LoaiHopDongCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLoaiHopDongCongChungUpdatableFieldsEquals(
            partialUpdatedLoaiHopDongCongChung,
            getPersistedLoaiHopDongCongChung(partialUpdatedLoaiHopDongCongChung)
        );
    }

    @Test
    @Transactional
    void patchNonExistingLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(longCount.incrementAndGet());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, loaiHopDongCongChungDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(longCount.incrementAndGet());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLoaiHopDongCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        loaiHopDongCongChung.setId(longCount.incrementAndGet());

        // Create the LoaiHopDongCongChung
        LoaiHopDongCongChungDTO loaiHopDongCongChungDTO = loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLoaiHopDongCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(loaiHopDongCongChungDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LoaiHopDongCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLoaiHopDongCongChung() throws Exception {
        // Initialize the database
        insertedLoaiHopDongCongChung = loaiHopDongCongChungRepository.saveAndFlush(loaiHopDongCongChung);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the loaiHopDongCongChung
        restLoaiHopDongCongChungMockMvc
            .perform(delete(ENTITY_API_URL_ID, loaiHopDongCongChung.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return loaiHopDongCongChungRepository.count();
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

    protected LoaiHopDongCongChung getPersistedLoaiHopDongCongChung(LoaiHopDongCongChung loaiHopDongCongChung) {
        return loaiHopDongCongChungRepository.findById(loaiHopDongCongChung.getId()).orElseThrow();
    }

    protected void assertPersistedLoaiHopDongCongChungToMatchAllProperties(LoaiHopDongCongChung expectedLoaiHopDongCongChung) {
        assertLoaiHopDongCongChungAllPropertiesEquals(
            expectedLoaiHopDongCongChung,
            getPersistedLoaiHopDongCongChung(expectedLoaiHopDongCongChung)
        );
    }

    protected void assertPersistedLoaiHopDongCongChungToMatchUpdatableProperties(LoaiHopDongCongChung expectedLoaiHopDongCongChung) {
        assertLoaiHopDongCongChungAllUpdatablePropertiesEquals(
            expectedLoaiHopDongCongChung,
            getPersistedLoaiHopDongCongChung(expectedLoaiHopDongCongChung)
        );
    }
}
