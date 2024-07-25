package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CauHinhHopDong;
import vn.vnpt.repository.CauHinhHopDongRepository;
import vn.vnpt.service.dto.CauHinhHopDongDTO;
import vn.vnpt.service.mapper.CauHinhHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CauHinhHopDong}.
 */
@Service
@Transactional
public class CauHinhHopDongService {

    private static final Logger log = LoggerFactory.getLogger(CauHinhHopDongService.class);

    private final CauHinhHopDongRepository cauHinhHopDongRepository;

    private final CauHinhHopDongMapper cauHinhHopDongMapper;

    public CauHinhHopDongService(CauHinhHopDongRepository cauHinhHopDongRepository, CauHinhHopDongMapper cauHinhHopDongMapper) {
        this.cauHinhHopDongRepository = cauHinhHopDongRepository;
        this.cauHinhHopDongMapper = cauHinhHopDongMapper;
    }

    /**
     * Save a cauHinhHopDong.
     *
     * @param cauHinhHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public CauHinhHopDongDTO save(CauHinhHopDongDTO cauHinhHopDongDTO) {
        log.debug("Request to save CauHinhHopDong : {}", cauHinhHopDongDTO);
        CauHinhHopDong cauHinhHopDong = cauHinhHopDongMapper.toEntity(cauHinhHopDongDTO);
        cauHinhHopDong = cauHinhHopDongRepository.save(cauHinhHopDong);
        return cauHinhHopDongMapper.toDto(cauHinhHopDong);
    }

    /**
     * Update a cauHinhHopDong.
     *
     * @param cauHinhHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public CauHinhHopDongDTO update(CauHinhHopDongDTO cauHinhHopDongDTO) {
        log.debug("Request to update CauHinhHopDong : {}", cauHinhHopDongDTO);
        CauHinhHopDong cauHinhHopDong = cauHinhHopDongMapper.toEntity(cauHinhHopDongDTO);
        cauHinhHopDong = cauHinhHopDongRepository.save(cauHinhHopDong);
        return cauHinhHopDongMapper.toDto(cauHinhHopDong);
    }

    /**
     * Partially update a cauHinhHopDong.
     *
     * @param cauHinhHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CauHinhHopDongDTO> partialUpdate(CauHinhHopDongDTO cauHinhHopDongDTO) {
        log.debug("Request to partially update CauHinhHopDong : {}", cauHinhHopDongDTO);

        return cauHinhHopDongRepository
            .findById(cauHinhHopDongDTO.getId())
            .map(existingCauHinhHopDong -> {
                cauHinhHopDongMapper.partialUpdate(existingCauHinhHopDong, cauHinhHopDongDTO);

                return existingCauHinhHopDong;
            })
            .map(cauHinhHopDongRepository::save)
            .map(cauHinhHopDongMapper::toDto);
    }

    /**
     * Get all the cauHinhHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CauHinhHopDongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CauHinhHopDongs");
        return cauHinhHopDongRepository.findAll(pageable).map(cauHinhHopDongMapper::toDto);
    }

    /**
     * Get one cauHinhHopDong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CauHinhHopDongDTO> findOne(Long id) {
        log.debug("Request to get CauHinhHopDong : {}", id);
        return cauHinhHopDongRepository.findById(id).map(cauHinhHopDongMapper::toDto);
    }

    /**
     * Delete the cauHinhHopDong by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CauHinhHopDong : {}", id);
        cauHinhHopDongRepository.deleteById(id);
    }
}
