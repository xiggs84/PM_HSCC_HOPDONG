package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.SoCongChungAsserts.*;
import static vn.vnpt.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
import vn.vnpt.domain.SoCongChung;
import vn.vnpt.repository.SoCongChungRepository;
import vn.vnpt.service.dto.SoCongChungDTO;
import vn.vnpt.service.mapper.SoCongChungMapper;

/**
 * Integration tests for the {@link SoCongChungResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SoCongChungResourceIT {

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_SO = 1L;
    private static final Long UPDATED_ID_SO = 2L;

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final String DEFAULT_TEN_SO = "AAAAAAAAAA";
    private static final String UPDATED_TEN_SO = "BBBBBBBBBB";

    private static final Long DEFAULT_GIA_TRI = 1L;
    private static final Long UPDATED_GIA_TRI = 2L;

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final Long DEFAULT_ID_LOAI_SO = 1L;
    private static final Long UPDATED_ID_LOAI_SO = 2L;

    private static final String ENTITY_API_URL = "/api/so-cong-chungs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SoCongChungRepository soCongChungRepository;

    @Autowired
    private SoCongChungMapper soCongChungMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSoCongChungMockMvc;

    private SoCongChung soCongChung;

    private SoCongChung insertedSoCongChung;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoCongChung createEntity(EntityManager em) {
        SoCongChung soCongChung = new SoCongChung()
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .idSo(DEFAULT_ID_SO)
            .idDonVi(DEFAULT_ID_DON_VI)
            .tenSo(DEFAULT_TEN_SO)
            .giaTri(DEFAULT_GIA_TRI)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .trangThai(DEFAULT_TRANG_THAI)
            .idLoaiSo(DEFAULT_ID_LOAI_SO);
        return soCongChung;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SoCongChung createUpdatedEntity(EntityManager em) {
        SoCongChung soCongChung = new SoCongChung()
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idSo(UPDATED_ID_SO)
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);
        return soCongChung;
    }

    @BeforeEach
    public void initTest() {
        soCongChung = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSoCongChung != null) {
            soCongChungRepository.delete(insertedSoCongChung);
            insertedSoCongChung = null;
        }
    }

    @Test
    @Transactional
    void createSoCongChung() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);
        var returnedSoCongChungDTO = om.readValue(
            restSoCongChungMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SoCongChungDTO.class
        );

        // Validate the SoCongChung in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedSoCongChung = soCongChungMapper.toEntity(returnedSoCongChungDTO);
        assertSoCongChungUpdatableFieldsEquals(returnedSoCongChung, getPersistedSoCongChung(returnedSoCongChung));

        insertedSoCongChung = returnedSoCongChung;
    }

    @Test
    @Transactional
    void createSoCongChungWithExistingId() throws Exception {
        // Create the SoCongChung with an existing ID
        soCongChung.setId(1L);
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoCongChungMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSoCongChungs() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.saveAndFlush(soCongChung);

        // Get all the soCongChungList
        restSoCongChungMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(soCongChung.getId().intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].idSo").value(hasItem(DEFAULT_ID_SO.intValue())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].tenSo").value(hasItem(DEFAULT_TEN_SO)))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.intValue())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].idLoaiSo").value(hasItem(DEFAULT_ID_LOAI_SO.intValue())));
    }

    @Test
    @Transactional
    void getSoCongChung() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.saveAndFlush(soCongChung);

        // Get the soCongChung
        restSoCongChungMockMvc
            .perform(get(ENTITY_API_URL_ID, soCongChung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(soCongChung.getId().intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.idSo").value(DEFAULT_ID_SO.intValue()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.tenSo").value(DEFAULT_TEN_SO))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.intValue()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.idLoaiSo").value(DEFAULT_ID_LOAI_SO.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingSoCongChung() throws Exception {
        // Get the soCongChung
        restSoCongChungMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSoCongChung() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.saveAndFlush(soCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChung
        SoCongChung updatedSoCongChung = soCongChungRepository.findById(soCongChung.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSoCongChung are not directly saved in db
        em.detach(updatedSoCongChung);
        updatedSoCongChung
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idSo(UPDATED_ID_SO)
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(updatedSoCongChung);

        restSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSoCongChungToMatchAllProperties(updatedSoCongChung);
    }

    @Test
    @Transactional
    void putNonExistingSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(longCount.incrementAndGet());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, soCongChungDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(longCount.incrementAndGet());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(longCount.incrementAndGet());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(soCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSoCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.saveAndFlush(soCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChung using partial update
        SoCongChung partialUpdatedSoCongChung = new SoCongChung();
        partialUpdatedSoCongChung.setId(soCongChung.getId());

        partialUpdatedSoCongChung
            .tenSo(UPDATED_TEN_SO)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);

        restSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoCongChung))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoCongChungUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSoCongChung, soCongChung),
            getPersistedSoCongChung(soCongChung)
        );
    }

    @Test
    @Transactional
    void fullUpdateSoCongChungWithPatch() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.saveAndFlush(soCongChung);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the soCongChung using partial update
        SoCongChung partialUpdatedSoCongChung = new SoCongChung();
        partialUpdatedSoCongChung.setId(soCongChung.getId());

        partialUpdatedSoCongChung
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .idSo(UPDATED_ID_SO)
            .idDonVi(UPDATED_ID_DON_VI)
            .tenSo(UPDATED_TEN_SO)
            .giaTri(UPDATED_GIA_TRI)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .trangThai(UPDATED_TRANG_THAI)
            .idLoaiSo(UPDATED_ID_LOAI_SO);

        restSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSoCongChung.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSoCongChung))
            )
            .andExpect(status().isOk());

        // Validate the SoCongChung in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSoCongChungUpdatableFieldsEquals(partialUpdatedSoCongChung, getPersistedSoCongChung(partialUpdatedSoCongChung));
    }

    @Test
    @Transactional
    void patchNonExistingSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(longCount.incrementAndGet());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, soCongChungDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(longCount.incrementAndGet());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(soCongChungDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSoCongChung() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        soCongChung.setId(longCount.incrementAndGet());

        // Create the SoCongChung
        SoCongChungDTO soCongChungDTO = soCongChungMapper.toDto(soCongChung);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSoCongChungMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(soCongChungDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SoCongChung in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSoCongChung() throws Exception {
        // Initialize the database
        insertedSoCongChung = soCongChungRepository.saveAndFlush(soCongChung);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the soCongChung
        restSoCongChungMockMvc
            .perform(delete(ENTITY_API_URL_ID, soCongChung.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return soCongChungRepository.count();
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

    protected SoCongChung getPersistedSoCongChung(SoCongChung soCongChung) {
        return soCongChungRepository.findById(soCongChung.getId()).orElseThrow();
    }

    protected void assertPersistedSoCongChungToMatchAllProperties(SoCongChung expectedSoCongChung) {
        assertSoCongChungAllPropertiesEquals(expectedSoCongChung, getPersistedSoCongChung(expectedSoCongChung));
    }

    protected void assertPersistedSoCongChungToMatchUpdatableProperties(SoCongChung expectedSoCongChung) {
        assertSoCongChungAllUpdatablePropertiesEquals(expectedSoCongChung, getPersistedSoCongChung(expectedSoCongChung));
    }
}
