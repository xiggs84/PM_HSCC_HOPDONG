package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.CauHinhHopDongAsserts.*;
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
import vn.vnpt.domain.CauHinhHopDong;
import vn.vnpt.repository.CauHinhHopDongRepository;
import vn.vnpt.service.dto.CauHinhHopDongDTO;
import vn.vnpt.service.mapper.CauHinhHopDongMapper;

/**
 * Integration tests for the {@link CauHinhHopDongResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CauHinhHopDongResourceIT {

    private static final Long DEFAULT_ID_LOAI_HOP_DONG = 1L;
    private static final Long UPDATED_ID_LOAI_HOP_DONG = 2L;

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final Long DEFAULT_CHIEU_DAI = 1L;
    private static final Long UPDATED_CHIEU_DAI = 2L;

    private static final String DEFAULT_TIEN_TO = "AAAAAAAAAA";
    private static final String UPDATED_TIEN_TO = "BBBBBBBBBB";

    private static final String DEFAULT_PHUONG_THUC_THANH_TOAN = "AAAAAAAAAA";
    private static final String UPDATED_PHUONG_THUC_THANH_TOAN = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_THEM = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_THEM = "BBBBBBBBBB";

    private static final Long DEFAULT_GIA_TRI = 1L;
    private static final Long UPDATED_GIA_TRI = 2L;

    private static final String DEFAULT_HIEN_THI = "AAAAAAAAAA";
    private static final String UPDATED_HIEN_THI = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final String ENTITY_API_URL = "/api/cau-hinh-hop-dongs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CauHinhHopDongRepository cauHinhHopDongRepository;

    @Autowired
    private CauHinhHopDongMapper cauHinhHopDongMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCauHinhHopDongMockMvc;

    private CauHinhHopDong cauHinhHopDong;

    private CauHinhHopDong insertedCauHinhHopDong;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhHopDong createEntity(EntityManager em) {
        CauHinhHopDong cauHinhHopDong = new CauHinhHopDong()
            .idLoaiHopDong(DEFAULT_ID_LOAI_HOP_DONG)
            .idDonVi(DEFAULT_ID_DON_VI)
            .chieuDai(DEFAULT_CHIEU_DAI)
            .tienTo(DEFAULT_TIEN_TO)
            .phuongThucThanhToan(DEFAULT_PHUONG_THUC_THANH_TOAN)
            .thongTinThem(DEFAULT_THONG_TIN_THEM)
            .giaTri(DEFAULT_GIA_TRI)
            .hienThi(DEFAULT_HIEN_THI)
            .trangThai(DEFAULT_TRANG_THAI);
        return cauHinhHopDong;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhHopDong createUpdatedEntity(EntityManager em) {
        CauHinhHopDong cauHinhHopDong = new CauHinhHopDong()
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idDonVi(UPDATED_ID_DON_VI)
            .chieuDai(UPDATED_CHIEU_DAI)
            .tienTo(UPDATED_TIEN_TO)
            .phuongThucThanhToan(UPDATED_PHUONG_THUC_THANH_TOAN)
            .thongTinThem(UPDATED_THONG_TIN_THEM)
            .giaTri(UPDATED_GIA_TRI)
            .hienThi(UPDATED_HIEN_THI)
            .trangThai(UPDATED_TRANG_THAI);
        return cauHinhHopDong;
    }

    @BeforeEach
    public void initTest() {
        cauHinhHopDong = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCauHinhHopDong != null) {
            cauHinhHopDongRepository.delete(insertedCauHinhHopDong);
            insertedCauHinhHopDong = null;
        }
    }

    @Test
    @Transactional
    void createCauHinhHopDong() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CauHinhHopDong
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(cauHinhHopDong);
        var returnedCauHinhHopDongDTO = om.readValue(
            restCauHinhHopDongMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhHopDongDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CauHinhHopDongDTO.class
        );

        // Validate the CauHinhHopDong in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCauHinhHopDong = cauHinhHopDongMapper.toEntity(returnedCauHinhHopDongDTO);
        assertCauHinhHopDongUpdatableFieldsEquals(returnedCauHinhHopDong, getPersistedCauHinhHopDong(returnedCauHinhHopDong));

        insertedCauHinhHopDong = returnedCauHinhHopDong;
    }

    @Test
    @Transactional
    void createCauHinhHopDongWithExistingId() throws Exception {
        // Create the CauHinhHopDong with an existing ID
        cauHinhHopDong.setId(1L);
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(cauHinhHopDong);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCauHinhHopDongMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhHopDongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CauHinhHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCauHinhHopDongs() throws Exception {
        // Initialize the database
        insertedCauHinhHopDong = cauHinhHopDongRepository.saveAndFlush(cauHinhHopDong);

        // Get all the cauHinhHopDongList
        restCauHinhHopDongMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cauHinhHopDong.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLoaiHopDong").value(hasItem(DEFAULT_ID_LOAI_HOP_DONG.intValue())))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].chieuDai").value(hasItem(DEFAULT_CHIEU_DAI.intValue())))
            .andExpect(jsonPath("$.[*].tienTo").value(hasItem(DEFAULT_TIEN_TO)))
            .andExpect(jsonPath("$.[*].phuongThucThanhToan").value(hasItem(DEFAULT_PHUONG_THUC_THANH_TOAN)))
            .andExpect(jsonPath("$.[*].thongTinThem").value(hasItem(DEFAULT_THONG_TIN_THEM)))
            .andExpect(jsonPath("$.[*].giaTri").value(hasItem(DEFAULT_GIA_TRI.intValue())))
            .andExpect(jsonPath("$.[*].hienThi").value(hasItem(DEFAULT_HIEN_THI)))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())));
    }

    @Test
    @Transactional
    void getCauHinhHopDong() throws Exception {
        // Initialize the database
        insertedCauHinhHopDong = cauHinhHopDongRepository.saveAndFlush(cauHinhHopDong);

        // Get the cauHinhHopDong
        restCauHinhHopDongMockMvc
            .perform(get(ENTITY_API_URL_ID, cauHinhHopDong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cauHinhHopDong.getId().intValue()))
            .andExpect(jsonPath("$.idLoaiHopDong").value(DEFAULT_ID_LOAI_HOP_DONG.intValue()))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.chieuDai").value(DEFAULT_CHIEU_DAI.intValue()))
            .andExpect(jsonPath("$.tienTo").value(DEFAULT_TIEN_TO))
            .andExpect(jsonPath("$.phuongThucThanhToan").value(DEFAULT_PHUONG_THUC_THANH_TOAN))
            .andExpect(jsonPath("$.thongTinThem").value(DEFAULT_THONG_TIN_THEM))
            .andExpect(jsonPath("$.giaTri").value(DEFAULT_GIA_TRI.intValue()))
            .andExpect(jsonPath("$.hienThi").value(DEFAULT_HIEN_THI))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCauHinhHopDong() throws Exception {
        // Get the cauHinhHopDong
        restCauHinhHopDongMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCauHinhHopDong() throws Exception {
        // Initialize the database
        insertedCauHinhHopDong = cauHinhHopDongRepository.saveAndFlush(cauHinhHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhHopDong
        CauHinhHopDong updatedCauHinhHopDong = cauHinhHopDongRepository.findById(cauHinhHopDong.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCauHinhHopDong are not directly saved in db
        em.detach(updatedCauHinhHopDong);
        updatedCauHinhHopDong
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idDonVi(UPDATED_ID_DON_VI)
            .chieuDai(UPDATED_CHIEU_DAI)
            .tienTo(UPDATED_TIEN_TO)
            .phuongThucThanhToan(UPDATED_PHUONG_THUC_THANH_TOAN)
            .thongTinThem(UPDATED_THONG_TIN_THEM)
            .giaTri(UPDATED_GIA_TRI)
            .hienThi(UPDATED_HIEN_THI)
            .trangThai(UPDATED_TRANG_THAI);
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(updatedCauHinhHopDong);

        restCauHinhHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cauHinhHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhHopDongDTO))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCauHinhHopDongToMatchAllProperties(updatedCauHinhHopDong);
    }

    @Test
    @Transactional
    void putNonExistingCauHinhHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhHopDong
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(cauHinhHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHinhHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cauHinhHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCauHinhHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhHopDong
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(cauHinhHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCauHinhHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhHopDong
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(cauHinhHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhHopDongMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CauHinhHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCauHinhHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedCauHinhHopDong = cauHinhHopDongRepository.saveAndFlush(cauHinhHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhHopDong using partial update
        CauHinhHopDong partialUpdatedCauHinhHopDong = new CauHinhHopDong();
        partialUpdatedCauHinhHopDong.setId(cauHinhHopDong.getId());

        partialUpdatedCauHinhHopDong
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idDonVi(UPDATED_ID_DON_VI)
            .chieuDai(UPDATED_CHIEU_DAI)
            .trangThai(UPDATED_TRANG_THAI);

        restCauHinhHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCauHinhHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCauHinhHopDong))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCauHinhHopDongUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCauHinhHopDong, cauHinhHopDong),
            getPersistedCauHinhHopDong(cauHinhHopDong)
        );
    }

    @Test
    @Transactional
    void fullUpdateCauHinhHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedCauHinhHopDong = cauHinhHopDongRepository.saveAndFlush(cauHinhHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhHopDong using partial update
        CauHinhHopDong partialUpdatedCauHinhHopDong = new CauHinhHopDong();
        partialUpdatedCauHinhHopDong.setId(cauHinhHopDong.getId());

        partialUpdatedCauHinhHopDong
            .idLoaiHopDong(UPDATED_ID_LOAI_HOP_DONG)
            .idDonVi(UPDATED_ID_DON_VI)
            .chieuDai(UPDATED_CHIEU_DAI)
            .tienTo(UPDATED_TIEN_TO)
            .phuongThucThanhToan(UPDATED_PHUONG_THUC_THANH_TOAN)
            .thongTinThem(UPDATED_THONG_TIN_THEM)
            .giaTri(UPDATED_GIA_TRI)
            .hienThi(UPDATED_HIEN_THI)
            .trangThai(UPDATED_TRANG_THAI);

        restCauHinhHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCauHinhHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCauHinhHopDong))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCauHinhHopDongUpdatableFieldsEquals(partialUpdatedCauHinhHopDong, getPersistedCauHinhHopDong(partialUpdatedCauHinhHopDong));
    }

    @Test
    @Transactional
    void patchNonExistingCauHinhHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhHopDong
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(cauHinhHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHinhHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cauHinhHopDongDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCauHinhHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhHopDong
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(cauHinhHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCauHinhHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhHopDong
        CauHinhHopDongDTO cauHinhHopDongDTO = cauHinhHopDongMapper.toDto(cauHinhHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhHopDongMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cauHinhHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CauHinhHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCauHinhHopDong() throws Exception {
        // Initialize the database
        insertedCauHinhHopDong = cauHinhHopDongRepository.saveAndFlush(cauHinhHopDong);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cauHinhHopDong
        restCauHinhHopDongMockMvc
            .perform(delete(ENTITY_API_URL_ID, cauHinhHopDong.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cauHinhHopDongRepository.count();
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

    protected CauHinhHopDong getPersistedCauHinhHopDong(CauHinhHopDong cauHinhHopDong) {
        return cauHinhHopDongRepository.findById(cauHinhHopDong.getId()).orElseThrow();
    }

    protected void assertPersistedCauHinhHopDongToMatchAllProperties(CauHinhHopDong expectedCauHinhHopDong) {
        assertCauHinhHopDongAllPropertiesEquals(expectedCauHinhHopDong, getPersistedCauHinhHopDong(expectedCauHinhHopDong));
    }

    protected void assertPersistedCauHinhHopDongToMatchUpdatableProperties(CauHinhHopDong expectedCauHinhHopDong) {
        assertCauHinhHopDongAllUpdatablePropertiesEquals(expectedCauHinhHopDong, getPersistedCauHinhHopDong(expectedCauHinhHopDong));
    }
}
