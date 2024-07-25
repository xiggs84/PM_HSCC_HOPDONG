package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.CauHinhMauHopDong;
import vn.vnpt.repository.CauHinhMauHopDongRepository;
import vn.vnpt.service.dto.CauHinhMauHopDongDTO;
import vn.vnpt.service.mapper.CauHinhMauHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.CauHinhMauHopDong}.
 */
@Service
@Transactional
public class CauHinhMauHopDongService {

    private static final Logger log = LoggerFactory.getLogger(CauHinhMauHopDongService.class);

    private final CauHinhMauHopDongRepository cauHinhMauHopDongRepository;

    private final CauHinhMauHopDongMapper cauHinhMauHopDongMapper;

    public CauHinhMauHopDongService(
        CauHinhMauHopDongRepository cauHinhMauHopDongRepository,
        CauHinhMauHopDongMapper cauHinhMauHopDongMapper
    ) {
        this.cauHinhMauHopDongRepository = cauHinhMauHopDongRepository;
        this.cauHinhMauHopDongMapper = cauHinhMauHopDongMapper;
    }

    /**
     * Save a cauHinhMauHopDong.
     *
     * @param cauHinhMauHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public CauHinhMauHopDongDTO save(CauHinhMauHopDongDTO cauHinhMauHopDongDTO) {
        log.debug("Request to save CauHinhMauHopDong : {}", cauHinhMauHopDongDTO);
        CauHinhMauHopDong cauHinhMauHopDong = cauHinhMauHopDongMapper.toEntity(cauHinhMauHopDongDTO);
        cauHinhMauHopDong = cauHinhMauHopDongRepository.save(cauHinhMauHopDong);
        return cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);
    }

    /**
     * Update a cauHinhMauHopDong.
     *
     * @param cauHinhMauHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public CauHinhMauHopDongDTO update(CauHinhMauHopDongDTO cauHinhMauHopDongDTO) {
        log.debug("Request to update CauHinhMauHopDong : {}", cauHinhMauHopDongDTO);
        CauHinhMauHopDong cauHinhMauHopDong = cauHinhMauHopDongMapper.toEntity(cauHinhMauHopDongDTO);
        cauHinhMauHopDong = cauHinhMauHopDongRepository.save(cauHinhMauHopDong);
        return cauHinhMauHopDongMapper.toDto(cauHinhMauHopDong);
    }

    /**
     * Partially update a cauHinhMauHopDong.
     *
     * @param cauHinhMauHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CauHinhMauHopDongDTO> partialUpdate(CauHinhMauHopDongDTO cauHinhMauHopDongDTO) {
        log.debug("Request to partially update CauHinhMauHopDong : {}", cauHinhMauHopDongDTO);

        return cauHinhMauHopDongRepository
            .findById(cauHinhMauHopDongDTO.getId())
            .map(existingCauHinhMauHopDong -> {
                cauHinhMauHopDongMapper.partialUpdate(existingCauHinhMauHopDong, cauHinhMauHopDongDTO);

                return existingCauHinhMauHopDong;
            })
            .map(cauHinhMauHopDongRepository::save)
            .map(cauHinhMauHopDongMapper::toDto);
    }

    /**
     * Get all the cauHinhMauHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CauHinhMauHopDongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CauHinhMauHopDongs");
        return cauHinhMauHopDongRepository.findAll(pageable).map(cauHinhMauHopDongMapper::toDto);
    }

    /**
     * Get one cauHinhMauHopDong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CauHinhMauHopDongDTO> findOne(Long id) {
        log.debug("Request to get CauHinhMauHopDong : {}", id);
        return cauHinhMauHopDongRepository.findById(id).map(cauHinhMauHopDongMapper::toDto);
    }

    /**
     * Delete the cauHinhMauHopDong by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CauHinhMauHopDong : {}", id);
        cauHinhMauHopDongRepository.deleteById(id);
    }
}
