package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucLoaiSoCongChung;
import vn.vnpt.repository.DanhMucLoaiSoCongChungRepository;
import vn.vnpt.service.dto.DanhMucLoaiSoCongChungDTO;
import vn.vnpt.service.mapper.DanhMucLoaiSoCongChungMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiSoCongChung}.
 */
@Service
@Transactional
public class DanhMucLoaiSoCongChungService {

    private static final Logger log = LoggerFactory.getLogger(DanhMucLoaiSoCongChungService.class);

    private final DanhMucLoaiSoCongChungRepository danhMucLoaiSoCongChungRepository;

    private final DanhMucLoaiSoCongChungMapper danhMucLoaiSoCongChungMapper;

    public DanhMucLoaiSoCongChungService(
        DanhMucLoaiSoCongChungRepository danhMucLoaiSoCongChungRepository,
        DanhMucLoaiSoCongChungMapper danhMucLoaiSoCongChungMapper
    ) {
        this.danhMucLoaiSoCongChungRepository = danhMucLoaiSoCongChungRepository;
        this.danhMucLoaiSoCongChungMapper = danhMucLoaiSoCongChungMapper;
    }

    /**
     * Save a danhMucLoaiSoCongChung.
     *
     * @param danhMucLoaiSoCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucLoaiSoCongChungDTO save(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO) {
        log.debug("Request to save DanhMucLoaiSoCongChung : {}", danhMucLoaiSoCongChungDTO);
        DanhMucLoaiSoCongChung danhMucLoaiSoCongChung = danhMucLoaiSoCongChungMapper.toEntity(danhMucLoaiSoCongChungDTO);
        danhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);
        return danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);
    }

    /**
     * Update a danhMucLoaiSoCongChung.
     *
     * @param danhMucLoaiSoCongChungDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucLoaiSoCongChungDTO update(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO) {
        log.debug("Request to update DanhMucLoaiSoCongChung : {}", danhMucLoaiSoCongChungDTO);
        DanhMucLoaiSoCongChung danhMucLoaiSoCongChung = danhMucLoaiSoCongChungMapper.toEntity(danhMucLoaiSoCongChungDTO);
        danhMucLoaiSoCongChung = danhMucLoaiSoCongChungRepository.save(danhMucLoaiSoCongChung);
        return danhMucLoaiSoCongChungMapper.toDto(danhMucLoaiSoCongChung);
    }

    /**
     * Partially update a danhMucLoaiSoCongChung.
     *
     * @param danhMucLoaiSoCongChungDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucLoaiSoCongChungDTO> partialUpdate(DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO) {
        log.debug("Request to partially update DanhMucLoaiSoCongChung : {}", danhMucLoaiSoCongChungDTO);

        return danhMucLoaiSoCongChungRepository
            .findById(danhMucLoaiSoCongChungDTO.getId())
            .map(existingDanhMucLoaiSoCongChung -> {
                danhMucLoaiSoCongChungMapper.partialUpdate(existingDanhMucLoaiSoCongChung, danhMucLoaiSoCongChungDTO);

                return existingDanhMucLoaiSoCongChung;
            })
            .map(danhMucLoaiSoCongChungRepository::save)
            .map(danhMucLoaiSoCongChungMapper::toDto);
    }

    /**
     * Get all the danhMucLoaiSoCongChungs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucLoaiSoCongChungDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhMucLoaiSoCongChungs");
        return danhMucLoaiSoCongChungRepository.findAll(pageable).map(danhMucLoaiSoCongChungMapper::toDto);
    }

    /**
     * Get one danhMucLoaiSoCongChung by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucLoaiSoCongChungDTO> findOne(Long id) {
        log.debug("Request to get DanhMucLoaiSoCongChung : {}", id);
        return danhMucLoaiSoCongChungRepository.findById(id).map(danhMucLoaiSoCongChungMapper::toDto);
    }

    /**
     * Delete the danhMucLoaiSoCongChung by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DanhMucLoaiSoCongChung : {}", id);
        danhMucLoaiSoCongChungRepository.deleteById(id);
    }
}
