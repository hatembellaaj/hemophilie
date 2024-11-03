package org.adhes.hemophilie.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.adhes.hemophilie.repository.HematomeSuperficielRepository;
import org.adhes.hemophilie.service.HematomeSuperficielService;
import org.adhes.hemophilie.service.dto.HematomeSuperficielDTO;
import org.adhes.hemophilie.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.adhes.hemophilie.domain.HematomeSuperficiel}.
 */
@RestController
@RequestMapping("/api/hematome-superficiels")
public class HematomeSuperficielResource {

    private static final Logger LOG = LoggerFactory.getLogger(HematomeSuperficielResource.class);

    private static final String ENTITY_NAME = "hematomeSuperficiel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HematomeSuperficielService hematomeSuperficielService;

    private final HematomeSuperficielRepository hematomeSuperficielRepository;

    public HematomeSuperficielResource(
        HematomeSuperficielService hematomeSuperficielService,
        HematomeSuperficielRepository hematomeSuperficielRepository
    ) {
        this.hematomeSuperficielService = hematomeSuperficielService;
        this.hematomeSuperficielRepository = hematomeSuperficielRepository;
    }

    /**
     * {@code POST  /hematome-superficiels} : Create a new hematomeSuperficiel.
     *
     * @param hematomeSuperficielDTO the hematomeSuperficielDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hematomeSuperficielDTO, or with status {@code 400 (Bad Request)} if the hematomeSuperficiel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HematomeSuperficielDTO> createHematomeSuperficiel(@RequestBody HematomeSuperficielDTO hematomeSuperficielDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save HematomeSuperficiel : {}", hematomeSuperficielDTO);
        if (hematomeSuperficielDTO.getId() != null) {
            throw new BadRequestAlertException("A new hematomeSuperficiel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hematomeSuperficielDTO = hematomeSuperficielService.save(hematomeSuperficielDTO);
        return ResponseEntity.created(new URI("/api/hematome-superficiels/" + hematomeSuperficielDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, hematomeSuperficielDTO.getId().toString()))
            .body(hematomeSuperficielDTO);
    }

    /**
     * {@code PUT  /hematome-superficiels/:id} : Updates an existing hematomeSuperficiel.
     *
     * @param id the id of the hematomeSuperficielDTO to save.
     * @param hematomeSuperficielDTO the hematomeSuperficielDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hematomeSuperficielDTO,
     * or with status {@code 400 (Bad Request)} if the hematomeSuperficielDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hematomeSuperficielDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HematomeSuperficielDTO> updateHematomeSuperficiel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HematomeSuperficielDTO hematomeSuperficielDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update HematomeSuperficiel : {}, {}", id, hematomeSuperficielDTO);
        if (hematomeSuperficielDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hematomeSuperficielDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hematomeSuperficielRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hematomeSuperficielDTO = hematomeSuperficielService.update(hematomeSuperficielDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hematomeSuperficielDTO.getId().toString()))
            .body(hematomeSuperficielDTO);
    }

    /**
     * {@code PATCH  /hematome-superficiels/:id} : Partial updates given fields of an existing hematomeSuperficiel, field will ignore if it is null
     *
     * @param id the id of the hematomeSuperficielDTO to save.
     * @param hematomeSuperficielDTO the hematomeSuperficielDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hematomeSuperficielDTO,
     * or with status {@code 400 (Bad Request)} if the hematomeSuperficielDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hematomeSuperficielDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hematomeSuperficielDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HematomeSuperficielDTO> partialUpdateHematomeSuperficiel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HematomeSuperficielDTO hematomeSuperficielDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update HematomeSuperficiel partially : {}, {}", id, hematomeSuperficielDTO);
        if (hematomeSuperficielDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hematomeSuperficielDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hematomeSuperficielRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HematomeSuperficielDTO> result = hematomeSuperficielService.partialUpdate(hematomeSuperficielDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hematomeSuperficielDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /hematome-superficiels} : get all the hematomeSuperficiels.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hematomeSuperficiels in body.
     */
    @GetMapping("")
    public ResponseEntity<List<HematomeSuperficielDTO>> getAllHematomeSuperficiels(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("fiche-is-null".equals(filter)) {
            LOG.debug("REST request to get all HematomeSuperficiels where fiche is null");
            return new ResponseEntity<>(hematomeSuperficielService.findAllWhereFicheIsNull(), HttpStatus.OK);
        }
        LOG.debug("REST request to get a page of HematomeSuperficiels");
        Page<HematomeSuperficielDTO> page = hematomeSuperficielService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hematome-superficiels/:id} : get the "id" hematomeSuperficiel.
     *
     * @param id the id of the hematomeSuperficielDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hematomeSuperficielDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HematomeSuperficielDTO> getHematomeSuperficiel(@PathVariable("id") Long id) {
        LOG.debug("REST request to get HematomeSuperficiel : {}", id);
        Optional<HematomeSuperficielDTO> hematomeSuperficielDTO = hematomeSuperficielService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hematomeSuperficielDTO);
    }

    /**
     * {@code DELETE  /hematome-superficiels/:id} : delete the "id" hematomeSuperficiel.
     *
     * @param id the id of the hematomeSuperficielDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHematomeSuperficiel(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete HematomeSuperficiel : {}", id);
        hematomeSuperficielService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
