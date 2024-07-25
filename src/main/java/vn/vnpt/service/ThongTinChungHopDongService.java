package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.ThongTinChungHopDong;
import vn.vnpt.repository.ThongTinChungHopDongRepository;
import vn.vnpt.service.dto.ThongTinChungHopDongDTO;
import vn.vnpt.service.mapper.ThongTinChungHopDongMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.ThongTinChungHopDong}.
 */
@Service
@Transactional
public class ThongTinChungHopDongService {

    private static final Logger log = LoggerFactory.getLogger(ThongTinChungHopDongService.class);

    private final ThongTinChungHopDongRepository thongTinChungHopDongRepository;

    private final ThongTinChungHopDongMapper thongTinChungHopDongMapper;

    public ThongTinChungHopDongService(
        ThongTinChungHopDongRepository thongTinChungHopDongRepository,
        ThongTinChungHopDongMapper thongTinChungHopDongMapper
    ) {
        this.thongTinChungHopDongRepository = thongTinChungHopDongRepository;
        this.thongTinChungHopDongMapper = thongTinChungHopDongMapper;
    }

    /**
     * Save a thongTinChungHopDong.
     *
     * @param thongTinChungHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public ThongTinChungHopDongDTO save(ThongTinChungHopDongDTO thongTinChungHopDongDTO) {
        log.debug("Request to save ThongTinChungHopDong : {}", thongTinChungHopDongDTO);
        ThongTinChungHopDong thongTinChungHopDong = thongTinChungHopDongMapper.toEntity(thongTinChungHopDongDTO);
        thongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);
        return thongTinChungHopDongMapper.toDto(thongTinChungHopDong);
    }

    /**
     * Update a thongTinChungHopDong.
     *
     * @param thongTinChungHopDongDTO the entity to save.
     * @return the persisted entity.
     */
    public ThongTinChungHopDongDTO update(ThongTinChungHopDongDTO thongTinChungHopDongDTO) {
        log.debug("Request to update ThongTinChungHopDong : {}", thongTinChungHopDongDTO);
        ThongTinChungHopDong thongTinChungHopDong = thongTinChungHopDongMapper.toEntity(thongTinChungHopDongDTO);
        thongTinChungHopDong = thongTinChungHopDongRepository.save(thongTinChungHopDong);
        return thongTinChungHopDongMapper.toDto(thongTinChungHopDong);
    }

    /**
     * Partially update a thongTinChungHopDong.
     *
     * @param thongTinChungHopDongDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ThongTinChungHopDongDTO> partialUpdate(ThongTinChungHopDongDTO thongTinChungHopDongDTO) {
        log.debug("Request to partially update ThongTinChungHopDong : {}", thongTinChungHopDongDTO);

        return thongTinChungHopDongRepository
            .findById(thongTinChungHopDongDTO.getId())
            .map(existingThongTinChungHopDong -> {
                thongTinChungHopDongMapper.partialUpdate(existingThongTinChungHopDong, thongTinChungHopDongDTO);

                return existingThongTinChungHopDong;
            })
            .map(thongTinChungHopDongRepository::save)
            .map(thongTinChungHopDongMapper::toDto);
    }

    /**
     * Get all the thongTinChungHopDongs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ThongTinChungHopDongDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThongTinChungHopDongs");
        return thongTinChungHopDongRepository.findAll(pageable).map(thongTinChungHopDongMapper::toDto);
    }

    /**
     * Get one thongTinChungHopDong by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ThongTinChungHopDongDTO> findOne(Long id) {
        log.debug("Request to get ThongTinChungHopDong : {}", id);
        return thongTinChungHopDongRepository.findById(id).map(thongTinChungHopDongMapper::toDto);
    }

    /**
     * Delete the thongTinChungHopDong by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ThongTinChungHopDong : {}", id);
        thongTinChungHopDongRepository.deleteById(id);
    }
}
