package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.SoCongChung;
import vn.vnpt.repository.SoCongChungRepository;
import vn.vnpt.service.dto.SoCongChungDTO;
import vn.vnpt.service.mapper.SoCongChungMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.SoCongChung}.
 */
@Service
@Transactional
public class SoCongChungService {

    private static final Logger log = LoggerFactory.getLogger(SoCongChungService.class);

    private final SoCongChungRepository soCongChungRepository;

    private final SoCongChungMapper soCongChungMapper;

    public SoCongChungService(SoCongChungRepository soCongChungRepository, SoCongChungMapper soCongChungMapper) {
        this.soCongChungRepository = soCongChungRepository;
        this.soCongChungMapper = soCongChungMapper;
    }

    /**
     * Save a soCongChung.
     *
     * @param soCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    public SoCongChungDTO save(SoCongChungDTO soCongChungDTO) {
        log.debug("Request to save SoCongChung : {}", soCongChungDTO);
        SoCongChung soCongChung = soCongChungMapper.toEntity(soCongChungDTO);
        soCongChung = soCongChungRepository.save(soCongChung);
        return soCongChungMapper.toDto(soCongChung);
    }

    /**
     * Update a soCongChung.
     *
     * @param soCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    public SoCongChungDTO update(SoCongChungDTO soCongChungDTO) {
        log.debug("Request to update SoCongChung : {}", soCongChungDTO);
        SoCongChung soCongChung = soCongChungMapper.toEntity(soCongChungDTO);
        soCongChung = soCongChungRepository.save(soCongChung);
        return soCongChungMapper.toDto(soCongChung);
    }

    /**
     * Partially update a soCongChung.
     *
     * @param soCongChungDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SoCongChungDTO> partialUpdate(SoCongChungDTO soCongChungDTO) {
        log.debug("Request to partially update SoCongChung : {}", soCongChungDTO);

        return soCongChungRepository
            .findById(soCongChungDTO.getId())
            .map(existingSoCongChung -> {
                soCongChungMapper.partialUpdate(existingSoCongChung, soCongChungDTO);

                return existingSoCongChung;
            })
            .map(soCongChungRepository::save)
            .map(soCongChungMapper::toDto);
    }

    /**
     * Get all the soCongChungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SoCongChungDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SoCongChungs");
        return soCongChungRepository.findAll(pageable).map(soCongChungMapper::toDto);
    }

    /**
     * Get one soCongChung by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SoCongChungDTO> findOne(Long id) {
        log.debug("Request to get SoCongChung : {}", id);
        return soCongChungRepository.findById(id).map(soCongChungMapper::toDto);
    }

    /**
     * Delete the soCongChung by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SoCongChung : {}", id);
        soCongChungRepository.deleteById(id);
    }
}
