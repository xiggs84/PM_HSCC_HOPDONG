package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.LoaiHopDongCongChung;
import vn.vnpt.repository.LoaiHopDongCongChungRepository;
import vn.vnpt.service.dto.LoaiHopDongCongChungDTO;
import vn.vnpt.service.mapper.LoaiHopDongCongChungMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.LoaiHopDongCongChung}.
 */
@Service
@Transactional
public class LoaiHopDongCongChungService {

    private static final Logger log = LoggerFactory.getLogger(LoaiHopDongCongChungService.class);

    private final LoaiHopDongCongChungRepository loaiHopDongCongChungRepository;

    private final LoaiHopDongCongChungMapper loaiHopDongCongChungMapper;

    public LoaiHopDongCongChungService(
        LoaiHopDongCongChungRepository loaiHopDongCongChungRepository,
        LoaiHopDongCongChungMapper loaiHopDongCongChungMapper
    ) {
        this.loaiHopDongCongChungRepository = loaiHopDongCongChungRepository;
        this.loaiHopDongCongChungMapper = loaiHopDongCongChungMapper;
    }

    /**
     * Save a loaiHopDongCongChung.
     *
     * @param loaiHopDongCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiHopDongCongChungDTO save(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO) {
        log.debug("Request to save LoaiHopDongCongChung : {}", loaiHopDongCongChungDTO);
        LoaiHopDongCongChung loaiHopDongCongChung = loaiHopDongCongChungMapper.toEntity(loaiHopDongCongChungDTO);
        loaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);
        return loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);
    }

    /**
     * Update a loaiHopDongCongChung.
     *
     * @param loaiHopDongCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    public LoaiHopDongCongChungDTO update(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO) {
        log.debug("Request to update LoaiHopDongCongChung : {}", loaiHopDongCongChungDTO);
        LoaiHopDongCongChung loaiHopDongCongChung = loaiHopDongCongChungMapper.toEntity(loaiHopDongCongChungDTO);
        loaiHopDongCongChung = loaiHopDongCongChungRepository.save(loaiHopDongCongChung);
        return loaiHopDongCongChungMapper.toDto(loaiHopDongCongChung);
    }

    /**
     * Partially update a loaiHopDongCongChung.
     *
     * @param loaiHopDongCongChungDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LoaiHopDongCongChungDTO> partialUpdate(LoaiHopDongCongChungDTO loaiHopDongCongChungDTO) {
        log.debug("Request to partially update LoaiHopDongCongChung : {}", loaiHopDongCongChungDTO);

        return loaiHopDongCongChungRepository
            .findById(loaiHopDongCongChungDTO.getId())
            .map(existingLoaiHopDongCongChung -> {
                loaiHopDongCongChungMapper.partialUpdate(existingLoaiHopDongCongChung, loaiHopDongCongChungDTO);

                return existingLoaiHopDongCongChung;
            })
            .map(loaiHopDongCongChungRepository::save)
            .map(loaiHopDongCongChungMapper::toDto);
    }

    /**
     * Get all the loaiHopDongCongChungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LoaiHopDongCongChungDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LoaiHopDongCongChungs");
        return loaiHopDongCongChungRepository.findAll(pageable).map(loaiHopDongCongChungMapper::toDto);
    }

    /**
     * Get one loaiHopDongCongChung by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LoaiHopDongCongChungDTO> findOne(Long id) {
        log.debug("Request to get LoaiHopDongCongChung : {}", id);
        return loaiHopDongCongChungRepository.findById(id).map(loaiHopDongCongChungMapper::toDto);
    }

    /**
     * Delete the loaiHopDongCongChung by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LoaiHopDongCongChung : {}", id);
        loaiHopDongCongChungRepository.deleteById(id);
    }
}
