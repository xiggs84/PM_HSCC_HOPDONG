package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.CauHinhMauHopDongAsserts.*;
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
import vn.vnpt.domain.CauHinhMauHopDong;
import vn.vnpt.repository.CauHinhMauHopDongRepository;
import vn.vnpt.service.dto.CauHinhMauHopDongDTO;
import vn.vnpt.service.mapper.CauHinhMauHopDongMapper;

/**
 * Integration tests for the {@link CauHinhMauHopDongResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CauHinhMauHopDongResourceIT {

    private static final Long DEFAULT_ID_LOAI_HD = 1L;
    private static final Long UPDATED_ID_LOAI_HD = 2L;

    private static final String DEFAULT_DIEN_GIAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_GIAI = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_VAI_TRO_1 = 1L;
    private static final Long UPDATED_ID_VAI_TRO_1 = 2L;

    private static final Long DEFAULT_ID_VAI_TRO_2 = 1L;
    private static final Long UPDATED_ID_VAI_TRO_2 = 2L;

    private static final String DEFAULT_FILE_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_FILE_HOP_DONG = "BBBBBBBBBB";

    private static final String DEFAULT_SRC_HOP_DONG = "AAAAAAAAAA";
    private static final String UPDATED_SRC_HOP_DONG = "BBBBBBBBBB";

    private static final String DEFAULT_DIEU_KHOAN = "AAAAAAAAAA";
    private static final String UPDATED_DIEU_KHOAN = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_DON_VI = 1L;
    private static final Long UPDATED_ID_DON_VI = 2L;

    private static final Long DEFAULT_TRANG_THAI = 1L;
    private static final Long UPDATED_TRANG_THAI = 2L;

    private static final LocalDate DEFAULT_NGAY_THAO_TAC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NGAY_THAO_TAC = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_NGUOI_THAO_TAC = 1L;
    private static final Long UPDATED_NGUOI_THAO_TAC = 2L;

    private static final String DEFAULT_SRC_LOI_CHUNG = "AAAAAAAAAA";
    private static final String UPDATED_SRC_LOI_CHUNG = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_NHOM = 1L;
    private static final Long UPDATED_ID_NHOM = 2L;

    private static final String DEFAULT_FILE_LOI_CHUNG = "AAAAAAAAAA";
    private static final String UPDATED_FILE_LOI_CHUNG = "BBBBBBBBBB";

    private static final Long DEFAULT_CHUYEN_TAI_SAN = 1L;
    private static final Long UPDATED_CHUYEN_TAI_SAN = 2L;

    private static final Long DEFAULT_LOAI_SUA_DOI = 1L;
    private static final Long UPDATED_LOAI_SUA_DOI = 2L;

    private static final Long DEFAULT_LOAI_HUY_BO = 1L;
    private static final Long UPDATED_LOAI_HUY_BO = 2L;

    private static final Long DEFAULT_TRANG_THAI_DUYET = 1L;
    private static final Long UPDATED_TRANG_THAI_DUYET = 2L;

    private static final Long DEFAULT_ID_PHAN_LOAI_HOP_DONG = 1L;
    private static final Long UPDATED_ID_PHAN_LOAI_HOP_DONG = 2L;

    private static final String DEFAULT_SRC_CV = "AAAAAAAAAA";
    private static final String UPDATED_SRC_CV = "BBBBBBBBBB";

    private static final String DEFAULT_SRC_TB = "AAAAAAAAAA";
    private static final String UPDATED_SRC_TB = "BBBBBBBBBB";

    private static final String DEFAULT_SRC_TTPC = "AAAAAAAAAA";
    private static final String UPDATED_SRC_TTPC = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_VAI_TRO_3 = 1L;
    private static final Long UPDATED_ID_VAI_TRO_3 = 2L;

    private static final String ENTITY_API_URL = "/api/cau-hinh-mau-hop-dongs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CauHinhMauHopDongRepository cauHinhMauHopDongRepository;

    @Autowired
    private CauHinhMauHopDongMapper cauHinhMauHopDongMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCauHinhMauHopDongMockMvc;

    private CauHinhMauHopDong cauHinhMauHopDong;

    private CauHinhMauHopDong insertedCauHinhMauHopDong;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhMauHopDong createEntity(EntityManager em) {
        CauHinhMauHopDong cauHinhMauHopDong = new CauHinhMauHopDong()
            .idLoaiHd(DEFAULT_ID_LOAI_HD)
            .dienGiai(DEFAULT_DIEN_GIAI)
            .idVaiTro1(DEFAULT_ID_VAI_TRO_1)
            .idVaiTro2(DEFAULT_ID_VAI_TRO_2)
            .fileHopDong(DEFAULT_FILE_HOP_DONG)
            .srcHopDong(DEFAULT_SRC_HOP_DONG)
            .dieuKhoan(DEFAULT_DIEU_KHOAN)
            .idDonVi(DEFAULT_ID_DON_VI)
            .trangThai(DEFAULT_TRANG_THAI)
            .ngayThaoTac(DEFAULT_NGAY_THAO_TAC)
            .nguoiThaoTac(DEFAULT_NGUOI_THAO_TAC)
            .srcLoiChung(DEFAULT_SRC_LOI_CHUNG)
            .idNhom(DEFAULT_ID_NHOM)
            .fileLoiChung(DEFAULT_FILE_LOI_CHUNG)
            .chuyenTaiSan(DEFAULT_CHUYEN_TAI_SAN)
            .loaiSuaDoi(DEFAULT_LOAI_SUA_DOI)
            .loaiHuyBo(DEFAULT_LOAI_HUY_BO)
            .trangThaiDuyet(DEFAULT_TRANG_THAI_DUYET)
            .idPhanLoaiHopDong(DEFAULT_ID_PHAN_LOAI_HOP_DONG)
            .srcCv(DEFAULT_SRC_CV)
            .srcTb(DEFAULT_SRC_TB)
            .srcTtpc(DEFAULT_SRC_TTPC)
            .idVaiTro3(DEFAULT_ID_VAI_TRO_3);
        return cauHinhMauHopDong;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CauHinhMauHopDong createUpdatedEntity(EntityManager em) {
        CauHinhMauHopDong cauHinhMauHopDong = new CauHinhMauHopDong()
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dienGiai(UPDATED_DIEN_GIAI)
            .idVaiTro1(UPDATED_ID_VAI_TRO_1)
            .idVaiTro2(UPDATED_ID_VAI_TRO_2)
            .fileHopDong(UPDATED_FILE_HOP_DONG)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .dieuKhoan(UPDATED_DIEU_KHOAN)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .srcLoiChung(UPDATED_SRC_LOI_CHUNG)
            .idNhom(UPDATED_ID_NHOM)
            .fileLoiChung(UPDATED_FILE_LOI_CHUNG)
            .chuyenTaiSan(UPDATED_CHUYEN_TAI_SAN)
            .loaiSuaDoi(UPDATED_LOAI_SUA_DOI)
            .loaiHuyBo(UPDATED_LOAI_HUY_BO)
            .trangThaiDuyet(UPDATED_TRANG_THAI_DUYET)
            .idPhanLoaiHopDong(UPDATED_ID_PHAN_LOAI_HOP_DONG)
            .srcCv(UPDATED_SRC_CV)
            .srcTb(UPDATED_SRC_TB)
            .srcTtpc(UPDATED_SRC_TTPC)
            .idVaiTro3(UPDATED_ID_VAI_TRO_3);
        return cauHinhMauHopDong;
    }

    @BeforeEach
    public void initTest() {
        cauHinhMauHopDong = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCauHinhMauHopDong != null) {
            cauHinhMauHopDongRepository.delete(insertedCauHinhMauHopDong);
            insertedCauHinhMauHopDong = null;
        }
    }

    @Test
    @Transactional
    void createCauHinhMauHopDong() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CauHinhMauHopDong
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);
        var returnedCauHinhMauHopDongDTO = om.readValue(
            restCauHinhMauHopDongMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhMauHopDongDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CauHinhMauHopDongDTO.class
        );

        // Validate the CauHinhMauHopDong in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedCauHinhMauHopDong = cauHinhMauHopDongMapper.toEntity(returnedCauHinhMauHopDongDTO);
        assertCauHinhMauHopDongUpdatableFieldsEquals(returnedCauHinhMauHopDong, getPersistedCauHinhMauHopDong(returnedCauHinhMauHopDong));

        insertedCauHinhMauHopDong = returnedCauHinhMauHopDong;
    }

    @Test
    @Transactional
    void createCauHinhMauHopDongWithExistingId() throws Exception {
        // Create the CauHinhMauHopDong with an existing ID
        cauHinhMauHopDong.setId(1L);
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCauHinhMauHopDongMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhMauHopDongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CauHinhMauHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCauHinhMauHopDongs() throws Exception {
        // Initialize the database
        insertedCauHinhMauHopDong = cauHinhMauHopDongRepository.saveAndFlush(cauHinhMauHopDong);

        // Get all the cauHinhMauHopDongList
        restCauHinhMauHopDongMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cauHinhMauHopDong.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLoaiHd").value(hasItem(DEFAULT_ID_LOAI_HD.intValue())))
            .andExpect(jsonPath("$.[*].dienGiai").value(hasItem(DEFAULT_DIEN_GIAI)))
            .andExpect(jsonPath("$.[*].idVaiTro1").value(hasItem(DEFAULT_ID_VAI_TRO_1.intValue())))
            .andExpect(jsonPath("$.[*].idVaiTro2").value(hasItem(DEFAULT_ID_VAI_TRO_2.intValue())))
            .andExpect(jsonPath("$.[*].fileHopDong").value(hasItem(DEFAULT_FILE_HOP_DONG)))
            .andExpect(jsonPath("$.[*].srcHopDong").value(hasItem(DEFAULT_SRC_HOP_DONG)))
            .andExpect(jsonPath("$.[*].dieuKhoan").value(hasItem(DEFAULT_DIEU_KHOAN)))
            .andExpect(jsonPath("$.[*].idDonVi").value(hasItem(DEFAULT_ID_DON_VI.intValue())))
            .andExpect(jsonPath("$.[*].trangThai").value(hasItem(DEFAULT_TRANG_THAI.intValue())))
            .andExpect(jsonPath("$.[*].ngayThaoTac").value(hasItem(DEFAULT_NGAY_THAO_TAC.toString())))
            .andExpect(jsonPath("$.[*].nguoiThaoTac").value(hasItem(DEFAULT_NGUOI_THAO_TAC.intValue())))
            .andExpect(jsonPath("$.[*].srcLoiChung").value(hasItem(DEFAULT_SRC_LOI_CHUNG)))
            .andExpect(jsonPath("$.[*].idNhom").value(hasItem(DEFAULT_ID_NHOM.intValue())))
            .andExpect(jsonPath("$.[*].fileLoiChung").value(hasItem(DEFAULT_FILE_LOI_CHUNG)))
            .andExpect(jsonPath("$.[*].chuyenTaiSan").value(hasItem(DEFAULT_CHUYEN_TAI_SAN.intValue())))
            .andExpect(jsonPath("$.[*].loaiSuaDoi").value(hasItem(DEFAULT_LOAI_SUA_DOI.intValue())))
            .andExpect(jsonPath("$.[*].loaiHuyBo").value(hasItem(DEFAULT_LOAI_HUY_BO.intValue())))
            .andExpect(jsonPath("$.[*].trangThaiDuyet").value(hasItem(DEFAULT_TRANG_THAI_DUYET.intValue())))
            .andExpect(jsonPath("$.[*].idPhanLoaiHopDong").value(hasItem(DEFAULT_ID_PHAN_LOAI_HOP_DONG.intValue())))
            .andExpect(jsonPath("$.[*].srcCv").value(hasItem(DEFAULT_SRC_CV)))
            .andExpect(jsonPath("$.[*].srcTb").value(hasItem(DEFAULT_SRC_TB)))
            .andExpect(jsonPath("$.[*].srcTtpc").value(hasItem(DEFAULT_SRC_TTPC)))
            .andExpect(jsonPath("$.[*].idVaiTro3").value(hasItem(DEFAULT_ID_VAI_TRO_3.intValue())));
    }

    @Test
    @Transactional
    void getCauHinhMauHopDong() throws Exception {
        // Initialize the database
        insertedCauHinhMauHopDong = cauHinhMauHopDongRepository.saveAndFlush(cauHinhMauHopDong);

        // Get the cauHinhMauHopDong
        restCauHinhMauHopDongMockMvc
            .perform(get(ENTITY_API_URL_ID, cauHinhMauHopDong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cauHinhMauHopDong.getId().intValue()))
            .andExpect(jsonPath("$.idLoaiHd").value(DEFAULT_ID_LOAI_HD.intValue()))
            .andExpect(jsonPath("$.dienGiai").value(DEFAULT_DIEN_GIAI))
            .andExpect(jsonPath("$.idVaiTro1").value(DEFAULT_ID_VAI_TRO_1.intValue()))
            .andExpect(jsonPath("$.idVaiTro2").value(DEFAULT_ID_VAI_TRO_2.intValue()))
            .andExpect(jsonPath("$.fileHopDong").value(DEFAULT_FILE_HOP_DONG))
            .andExpect(jsonPath("$.srcHopDong").value(DEFAULT_SRC_HOP_DONG))
            .andExpect(jsonPath("$.dieuKhoan").value(DEFAULT_DIEU_KHOAN))
            .andExpect(jsonPath("$.idDonVi").value(DEFAULT_ID_DON_VI.intValue()))
            .andExpect(jsonPath("$.trangThai").value(DEFAULT_TRANG_THAI.intValue()))
            .andExpect(jsonPath("$.ngayThaoTac").value(DEFAULT_NGAY_THAO_TAC.toString()))
            .andExpect(jsonPath("$.nguoiThaoTac").value(DEFAULT_NGUOI_THAO_TAC.intValue()))
            .andExpect(jsonPath("$.srcLoiChung").value(DEFAULT_SRC_LOI_CHUNG))
            .andExpect(jsonPath("$.idNhom").value(DEFAULT_ID_NHOM.intValue()))
            .andExpect(jsonPath("$.fileLoiChung").value(DEFAULT_FILE_LOI_CHUNG))
            .andExpect(jsonPath("$.chuyenTaiSan").value(DEFAULT_CHUYEN_TAI_SAN.intValue()))
            .andExpect(jsonPath("$.loaiSuaDoi").value(DEFAULT_LOAI_SUA_DOI.intValue()))
            .andExpect(jsonPath("$.loaiHuyBo").value(DEFAULT_LOAI_HUY_BO.intValue()))
            .andExpect(jsonPath("$.trangThaiDuyet").value(DEFAULT_TRANG_THAI_DUYET.intValue()))
            .andExpect(jsonPath("$.idPhanLoaiHopDong").value(DEFAULT_ID_PHAN_LOAI_HOP_DONG.intValue()))
            .andExpect(jsonPath("$.srcCv").value(DEFAULT_SRC_CV))
            .andExpect(jsonPath("$.srcTb").value(DEFAULT_SRC_TB))
            .andExpect(jsonPath("$.srcTtpc").value(DEFAULT_SRC_TTPC))
            .andExpect(jsonPath("$.idVaiTro3").value(DEFAULT_ID_VAI_TRO_3.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCauHinhMauHopDong() throws Exception {
        // Get the cauHinhMauHopDong
        restCauHinhMauHopDongMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCauHinhMauHopDong() throws Exception {
        // Initialize the database
        insertedCauHinhMauHopDong = cauHinhMauHopDongRepository.saveAndFlush(cauHinhMauHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhMauHopDong
        CauHinhMauHopDong updatedCauHinhMauHopDong = cauHinhMauHopDongRepository.findById(cauHinhMauHopDong.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCauHinhMauHopDong are not directly saved in db
        em.detach(updatedCauHinhMauHopDong);
        updatedCauHinhMauHopDong
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dienGiai(UPDATED_DIEN_GIAI)
            .idVaiTro1(UPDATED_ID_VAI_TRO_1)
            .idVaiTro2(UPDATED_ID_VAI_TRO_2)
            .fileHopDong(UPDATED_FILE_HOP_DONG)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .dieuKhoan(UPDATED_DIEU_KHOAN)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .srcLoiChung(UPDATED_SRC_LOI_CHUNG)
            .idNhom(UPDATED_ID_NHOM)
            .fileLoiChung(UPDATED_FILE_LOI_CHUNG)
            .chuyenTaiSan(UPDATED_CHUYEN_TAI_SAN)
            .loaiSuaDoi(UPDATED_LOAI_SUA_DOI)
            .loaiHuyBo(UPDATED_LOAI_HUY_BO)
            .trangThaiDuyet(UPDATED_TRANG_THAI_DUYET)
            .idPhanLoaiHopDong(UPDATED_ID_PHAN_LOAI_HOP_DONG)
            .srcCv(UPDATED_SRC_CV)
            .srcTb(UPDATED_SRC_TB)
            .srcTtpc(UPDATED_SRC_TTPC)
            .idVaiTro3(UPDATED_ID_VAI_TRO_3);
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(updatedCauHinhMauHopDong);

        restCauHinhMauHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cauHinhMauHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhMauHopDongDTO))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhMauHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCauHinhMauHopDongToMatchAllProperties(updatedCauHinhMauHopDong);
    }

    @Test
    @Transactional
    void putNonExistingCauHinhMauHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhMauHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhMauHopDong
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHinhMauHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cauHinhMauHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhMauHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhMauHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCauHinhMauHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhMauHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhMauHopDong
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhMauHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cauHinhMauHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhMauHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCauHinhMauHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhMauHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhMauHopDong
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhMauHopDongMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cauHinhMauHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CauHinhMauHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCauHinhMauHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedCauHinhMauHopDong = cauHinhMauHopDongRepository.saveAndFlush(cauHinhMauHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhMauHopDong using partial update
        CauHinhMauHopDong partialUpdatedCauHinhMauHopDong = new CauHinhMauHopDong();
        partialUpdatedCauHinhMauHopDong.setId(cauHinhMauHopDong.getId());

        partialUpdatedCauHinhMauHopDong
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dienGiai(UPDATED_DIEN_GIAI)
            .idVaiTro1(UPDATED_ID_VAI_TRO_1)
            .fileHopDong(UPDATED_FILE_HOP_DONG)
            .dieuKhoan(UPDATED_DIEU_KHOAN)
            .idNhom(UPDATED_ID_NHOM)
            .fileLoiChung(UPDATED_FILE_LOI_CHUNG)
            .loaiHuyBo(UPDATED_LOAI_HUY_BO)
            .idPhanLoaiHopDong(UPDATED_ID_PHAN_LOAI_HOP_DONG)
            .srcTb(UPDATED_SRC_TB)
            .idVaiTro3(UPDATED_ID_VAI_TRO_3);

        restCauHinhMauHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCauHinhMauHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCauHinhMauHopDong))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhMauHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCauHinhMauHopDongUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCauHinhMauHopDong, cauHinhMauHopDong),
            getPersistedCauHinhMauHopDong(cauHinhMauHopDong)
        );
    }

    @Test
    @Transactional
    void fullUpdateCauHinhMauHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedCauHinhMauHopDong = cauHinhMauHopDongRepository.saveAndFlush(cauHinhMauHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cauHinhMauHopDong using partial update
        CauHinhMauHopDong partialUpdatedCauHinhMauHopDong = new CauHinhMauHopDong();
        partialUpdatedCauHinhMauHopDong.setId(cauHinhMauHopDong.getId());

        partialUpdatedCauHinhMauHopDong
            .idLoaiHd(UPDATED_ID_LOAI_HD)
            .dienGiai(UPDATED_DIEN_GIAI)
            .idVaiTro1(UPDATED_ID_VAI_TRO_1)
            .idVaiTro2(UPDATED_ID_VAI_TRO_2)
            .fileHopDong(UPDATED_FILE_HOP_DONG)
            .srcHopDong(UPDATED_SRC_HOP_DONG)
            .dieuKhoan(UPDATED_DIEU_KHOAN)
            .idDonVi(UPDATED_ID_DON_VI)
            .trangThai(UPDATED_TRANG_THAI)
            .ngayThaoTac(UPDATED_NGAY_THAO_TAC)
            .nguoiThaoTac(UPDATED_NGUOI_THAO_TAC)
            .srcLoiChung(UPDATED_SRC_LOI_CHUNG)
            .idNhom(UPDATED_ID_NHOM)
            .fileLoiChung(UPDATED_FILE_LOI_CHUNG)
            .chuyenTaiSan(UPDATED_CHUYEN_TAI_SAN)
            .loaiSuaDoi(UPDATED_LOAI_SUA_DOI)
            .loaiHuyBo(UPDATED_LOAI_HUY_BO)
            .trangThaiDuyet(UPDATED_TRANG_THAI_DUYET)
            .idPhanLoaiHopDong(UPDATED_ID_PHAN_LOAI_HOP_DONG)
            .srcCv(UPDATED_SRC_CV)
            .srcTb(UPDATED_SRC_TB)
            .srcTtpc(UPDATED_SRC_TTPC)
            .idVaiTro3(UPDATED_ID_VAI_TRO_3);

        restCauHinhMauHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCauHinhMauHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCauHinhMauHopDong))
            )
            .andExpect(status().isOk());

        // Validate the CauHinhMauHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCauHinhMauHopDongUpdatableFieldsEquals(
            partialUpdatedCauHinhMauHopDong,
            getPersistedCauHinhMauHopDong(partialUpdatedCauHinhMauHopDong)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCauHinhMauHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhMauHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhMauHopDong
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCauHinhMauHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cauHinhMauHopDongDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhMauHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhMauHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCauHinhMauHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhMauHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhMauHopDong
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhMauHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cauHinhMauHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CauHinhMauHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCauHinhMauHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cauHinhMauHopDong.setId(longCount.incrementAndGet());

        // Create the CauHinhMauHopDong
        CauHinhMauHopDongDTO cauHinhMauHopDongDTO = cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCauHinhMauHopDongMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cauHinhMauHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CauHinhMauHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCauHinhMauHopDong() throws Exception {
        // Initialize the database
        insertedCauHinhMauHopDong = cauHinhMauHopDongRepository.saveAndFlush(cauHinhMauHopDong);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cauHinhMauHopDong
        restCauHinhMauHopDongMockMvc
            .perform(delete(ENTITY_API_URL_ID, cauHinhMauHopDong.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cauHinhMauHopDongRepository.count();
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

    protected CauHinhMauHopDong getPersistedCauHinhMauHopDong(CauHinhMauHopDong cauHinhMauHopDong) {
        return cauHinhMauHopDongRepository.findById(cauHinhMauHopDong.getId()).orElseThrow();
    }

    protected void assertPersistedCauHinhMauHopDongToMatchAllProperties(CauHinhMauHopDong expectedCauHinhMauHopDong) {
        assertCauHinhMauHopDongAllPropertiesEquals(expectedCauHinhMauHopDong, getPersistedCauHinhMauHopDong(expectedCauHinhMauHopDong));
    }

    protected void assertPersistedCauHinhMauHopDongToMatchUpdatableProperties(CauHinhMauHopDong expectedCauHinhMauHopDong) {
        assertCauHinhMauHopDongAllUpdatablePropertiesEquals(
            expectedCauHinhMauHopDong,
            getPersistedCauHinhMauHopDong(expectedCauHinhMauHopDong)
        );
    }
}
