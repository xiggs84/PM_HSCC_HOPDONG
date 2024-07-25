package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucLoaiHopDong;
import vn.vnpt.repository.DanhMucLoaiHopDongRepository;
import vn.vnpt.service.dto.DanhMucLoaiHopDongDTO;
import vn.vnpt.service.mapper.DanhMucLoaiHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiHopDong}.
 */
@Service
@Transactional
public class DanhMucLoaiHopDongService {

    private static final Logger log = LoggerFactory.getLogger(DanhMucLoaiHopDongService.class);

    private final DanhMucLoaiHopDongRepository danhMucLoaiHopDongRepository;

    private final DanhMucLoaiHopDongMapper danhMucLoaiHopDongMapper;

    public DanhMucLoaiHopDongService(
        DanhMucLoaiHopDongRepository danhMucLoaiHopDongRepository,
        DanhMucLoaiHopDongMapper danhMucLoaiHopDongMapper
    ) {
        this.danhMucLoaiHopDongRepository = danhMucLoaiHopDongRepository;
        this.danhMucLoaiHopDongMapper = danhMucLoaiHopDongMapper;
    }

    /**
     * Save a danhMucLoaiHopDong.
     *
     * @param danhMucLoaiHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucLoaiHopDongDTO save(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO) {
        log.debug("Request to save DanhMucLoaiHopDong : {}", danhMucLoaiHopDongDTO);
        DanhMucLoaiHopDong danhMucLoaiHopDong = danhMucLoaiHopDongMapper.toEntity(danhMucLoaiHopDongDTO);
        danhMucLoaiHopDong = danhMucLoaiHopDongRepository.save(danhMucLoaiHopDong);
        return danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);
    }

    /**
     * Update a danhMucLoaiHopDong.
     *
     * @param danhMucLoaiHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucLoaiHopDongDTO update(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO) {
        log.debug("Request to update DanhMucLoaiHopDong : {}", danhMucLoaiHopDongDTO);
        DanhMucLoaiHopDong danhMucLoaiHopDong = danhMucLoaiHopDongMapper.toEntity(danhMucLoaiHopDongDTO);
        danhMucLoaiHopDong = danhMucLoaiHopDongRepository.save(danhMucLoaiHopDong);
        return danhMucLoaiHopDongMapper.toDto(danhMucLoaiHopDong);
    }

    /**
     * Partially update a danhMucLoaiHopDong.
     *
     * @param danhMucLoaiHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucLoaiHopDongDTO> partialUpdate(DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO) {
        log.debug("Request to partially update DanhMucLoaiHopDong : {}", danhMucLoaiHopDongDTO);

        return danhMucLoaiHopDongRepository
            .findById(danhMucLoaiHopDongDTO.getId())
            .map(existingDanhMucLoaiHopDong -> {
                danhMucLoaiHopDongMapper.partialUpdate(existingDanhMucLoaiHopDong, danhMucLoaiHopDongDTO);

                return existingDanhMucLoaiHopDong;
            })
            .map(danhMucLoaiHopDongRepository::save)
            .map(danhMucLoaiHopDongMapper::toDto);
    }

    /**
     * Get all the danhMucLoaiHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucLoaiHopDongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhMucLoaiHopDongs");
        return danhMucLoaiHopDongRepository.findAll(pageable).map(danhMucLoaiHopDongMapper::toDto);
    }

    /**
     * Get one danhMucLoaiHopDong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucLoaiHopDongDTO> findOne(Long id) {
        log.debug("Request to get DanhMucLoaiHopDong : {}", id);
        return danhMucLoaiHopDongRepository.findById(id).map(danhMucLoaiHopDongMapper::toDto);
    }

    /**
     * Delete the danhMucLoaiHopDong by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DanhMucLoaiHopDong : {}", id);
        danhMucLoaiHopDongRepository.deleteById(id);
    }
}
