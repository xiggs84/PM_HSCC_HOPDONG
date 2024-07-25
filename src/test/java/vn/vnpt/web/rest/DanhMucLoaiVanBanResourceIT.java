package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucLoaiVanBanAsserts.*;
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
import vn.vnpt.domain.DanhMucLoaiVanBan;
import vn.vnpt.repository.DanhMucLoaiVanBanRepository;
import vn.vnpt.service.dto.DanhMucLoaiVanBanDTO;
import vn.vnpt.service.mapper.DanhMucLoaiVanBanMapper;

/**
 * Integration tests for the {@link DanhMucLoaiVanBanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucLoaiVanBanResourceIT {

    private static final Long DEFAULT_ID_LOAI_VB = 1L;
    private static final Long UPDATED_ID_LOAI_VB = 2L;

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_LOAI_HOP_DONG = 1L;
    private static final Long UPDATED_ID_LOAI_HOP_DONG = 2L;

    private static final String ENTITY_API_URL = "/api/danh-muc-loai-van-bans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucLoaiVanBanRepository danhMucLoaiVanBanRepository;

    @Autowired
    private DanhMucLoaiVanBanMapper danhMucLoaiVanBanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucLoaiVanBanMockMvc;

    private DanhMucLoaiVanBan danhMucLoaiVanBan;

    private DanhMucLoaiVanBan insertedDanhMucLoaiVanBan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiVanBan createEntity(EntityManager em) {
        DanhMucLoaiVanBan danhMucLoaiVanBan = new DanhMucLoaiVanBan()
            .idLoaiVb(DEFAULT_ID_LOAI_VB)
            .dienGiai(DEFAULT_DIEN_GIAI)
            .idLoaiHopDong(DEFAULT_ID_LOAI_HOP_DONG);
        return danhMucLoaiVanBan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiVanBan createUpdatedEntity(EntityManager em) {
        DanhMucLoaiVanBan danhMucLoaiVanBan = new DanhMucLoaiVanBan()
            .idLoaiVb(UPDATED_ID_LOAI_VB)
            .dienGiai(UPDATED_DIEN_GIAI)
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG);
        return danhMucLoaiVanBan;
    }

    @BeforeEach
    public void initTest() {
        danhMucLoaiVanBan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucLoaiVanBan != null) {
            danhMucLoaiVanBanRepository.delete(insertedDanhMucLoaiVanBan);
            insertedDanhMucLoaiVanBan = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucLoaiVanBan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucLoaiVanBan
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);
        var returnedDanhMucLoaiVanBanDTO = om.readValue(
            restDanhMucLoaiVanBanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiVanBanDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucLoaiVanBanDTO.class
        );

        // Validate the DanhMucLoaiVanBan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucLoaiVanBan = danhMucLoaiVanBanMapper.toEntity(returnedDanhMucLoaiVanBanDTO);
        assertDanhMucLoaiVanBanUpdatableFieldsEquals(returnedDanhMucLoaiVanBan, getPersistedDanhMucLoaiVanBan(returnedDanhMucLoaiVanBan));

        insertedDanhMucLoaiVanBan = returnedDanhMucLoaiVanBan;
    }

    @Test
    @Transactional
    void createDanhMucLoaiVanBanWithExistingId() throws Exception {
        // Create the DanhMucLoaiVanBan with an existing ID
        danhMucLoaiVanBan.setId(1L);
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucLoaiVanBanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiVanBanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiVanBan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiVanBans() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiVanBan = danhMucLoaiVanBanRepository.saveAndFlush(danhMucLoaiVanBan);

        // Get all the danhMucLoaiVanBanList
        restDanhMucLoaiVanBanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucLoaiVanBan.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLoaiVb").value(hasItem(DEFAULT_ID_LOAI_VB.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].idLoaiHopDong").value(hasItem(DEFAULT_ID_LOAI_HOP_DONG.intValue())));
    }

    @Test
    @Transactional
    void getDanhMucLoaiVanBan() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiVanBan = danhMucLoaiVanBanRepository.saveAndFlush(danhMucLoaiVanBan);

        // Get the danhMucLoaiVanBan
        restDanhMucLoaiVanBanMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucLoaiVanBan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucLoaiVanBan.getId().intValue()))
            .andExpect(jsonPath("$.idLoaiVb").value(DEFAULT_ID_LOAI_VB.intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.idLoaiHopDong").value(DEFAULT_ID_LOAI_HOP_DONG.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucLoaiVanBan() throws Exception {
        // Get the danhMucLoaiVanBan
        restDanhMucLoaiVanBanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucLoaiVanBan() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiVanBan = danhMucLoaiVanBanRepository.saveAndFlush(danhMucLoaiVanBan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiVanBan
        DanhMucLoaiVanBan updatedDanhMucLoaiVanBan = danhMucLoaiVanBanRepository.findById(danhMucLoaiVanBan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucLoaiVanBan are not directly saved in db
        em.detach(updatedDanhMucLoaiVanBan);
        updatedDanhMucLoaiVanBan.idLoaiVb(UPDATED_ID_LOAI_VB).dienGiai(UPDATED_DIEN_GIAI).idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG);
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(updatedDanhMucLoaiVanBan);

        restDanhMucLoaiVanBanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiVanBanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiVanBanDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiVanBan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucLoaiVanBanToMatchAllProperties(updatedDanhMucLoaiVanBan);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucLoaiVanBan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiVanBan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiVanBan
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiVanBanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiVanBanDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiVanBanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiVanBan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucLoaiVanBan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiVanBan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiVanBan
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiVanBanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiVanBanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiVanBan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucLoaiVanBan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiVanBan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiVanBan
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiVanBanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiVanBanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiVanBan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucLoaiVanBanWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiVanBan = danhMucLoaiVanBanRepository.saveAndFlush(danhMucLoaiVanBan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiVanBan using partial update
        DanhMucLoaiVanBan partialUpdatedDanhMucLoaiVanBan = new DanhMucLoaiVanBan();
        partialUpdatedDanhMucLoaiVanBan.setId(danhMucLoaiVanBan.getId());

        restDanhMucLoaiVanBanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiVanBan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiVanBan))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiVanBan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiVanBanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucLoaiVanBan, danhMucLoaiVanBan),
            getPersistedDanhMucLoaiVanBan(danhMucLoaiVanBan)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucLoaiVanBanWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiVanBan = danhMucLoaiVanBanRepository.saveAndFlush(danhMucLoaiVanBan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiVanBan using partial update
        DanhMucLoaiVanBan partialUpdatedDanhMucLoaiVanBan = new DanhMucLoaiVanBan();
        partialUpdatedDanhMucLoaiVanBan.setId(danhMucLoaiVanBan.getId());

        partialUpdatedDanhMucLoaiVanBan.idLoaiVb(UPDATED_ID_LOAI_VB).dienGiai(UPDATED_DIEN_GIAI).idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG);

        restDanhMucLoaiVanBanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiVanBan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiVanBan))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiVanBan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiVanBanUpdatableFieldsEquals(
            partialUpdatedDanhMucLoaiVanBan,
            getPersistedDanhMucLoaiVanBan(partialUpdatedDanhMucLoaiVanBan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucLoaiVanBan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiVanBan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiVanBan
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiVanBanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucLoaiVanBanDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiVanBanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiVanBan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucLoaiVanBan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiVanBan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiVanBan
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiVanBanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiVanBanDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiVanBan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucLoaiVanBan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiVanBan.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiVanBan
        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiVanBanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucLoaiVanBanDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiVanBan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucLoaiVanBan() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiVanBan = danhMucLoaiVanBanRepository.saveAndFlush(danhMucLoaiVanBan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucLoaiVanBan
        restDanhMucLoaiVanBanMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucLoaiVanBan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucLoaiVanBanRepository.count();
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

    protected DanhMucLoaiVanBan getPersistedDanhMucLoaiVanBan(DanhMucLoaiVanBan danhMucLoaiVanBan) {
        return danhMucLoaiVanBanRepository.findById(danhMucLoaiVanBan.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucLoaiVanBanToMatchAllProperties(DanhMucLoaiVanBan expectedDanhMucLoaiVanBan) {
        assertDanhMucLoaiVanBanAllPropertiesEquals(expectedDanhMucLoaiVanBan, getPersistedDanhMucLoaiVanBan(expectedDanhMucLoaiVanBan));
    }

    protected void assertPersistedDanhMucLoaiVanBanToMatchUpdatableProperties(DanhMucLoaiVanBan expectedDanhMucLoaiVanBan) {
        assertDanhMucLoaiVanBanAllUpdatablePropertiesEquals(
            expectedDanhMucLoaiVanBan,
            getPersistedDanhMucLoaiVanBan(expectedDanhMucLoaiVanBan)
        );
    }
}
