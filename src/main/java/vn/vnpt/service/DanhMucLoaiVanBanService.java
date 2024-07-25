package vn.vnpt.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vnpt.domain.DanhMucLoaiVanBan;
import vn.vnpt.repository.DanhMucLoaiVanBanRepository;
import vn.vnpt.service.dto.DanhMucLoaiVanBanDTO;
import vn.vnpt.service.mapper.DanhMucLoaiVanBanMapper;

/**
 * Service Implementation for managing {@link vn.vnpt.domain.DanhMucLoaiVanBan}.
 */
@Service
@Transactional
public class DanhMucLoaiVanBanService {

    private static final Logger log = LoggerFactory.getLogger(DanhMucLoaiVanBanService.class);

    private final DanhMucLoaiVanBanRepository danhMucLoaiVanBanRepository;

    private final DanhMucLoaiVanBanMapper danhMucLoaiVanBanMapper;

    public DanhMucLoaiVanBanService(
        DanhMucLoaiVanBanRepository danhMucLoaiVanBanRepository,
        DanhMucLoaiVanBanMapper danhMucLoaiVanBanMapper
    ) {
        this.danhMucLoaiVanBanRepository = danhMucLoaiVanBanRepository;
        this.danhMucLoaiVanBanMapper = danhMucLoaiVanBanMapper;
    }

    /**
     * Save a danhMucLoaiVanBan.
     *
     * @param danhMucLoaiVanBanDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucLoaiVanBanDTO save(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO) {
        log.debug("Request to save DanhMucLoaiVanBan : {}", danhMucLoaiVanBanDTO);
        DanhMucLoaiVanBan danhMucLoaiVanBan = danhMucLoaiVanBanMapper.toEntity(danhMucLoaiVanBanDTO);
        danhMucLoaiVanBan = danhMucLoaiVanBanRepository.save(danhMucLoaiVanBan);
        return danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);
    }

    /**
     * Update a danhMucLoaiVanBan.
     *
     * @param danhMucLoaiVanBanDTO the entity to save.
     * @return the persisted entity.
     */
    public DanhMucLoaiVanBanDTO update(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO) {
        log.debug("Request to update DanhMucLoaiVanBan : {}", danhMucLoaiVanBanDTO);
        DanhMucLoaiVanBan danhMucLoaiVanBan = danhMucLoaiVanBanMapper.toEntity(danhMucLoaiVanBanDTO);
        danhMucLoaiVanBan = danhMucLoaiVanBanRepository.save(danhMucLoaiVanBan);
        return danhMucLoaiVanBanMapper.toDto(danhMucLoaiVanBan);
    }

    /**
     * Partially update a danhMucLoaiVanBan.
     *
     * @param danhMucLoaiVanBanDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DanhMucLoaiVanBanDTO> partialUpdate(DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO) {
        log.debug("Request to partially update DanhMucLoaiVanBan : {}", danhMucLoaiVanBanDTO);

        return danhMucLoaiVanBanRepository
            .findById(danhMucLoaiVanBanDTO.getId())
            .map(existingDanhMucLoaiVanBan -> {
                danhMucLoaiVanBanMapper.partialUpdate(existingDanhMucLoaiVanBan, danhMucLoaiVanBanDTO);

                return existingDanhMucLoaiVanBan;
            })
            .map(danhMucLoaiVanBanRepository::save)
            .map(danhMucLoaiVanBanMapper::toDto);
    }

    /**
     * Get all the danhMucLoaiVanBans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DanhMucLoaiVanBanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DanhMucLoaiVanBans");
        return danhMucLoaiVanBanRepository.findAll(pageable).map(danhMucLoaiVanBanMapper::toDto);
    }

    /**
     * Get one danhMucLoaiVanBan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DanhMucLoaiVanBanDTO> findOne(Long id) {
        log.debug("Request to get DanhMucLoaiVanBan : {}", id);
        return danhMucLoaiVanBanRepository.findById(id).map(danhMucLoaiVanBanMapper::toDto);
    }

    /**
     * Delete the danhMucLoaiVanBan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DanhMucLoaiVanBan : {}", id);
        danhMucLoaiVanBanRepository.deleteById(id);
    }
}
