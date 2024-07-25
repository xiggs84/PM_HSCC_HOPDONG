package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.PhanLoaiHopDong;
import vn.vnpt.repository.PhanLoaiHopDongRepository;
import vn.vnpt.service.dto.PhanLoaiHopDongDTO;
import vn.vnpt.service.mapper.PhanLoaiHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.PhanLoaiHopDong}.
 */
@Service
@Transactional
public class PhanLoaiHopDongService {

    private static final Logger log = LoggerFactory.getLogger(PhanLoaiHopDongService.class);

    private final PhanLoaiHopDongRepository phanLoaiHopDongRepository;

    private final PhanLoaiHopDongMapper phanLoaiHopDongMapper;

    public PhanLoaiHopDongService(PhanLoaiHopDongRepository phanLoaiHopDongRepository, PhanLoaiHopDongMapper phanLoaiHopDongMapper) {
        this.phanLoaiHopDongRepository = phanLoaiHopDongRepository;
        this.phanLoaiHopDongMapper = phanLoaiHopDongMapper;
    }

    /**
     * Save a phanLoaiHopDong.
     *
     * @param phanLoaiHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public PhanLoaiHopDongDTO save(PhanLoaiHopDongDTO phanLoaiHopDongDTO) {
        log.debug("Request to save PhanLoaiHopDong : {}", phanLoaiHopDongDTO);
        PhanLoaiHopDong phanLoaiHopDong = phanLoaiHopDongMapper.toEntity(phanLoaiHopDongDTO);
        phanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);
        return phanLoaiHopDongMapper.toDto(phanLoaiHopDong);
    }

    /**
     * Update a phanLoaiHopDong.
     *
     * @param phanLoaiHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public PhanLoaiHopDongDTO update(PhanLoaiHopDongDTO phanLoaiHopDongDTO) {
        log.debug("Request to update PhanLoaiHopDong : {}", phanLoaiHopDongDTO);
        PhanLoaiHopDong phanLoaiHopDong = phanLoaiHopDongMapper.toEntity(phanLoaiHopDongDTO);
        phanLoaiHopDong = phanLoaiHopDongRepository.save(phanLoaiHopDong);
        return phanLoaiHopDongMapper.toDto(phanLoaiHopDong);
    }

    /**
     * Partially update a phanLoaiHopDong.
     *
     * @param phanLoaiHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PhanLoaiHopDongDTO> partialUpdate(PhanLoaiHopDongDTO phanLoaiHopDongDTO) {
        log.debug("Request to partially update PhanLoaiHopDong : {}", phanLoaiHopDongDTO);

        return phanLoaiHopDongRepository
            .findById(phanLoaiHopDongDTO.getId())
            .map(existingPhanLoaiHopDong -> {
                phanLoaiHopDongMapper.partialUpdate(existingPhanLoaiHopDong, phanLoaiHopDongDTO);

                return existingPhanLoaiHopDong;
            })
            .map(phanLoaiHopDongRepository::save)
            .map(phanLoaiHopDongMapper::toDto);
    }

    /**
     * Get all the phanLoaiHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PhanLoaiHopDongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PhanLoaiHopDongs");
        return phanLoaiHopDongRepository.findAll(pageable).map(phanLoaiHopDongMapper::toDto);
    }

    /**
     * Get one phanLoaiHopDong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PhanLoaiHopDongDTO> findOne(Long id) {
        log.debug("Request to get PhanLoaiHopDong : {}", id);
        return phanLoaiHopDongRepository.findById(id).map(phanLoaiHopDongMapper::toDto);
    }

    /**
     * Delete the phanLoaiHopDong by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PhanLoaiHopDong : {}", id);
        phanLoaiHopDongRepository.deleteById(id);
    }
}
