package org.adhes.hemophilie.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.adhes.hemophilie.repository.HematomePsoasRepository;
import org.adhes.hemophilie.service.HematomePsoasService;
import org.adhes.hemophilie.service.dto.HematomePsoasDTO;
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
 * REST controller for managing {@link org.adhes.hemophilie.domain.HematomePsoas}.
 */
@RestController
@RequestMapping("/api/hematome-psoas")
public class HematomePsoasResource {

    private static final Logger LOG = LoggerFactory.getLogger(HematomePsoasResource.class);

    private static final String ENTITY_NAME = "hematomePsoas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HematomePsoasService hematomePsoasService;

    private final HematomePsoasRepository hematomePsoasRepository;

    public HematomePsoasResource(HematomePsoasService hematomePsoasService, HematomePsoasRepository hematomePsoasRepository) {
        this.hematomePsoasService = hematomePsoasService;
        this.hematomePsoasRepository = hematomePsoasRepository;
    }

    /**
     * {@code POST  /hematome-psoas} : Create a new hematomePsoas.
     *
     * @param hematomePsoasDTO the hematomePsoasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hematomePsoasDTO, or with status {@code 400 (Bad Request)} if the hematomePsoas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HematomePsoasDTO> createHematomePsoas(@RequestBody HematomePsoasDTO hematomePsoasDTO) throws URISyntaxException {
        LOG.debug("REST request to save HematomePsoas : {}", hematomePsoasDTO);
        if (hematomePsoasDTO.getId() != null) {
            throw new BadRequestAlertException("A new hematomePsoas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hematomePsoasDTO = hematomePsoasService.save(hematomePsoasDTO);
        return ResponseEntity.created(new URI("/api/hematome-psoas/" + hematomePsoasDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, hematomePsoasDTO.getId().toString()))
            .body(hematomePsoasDTO);
    }

    /**
     * {@code PUT  /hematome-psoas/:id} : Updates an existing hematomePsoas.
     *
     * @param id the id of the hematomePsoasDTO to save.
     * @param hematomePsoasDTO the hematomePsoasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hematomePsoasDTO,
     * or with status {@code 400 (Bad Request)} if the hematomePsoasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hematomePsoasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HematomePsoasDTO> updateHematomePsoas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HematomePsoasDTO hematomePsoasDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update HematomePsoas : {}, {}", id, hematomePsoasDTO);
        if (hematomePsoasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hematomePsoasDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hematomePsoasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hematomePsoasDTO = hematomePsoasService.update(hematomePsoasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hematomePsoasDTO.getId().toString()))
            .body(hematomePsoasDTO);
    }

    /**
     * {@code PATCH  /hematome-psoas/:id} : Partial updates given fields of an existing hematomePsoas, field will ignore if it is null
     *
     * @param id the id of the hematomePsoasDTO to save.
     * @param hematomePsoasDTO the hematomePsoasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hematomePsoasDTO,
     * or with status {@code 400 (Bad Request)} if the hematomePsoasDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hematomePsoasDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hematomePsoasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HematomePsoasDTO> partialUpdateHematomePsoas(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HematomePsoasDTO hematomePsoasDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update HematomePsoas partially : {}, {}", id, hematomePsoasDTO);
        if (hematomePsoasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hematomePsoasDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hematomePsoasRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HematomePsoasDTO> result = hematomePsoasService.partialUpdate(hematomePsoasDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hematomePsoasDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /hematome-psoas} : get all the hematomePsoas.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hematomePsoas in body.
     */
    @GetMapping("")
    public ResponseEntity<List<HematomePsoasDTO>> getAllHematomePsoas(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("fiche-is-null".equals(filter)) {
            LOG.debug("REST request to get all HematomePsoass where fiche is null");
            return new ResponseEntity<>(hematomePsoasService.findAllWhereFicheIsNull(), HttpStatus.OK);
        }
        LOG.debug("REST request to get a page of HematomePsoas");
        Page<HematomePsoasDTO> page = hematomePsoasService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hematome-psoas/:id} : get the "id" hematomePsoas.
     *
     * @param id the id of the hematomePsoasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hematomePsoasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HematomePsoasDTO> getHematomePsoas(@PathVariable("id") Long id) {
        LOG.debug("REST request to get HematomePsoas : {}", id);
        Optional<HematomePsoasDTO> hematomePsoasDTO = hematomePsoasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hematomePsoasDTO);
    }

    /**
     * {@code DELETE  /hematome-psoas/:id} : delete the "id" hematomePsoas.
     *
     * @param id the id of the hematomePsoasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHematomePsoas(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete HematomePsoas : {}", id);
        hematomePsoasService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
