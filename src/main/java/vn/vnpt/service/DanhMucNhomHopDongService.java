package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucNhomHopDong;
import vn.vnpt.repository.DanhMucNhomHopDongRepository;
import vn.vnpt.service.dto.DanhMucNhomHopDongDTO;
import vn.vnpt.service.mapper.DanhMucNhomHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucNhomHopDong}.
 */
@Service
@Transactional
public class DanhMucNhomHopDongService {

    private static final Logger log = LoggerFactory.getLogger(DanhMucNhomHopDongService.class);

    private final DanhMucNhomHopDongRepository danhMucNhomHopDongRepository;

    private final DanhMucNhomHopDongMapper danhMucNhomHopDongMapper;

    public DanhMucNhomHopDongService(
        DanhMucNhomHopDongRepository danhMucNhomHopDongRepository,
        DanhMucNhomHopDongMapper danhMucNhomHopDongMapper
    ) {
        this.danhMucNhomHopDongRepository = danhMucNhomHopDongRepository;
        this.danhMucNhomHopDongMapper = danhMucNhomHopDongMapper;
    }

    /**
     * Save a danhMucNhomHopDong.
     *
     * @param danhMucNhomHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucNhomHopDongDTO save(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO) {
        log.debug("Request to save DanhMucNhomHopDong : {}", danhMucNhomHopDongDTO);
        DanhMucNhomHopDong danhMucNhomHopDong = danhMucNhomHopDongMapper.toEntity(danhMucNhomHopDongDTO);
        danhMucNhomHopDong = danhMucNhomHopDongRepository.save(danhMucNhomHopDong);
        return danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);
    }

    /**
     * Update a danhMucNhomHopDong.
     *
     * @param danhMucNhomHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucNhomHopDongDTO update(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO) {
        log.debug("Request to update DanhMucNhomHopDong : {}", danhMucNhomHopDongDTO);
        DanhMucNhomHopDong danhMucNhomHopDong = danhMucNhomHopDongMapper.toEntity(danhMucNhomHopDongDTO);
        danhMucNhomHopDong = danhMucNhomHopDongRepository.save(danhMucNhomHopDong);
        return danhMucNhomHopDongMapper.toDto(danhMucNhomHopDong);
    }

    /**
     * Partially update a danhMucNhomHopDong.
     *
     * @param danhMucNhomHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucNhomHopDongDTO> partialUpdate(DanhMucNhomHopDongDTO danhMucNhomHopDongDTO) {
        log.debug("Request to partially update DanhMucNhomHopDong : {}", danhMucNhomHopDongDTO);

        return danhMucNhomHopDongRepository
            .findById(danhMucNhomHopDongDTO.getId())
            .map(existingDanhMucNhomHopDong -> {
                danhMucNhomHopDongMapper.partialUpdate(existingDanhMucNhomHopDong, danhMucNhomHopDongDTO);

                return existingDanhMucNhomHopDong;
            })
            .map(danhMucNhomHopDongRepository::save)
            .map(danhMucNhomHopDongMapper::toDto);
    }

    /**
     * Get all the danhMucNhomHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucNhomHopDongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhMucNhomHopDongs");
        return danhMucNhomHopDongRepository.findAll(pageable).map(danhMucNhomHopDongMapper::toDto);
    }

    /**
     * Get one danhMucNhomHopDong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucNhomHopDongDTO> findOne(Long id) {
        log.debug("Request to get DanhMucNhomHopDong : {}", id);
        return danhMucNhomHopDongRepository.findById(id).map(danhMucNhomHopDongMapper::toDto);
    }

    /**
     * Delete the danhMucNhomHopDong by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DanhMucNhomHopDong : {}", id);
        danhMucNhomHopDongRepository.deleteById(id);
    }
}
