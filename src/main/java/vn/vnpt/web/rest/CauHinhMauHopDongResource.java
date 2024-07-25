package vn.vnpt.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.vnpt.repository.CauHinhMauHopDongRepository;
import vn.vnpt.service.CauHinhMauHopDongService;
import vn.vnpt.service.dto.CauHinhMauHopDongDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.CauHinhMauHopDong}.
 */
@RestController
@RequestMapping("/api/cau-hinh-mau-hop-dongs")
public class CauHinhMauHopDongResource {

    private static final Logger log = LoggerFactory.getLogger(CauHinhMauHopDongResource.class);

    private static final String ENTITY_NAME = "hopDongCauHinhMauHopDong";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CauHinhMauHopDongService cauHinhMauHopDongService;

    private final CauHinhMauHopDongRepository cauHinhMauHopDongRepository;

    public CauHinhMauHopDongResource(
        CauHinhMauHopDongService cauHinhMauHopDongService,
        CauHinhMauHopDongRepository cauHinhMauHopDongRepository
    ) {
        this.cauHinhMauHopDongService = cauHinhMauHopDongService;
        this.cauHinhMauHopDongRepository = cauHinhMauHopDongRepository;
    }

    /**
     * {@code POST  /cau-hinh-mau-hop-dongs} : Create a new cauHinhMauHopDong.
     *
     * @param cauHinhMauHopDongDTO the cauHinhMauHopDongDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cauHinhMauHopDongDTO, or with status {@code 400 (Bad Request)} if the cauHinhMauHopDong has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CauHinhMauHopDongDTO> createCauHinhMauHopDong(@RequestBody CauHinhMauHopDongDTO cauHinhMauHopDongDTO)
        throws URISyntaxException {
        log.debug("REST request to save CauHinhMauHopDong : {}", cauHinhMauHopDongDTO);
        if (cauHinhMauHopDongDTO.getId() != null) {
            throw new BadRequestAlertException("A new cauHinhMauHopDong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cauHinhMauHopDongDTO = cauHinhMauHopDongService.save(cauHinhMauHopDongDTO);
        return ResponseEntity.created(new URI("/api/cau-hinh-mau-hop-dongs/" + cauHinhMauHopDongDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cauHinhMauHopDongDTO.getId().toString()))
            .body(cauHinhMauHopDongDTO);
    }

    /**
     * {@code PUT  /cau-hinh-mau-hop-dongs/:id} : Updates an existing cauHinhMauHopDong.
     *
     * @param id the id of the cauHinhMauHopDongDTO to save.
     * @param cauHinhMauHopDongDTO the cauHinhMauHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHinhMauHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the cauHinhMauHopDongDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cauHinhMauHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CauHinhMauHopDongDTO> updateCauHinhMauHopDong(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CauHinhMauHopDongDTO cauHinhMauHopDongDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CauHinhMauHopDong : {}, {}", id, cauHinhMauHopDongDTO);
        if (cauHinhMauHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cauHinhMauHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cauHinhMauHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cauHinhMauHopDongDTO = cauHinhMauHopDongService.update(cauHinhMauHopDongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cauHinhMauHopDongDTO.getId().toString()))
            .body(cauHinhMauHopDongDTO);
    }

    /**
     * {@code PATCH  /cau-hinh-mau-hop-dongs/:id} : Partial updates given fields of an existing cauHinhMauHopDong, field will ignore if it is null
     *
     * @param id the id of the cauHinhMauHopDongDTO to save.
     * @param cauHinhMauHopDongDTO the cauHinhMauHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHinhMauHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the cauHinhMauHopDongDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cauHinhMauHopDongDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cauHinhMauHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CauHinhMauHopDongDTO> partialUpdateCauHinhMauHopDong(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CauHinhMauHopDongDTO cauHinhMauHopDongDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CauHinhMauHopDong partially : {}, {}", id, cauHinhMauHopDongDTO);
        if (cauHinhMauHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cauHinhMauHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cauHinhMauHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CauHinhMauHopDongDTO> result = cauHinhMauHopDongService.partialUpdate(cauHinhMauHopDongDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cauHinhMauHopDongDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cau-hinh-mau-hop-dongs} : get all the cauHinhMauHopDongs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cauHinhMauHopDongs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CauHinhMauHopDongDTO>> getAllCauHinhMauHopDongs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of CauHinhMauHopDongs");
        Page<CauHinhMauHopDongDTO> page = cauHinhMauHopDongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cau-hinh-mau-hop-dongs/:id} : get the "id" cauHinhMauHopDong.
     *
     * @param id the id of the cauHinhMauHopDongDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cauHinhMauHopDongDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CauHinhMauHopDongDTO> getCauHinhMauHopDong(@PathVariable("id") Long id) {
        log.debug("REST request to get CauHinhMauHopDong : {}", id);
        Optional<CauHinhMauHopDongDTO> cauHinhMauHopDongDTO = cauHinhMauHopDongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cauHinhMauHopDongDTO);
    }

    /**
     * {@code DELETE  /cau-hinh-mau-hop-dongs/:id} : delete the "id" cauHinhMauHopDong.
     *
     * @param id the id of the cauHinhMauHopDongDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCauHinhMauHopDong(@PathVariable("id") Long id) {
        log.debug("REST request to delete CauHinhMauHopDong : {}", id);
        cauHinhMauHopDongService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
