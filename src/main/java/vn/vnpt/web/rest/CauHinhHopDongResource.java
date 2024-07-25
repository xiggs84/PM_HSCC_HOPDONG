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
import vn.vnpt.repository.CauHinhHopDongRepository;
import vn.vnpt.service.CauHinhHopDongService;
import vn.vnpt.service.dto.CauHinhHopDongDTO;
import vn.vnpt.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.vnpt.domain.CauHinhHopDong}.
 */
@RestController
@RequestMapping("/api/cau-hinh-hop-dongs")
public class CauHinhHopDongResource {

    private static final Logger log = LoggerFactory.getLogger(CauHinhHopDongResource.class);

    private static final String ENTITY_NAME = "hopDongCauHinhHopDong";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CauHinhHopDongService cauHinhHopDongService;

    private final CauHinhHopDongRepository cauHinhHopDongRepository;

    public CauHinhHopDongResource(CauHinhHopDongService cauHinhHopDongService, CauHinhHopDongRepository cauHinhHopDongRepository) {
        this.cauHinhHopDongService = cauHinhHopDongService;
        this.cauHinhHopDongRepository = cauHinhHopDongRepository;
    }

    /**
     * {@code POST  /cau-hinh-hop-dongs} : Create a new cauHinhHopDong.
     *
     * @param cauHinhHopDongDTO the cauHinhHopDongDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cauHinhHopDongDTO, or with status {@code 400 (Bad Request)} if the cauHinhHopDong has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CauHinhHopDongDTO> createCauHinhHopDong(@RequestBody CauHinhHopDongDTO cauHinhHopDongDTO)
        throws URISyntaxException {
        log.debug("REST request to save CauHinhHopDong : {}", cauHinhHopDongDTO);
        if (cauHinhHopDongDTO.getId() != null) {
            throw new BadRequestAlertException("A new cauHinhHopDong cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cauHinhHopDongDTO = cauHinhHopDongService.save(cauHinhHopDongDTO);
        return ResponseEntity.created(new URI("/api/cau-hinh-hop-dongs/" + cauHinhHopDongDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, cauHinhHopDongDTO.getId().toString()))
            .body(cauHinhHopDongDTO);
    }

    /**
     * {@code PUT  /cau-hinh-hop-dongs/:id} : Updates an existing cauHinhHopDong.
     *
     * @param id the id of the cauHinhHopDongDTO to save.
     * @param cauHinhHopDongDTO the cauHinhHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHinhHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the cauHinhHopDongDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cauHinhHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CauHinhHopDongDTO> updateCauHinhHopDong(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CauHinhHopDongDTO cauHinhHopDongDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CauHinhHopDong : {}, {}", id, cauHinhHopDongDTO);
        if (cauHinhHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cauHinhHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cauHinhHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cauHinhHopDongDTO = cauHinhHopDongService.update(cauHinhHopDongDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cauHinhHopDongDTO.getId().toString()))
            .body(cauHinhHopDongDTO);
    }

    /**
     * {@code PATCH  /cau-hinh-hop-dongs/:id} : Partial updates given fields of an existing cauHinhHopDong, field will ignore if it is null
     *
     * @param id the id of the cauHinhHopDongDTO to save.
     * @param cauHinhHopDongDTO the cauHinhHopDongDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cauHinhHopDongDTO,
     * or with status {@code 400 (Bad Request)} if the cauHinhHopDongDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cauHinhHopDongDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cauHinhHopDongDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CauHinhHopDongDTO> partialUpdateCauHinhHopDong(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CauHinhHopDongDTO cauHinhHopDongDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CauHinhHopDong partially : {}, {}", id, cauHinhHopDongDTO);
        if (cauHinhHopDongDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cauHinhHopDongDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cauHinhHopDongRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CauHinhHopDongDTO> result = cauHinhHopDongService.partialUpdate(cauHinhHopDongDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cauHinhHopDongDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cau-hinh-hop-dongs} : get all the cauHinhHopDongs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cauHinhHopDongs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CauHinhHopDongDTO>> getAllCauHinhHopDongs(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of CauHinhHopDongs");
        Page<CauHinhHopDongDTO> page = cauHinhHopDongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cau-hinh-hop-dongs/:id} : get the "id" cauHinhHopDong.
     *
     * @param id the id of the cauHinhHopDongDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cauHinhHopDongDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CauHinhHopDongDTO> getCauHinhHopDong(@PathVariable("id") Long id) {
        log.debug("REST request to get CauHinhHopDong : {}", id);
        Optional<CauHinhHopDongDTO> cauHinhHopDongDTO = cauHinhHopDongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cauHinhHopDongDTO);
    }

    /**
     * {@code DELETE  /cau-hinh-hop-dongs/:id} : delete the "id" cauHinhHopDong.
     *
     * @param id the id of the cauHinhHopDongDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCauHinhHopDong(@PathVariable("id") Long id) {
        log.debug("REST request to delete CauHinhHopDong : {}", id);
        cauHinhHopDongService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
