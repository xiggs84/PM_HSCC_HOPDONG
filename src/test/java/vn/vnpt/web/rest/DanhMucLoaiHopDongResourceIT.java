package vn.vnpt.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static vn.vnpt.domain.DanhMucLoaiHopDongAsserts.*;
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
import vn.vnpt.domain.DanhMucLoaiHopDong;
import vn.vnpt.repository.DanhMucLoaiHopDongRepository;
import vn.vnpt.service.dto.DanhMucLoaiHopDongDTO;
import vn.vnpt.service.mapper.DanhMucLoaiHopDongMapper;

/**
 * Integration tests for the {@link DanhMucLoaiHopDongResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DanhMucLoaiHopDongResourceIT {

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

    private static final String DEFAULT_DG_TEN = "AAAAAAAAAA";
    private static final String UPDATED_DG_TEN = "BBBBBBBBBB";

    private static final Long DEFAULT_NHOM_TEN = 1L;
    private static final Long UPDATED_NHOM_TEN = 2L;

    private static final Long DEFAULT_ID_VAI_TRO_3 = 1L;
    private static final Long UPDATED_ID_VAI_TRO_3 = 2L;

    private static final String ENTITY_API_URL = "/api/danh-muc-loai-hop-dongs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DanhMucLoaiHopDongRepository danhMucLoaiHopDongRepository;

    @Autowired
    private DanhMucLoaiHopDongMapper danhMucLoaiHopDongMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDanhMucLoaiHopDongMockMvc;

    private DanhMucLoaiHopDong danhMucLoaiHopDong;

    private DanhMucLoaiHopDong insertedDanhMucLoaiHopDong;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiHopDong createEntity(EntityManager em) {
        DanhMucLoaiHopDong danhMucLoaiHopDong = new DanhMucLoaiHopDong()
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
            .dgTen(DEFAULT_DG_TEN)
            .nhomTen(DEFAULT_NHOM_TEN)
            .idVaiTro3(DEFAULT_ID_VAI_TRO_3);
        return danhMucLoaiHopDong;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DanhMucLoaiHopDong createUpdatedEntity(EntityManager em) {
        DanhMucLoaiHopDong danhMucLoaiHopDong = new DanhMucLoaiHopDong()
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
            .dgTen(UPDATED_DG_TEN)
            .nhomTen(UPDATED_NHOM_TEN)
            .idVaiTro3(UPDATED_ID_VAI_TRO_3);
        return danhMucLoaiHopDong;
    }

    @BeforeEach
    public void initTest() {
        danhMucLoaiHopDong = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDanhMucLoaiHopDong != null) {
            danhMucLoaiHopDongRepository.delete(insertedDanhMucLoaiHopDong);
            insertedDanhMucLoaiHopDong = null;
        }
    }

    @Test
    @Transactional
    void createDanhMucLoaiHopDong() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DanhMucLoaiHopDong
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);
        var returnedDanhMucLoaiHopDongDTO = om.readValue(
            restDanhMucLoaiHopDongMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiHopDongDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DanhMucLoaiHopDongDTO.class
        );

        // Validate the DanhMucLoaiHopDong in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedDanhMucLoaiHopDong = danhMucLoaiHopDongMapper.toEntity(returnedDanhMucLoaiHopDongDTO);
        assertDanhMucLoaiHopDongUpdatableFieldsEquals(
            returnedDanhMucLoaiHopDong,
            getPersistedDanhMucLoaiHopDong(returnedDanhMucLoaiHopDong)
        );

        insertedDanhMucLoaiHopDong = returnedDanhMucLoaiHopDong;
    }

    @Test
    @Transactional
    void createDanhMucLoaiHopDongWithExistingId() throws Exception {
        // Create the DanhMucLoaiHopDong with an existing ID
        danhMucLoaiHopDong.setId(1L);
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDanhMucLoaiHopDongMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiHopDongDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDanhMucLoaiHopDongs() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiHopDong = danhMucLoaiHopDongRepository.saveAndFlush(danhMucLoaiHopDong);

        // Get all the danhMucLoaiHopDongList
        restDanhMucLoaiHopDongMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(danhMucLoaiHopDong.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].dgTen").value(hasItem(DEFAULT_DG_TEN)))
            .andExpect(jsonPath("$.[*].nhomTen").value(hasItem(DEFAULT_NHOM_TEN.intValue())))
            .andExpect(jsonPath("$.[*].idVaiTro3").value(hasItem(DEFAULT_ID_VAI_TRO_3.intValue())));
    }

    @Test
    @Transactional
    void getDanhMucLoaiHopDong() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiHopDong = danhMucLoaiHopDongRepository.saveAndFlush(danhMucLoaiHopDong);

        // Get the danhMucLoaiHopDong
        restDanhMucLoaiHopDongMockMvc
            .perform(get(ENTITY_API_URL_ID, danhMucLoaiHopDong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(danhMucLoaiHopDong.getId().intValue()))
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
            .andExpect(jsonPath("$.dgTen").value(DEFAULT_DG_TEN))
            .andExpect(jsonPath("$.nhomTen").value(DEFAULT_NHOM_TEN.intValue()))
            .andExpect(jsonPath("$.idVaiTro3").value(DEFAULT_ID_VAI_TRO_3.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingDanhMucLoaiHopDong() throws Exception {
        // Get the danhMucLoaiHopDong
        restDanhMucLoaiHopDongMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDanhMucLoaiHopDong() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiHopDong = danhMucLoaiHopDongRepository.saveAndFlush(danhMucLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiHopDong
        DanhMucLoaiHopDong updatedDanhMucLoaiHopDong = danhMucLoaiHopDongRepository.findById(danhMucLoaiHopDong.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDanhMucLoaiHopDong are not directly saved in db
        em.detach(updatedDanhMucLoaiHopDong);
        updatedDanhMucLoaiHopDong
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
            .dgTen(UPDATED_DG_TEN)
            .nhomTen(UPDATED_NHOM_TEN)
            .idVaiTro3(UPDATED_ID_VAI_TRO_3);
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(updatedDanhMucLoaiHopDong);

        restDanhMucLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiHopDongDTO))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDanhMucLoaiHopDongToMatchAllProperties(updatedDanhMucLoaiHopDong);
    }

    @Test
    @Transactional
    void putNonExistingDanhMucLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiHopDong
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, danhMucLoaiHopDongDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDanhMucLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiHopDong
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiHopDongMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(danhMucLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDanhMucLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiHopDong
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiHopDongMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(danhMucLoaiHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDanhMucLoaiHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiHopDong = danhMucLoaiHopDongRepository.saveAndFlush(danhMucLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiHopDong using partial update
        DanhMucLoaiHopDong partialUpdatedDanhMucLoaiHopDong = new DanhMucLoaiHopDong();
        partialUpdatedDanhMucLoaiHopDong.setId(danhMucLoaiHopDong.getId());

        partialUpdatedDanhMucLoaiHopDong
            .dienGiai(UPDATED_DIEN_GIAI)
            .idVaiTro1(UPDATED_ID_VAI_TRO_1)
            .idVaiTro2(UPDATED_ID_VAI_TRO_2)
            .idDonVi(UPDATED_ID_DON_VI)
            .idNhom(UPDATED_ID_NHOM)
            .fileLoiChung(UPDATED_FILE_LOI_CHUNG)
            .chuyenTaiSan(UPDATED_CHUYEN_TAI_SAN)
            .dgTen(UPDATED_DG_TEN);

        restDanhMucLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiHopDong))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiHopDongUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDanhMucLoaiHopDong, danhMucLoaiHopDong),
            getPersistedDanhMucLoaiHopDong(danhMucLoaiHopDong)
        );
    }

    @Test
    @Transactional
    void fullUpdateDanhMucLoaiHopDongWithPatch() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiHopDong = danhMucLoaiHopDongRepository.saveAndFlush(danhMucLoaiHopDong);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the danhMucLoaiHopDong using partial update
        DanhMucLoaiHopDong partialUpdatedDanhMucLoaiHopDong = new DanhMucLoaiHopDong();
        partialUpdatedDanhMucLoaiHopDong.setId(danhMucLoaiHopDong.getId());

        partialUpdatedDanhMucLoaiHopDong
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
            .dgTen(UPDATED_DG_TEN)
            .nhomTen(UPDATED_NHOM_TEN)
            .idVaiTro3(UPDATED_ID_VAI_TRO_3);

        restDanhMucLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDanhMucLoaiHopDong.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDanhMucLoaiHopDong))
            )
            .andExpect(status().isOk());

        // Validate the DanhMucLoaiHopDong in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDanhMucLoaiHopDongUpdatableFieldsEquals(
            partialUpdatedDanhMucLoaiHopDong,
            getPersistedDanhMucLoaiHopDong(partialUpdatedDanhMucLoaiHopDong)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDanhMucLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiHopDong
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDanhMucLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, danhMucLoaiHopDongDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDanhMucLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiHopDong
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiHopDongMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(danhMucLoaiHopDongDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DanhMucLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDanhMucLoaiHopDong() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        danhMucLoaiHopDong.setId(longCount.incrementAndGet());

        // Create the DanhMucLoaiHopDong
        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDanhMucLoaiHopDongMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(danhMucLoaiHopDongDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DanhMucLoaiHopDong in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDanhMucLoaiHopDong() throws Exception {
        // Initialize the database
        insertedDanhMucLoaiHopDong = danhMucLoaiHopDongRepository.saveAndFlush(danhMucLoaiHopDong);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the danhMucLoaiHopDong
        restDanhMucLoaiHopDongMockMvc
            .perform(delete(ENTITY_API_URL_ID, danhMucLoaiHopDong.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return danhMucLoaiHopDongRepository.count();
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

    protected DanhMucLoaiHopDong getPersistedDanhMucLoaiHopDong(DanhMucLoaiHopDong danhMucLoaiHopDong) {
        return danhMucLoaiHopDongRepository.findById(danhMucLoaiHopDong.getId()).orElseThrow();
    }

    protected void assertPersistedDanhMucLoaiHopDongToMatchAllProperties(DanhMucLoaiHopDong expectedDanhMucLoaiHopDong) {
        assertDanhMucLoaiHopDongAllPropertiesEquals(expectedDanhMucLoaiHopDong, getPersistedDanhMucLoaiHopDong(expectedDanhMucLoaiHopDong));
    }

    protected void assertPersistedDanhMucLoaiHopDongToMatchUpdatableProperties(DanhMucLoaiHopDong expectedDanhMucLoaiHopDong) {
        assertDanhMucLoaiHopDongAllUpdatablePropertiesEquals(
            expectedDanhMucLoaiHopDong,
            getPersistedDanhMucLoaiHopDong(expectedDanhMucLoaiHopDong)
        );
    }
}
