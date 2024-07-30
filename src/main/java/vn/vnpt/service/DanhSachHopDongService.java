package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhSachHopDong;
import vn.vnpt.repository.DanhSachHopDongRepository;
import vn.vnpt.service.dto.DanhSachHopDongDTO;
import vn.vnpt.service.mapper.DanhSachHopDongMapper;
import java.time.LocalDate;


/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhSachHopDong}.
 */
@Service
@Transactional
public class DanhSachHopDongService {

    private static final Logger log = LoggerFactory.getLogger(DanhSachHopDongService.class);

    private final DanhSachHopDongRepository danhSachHopDongRepository;

    private final DanhSachHopDongMapper danhSachHopDongMapper;

    public DanhSachHopDongService(DanhSachHopDongRepository danhSachHopDongRepository, DanhSachHopDongMapper danhSachHopDongMapper) {
        this.danhSachHopDongRepository = danhSachHopDongRepository;
        this.danhSachHopDongMapper = danhSachHopDongMapper;
    }

    /**
     * Save a danhSachHopDong.
     *
     * @param danhSachHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhSachHopDongDTO save(DanhSachHopDongDTO danhSachHopDongDTO) {
        log.debug("Request to save DanhSachHopDong : {}", danhSachHopDongDTO);
        DanhSachHopDong danhSachHopDong = danhSachHopDongMapper.toEntity(danhSachHopDongDTO);
        danhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);
        return danhSachHopDongMapper.toDto(danhSachHopDong);
    }

    /**
     * Update a danhSachHopDong.
     *
     * @param danhSachHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhSachHopDongDTO update(DanhSachHopDongDTO danhSachHopDongDTO) {
        log.debug("Request to update DanhSachHopDong : {}", danhSachHopDongDTO);
        DanhSachHopDong danhSachHopDong = danhSachHopDongMapper.toEntity(danhSachHopDongDTO);
        danhSachHopDong = danhSachHopDongRepository.save(danhSachHopDong);
        return danhSachHopDongMapper.toDto(danhSachHopDong);
    }

    /**
     * Partially update a danhSachHopDong.
     *
     * @param danhSachHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhSachHopDongDTO> partialUpdate(DanhSachHopDongDTO danhSachHopDongDTO) {
        log.debug("Request to partially update DanhSachHopDong : {}", danhSachHopDongDTO);

        return danhSachHopDongRepository
            .findById(danhSachHopDongDTO.getId())
            .map(existingDanhSachHopDong -> {
                danhSachHopDongMapper.partialUpdate(existingDanhSachHopDong, danhSachHopDongDTO);

                return existingDanhSachHopDong;
            })
            .map(danhSachHopDongRepository::save)
            .map(danhSachHopDongMapper::toDto);
    }

    /**
     * Get all the danhSachHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhSachHopDongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhSachHopDongs");
        return danhSachHopDongRepository.findAll(pageable).map(danhSachHopDongMapper::toDto);
    }

    /**
     * Get one danhSachHopDong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhSachHopDongDTO> findOne(Long id) {
        log.debug("Request to get DanhSachHopDong : {}", id);
        return danhSachHopDongRepository.findById(id).map(danhSachHopDongMapper::toDto);
    }

    /**
     * Delete the danhSachHopDong by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DanhSachHopDong : {}", id);
        danhSachHopDongRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public long countByIdDonViAndNgayLapHd(Long idDonVi, LocalDate ngayLapHd) {
        log.debug("Request to count DanhSachHopDong by idDonVi : {} and ngayLapHd : {}", idDonVi, ngayLapHd);
        return danhSachHopDongRepository.countByIdDonViAndNgayLapHd(idDonVi, ngayLapHd);
    }

    @Transactional(readOnly = true)
    public long countByIdDonViAndNgayLapHdBetween(Long idDonVi, LocalDate startDate, LocalDate endDate) {
        return danhSachHopDongRepository.countByIdDonViAndNgayLapHdBetween(idDonVi, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public Long countByMonth(int month, int year) {
        return danhSachHopDongRepository.countByMonth(month, year);
    }

    @Transactional(readOnly = true)
    public Long countByMonthAndIdDonVi(int month, int year, Long idDonVi) {
        return danhSachHopDongRepository.countByMonthAndIdDonVi(month, year, idDonVi);
    }
}
