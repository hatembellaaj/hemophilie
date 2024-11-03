package org.adhes.hemophilie.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.adhes.hemophilie.repository.HemarthroseRepository;
import org.adhes.hemophilie.service.HemarthroseService;
import org.adhes.hemophilie.service.dto.HemarthroseDTO;
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
 * REST controller for managing {@link org.adhes.hemophilie.domain.Hemarthrose}.
 */
@RestController
@RequestMapping("/api/hemarthroses")
public class HemarthroseResource {

    private static final Logger LOG = LoggerFactory.getLogger(HemarthroseResource.class);

    private static final String ENTITY_NAME = "hemarthrose";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HemarthroseService hemarthroseService;

    private final HemarthroseRepository hemarthroseRepository;

    public HemarthroseResource(HemarthroseService hemarthroseService, HemarthroseRepository hemarthroseRepository) {
        this.hemarthroseService = hemarthroseService;
        this.hemarthroseRepository = hemarthroseRepository;
    }

    /**
     * {@code POST  /hemarthroses} : Create a new hemarthrose.
     *
     * @param hemarthroseDTO the hemarthroseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hemarthroseDTO, or with status {@code 400 (Bad Request)} if the hemarthrose has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HemarthroseDTO> createHemarthrose(@Valid @RequestBody HemarthroseDTO hemarthroseDTO) throws URISyntaxException {
        LOG.debug("REST request to save Hemarthrose : {}", hemarthroseDTO);
        if (hemarthroseDTO.getId() != null) {
            throw new BadRequestAlertException("A new hemarthrose cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hemarthroseDTO = hemarthroseService.save(hemarthroseDTO);
        return ResponseEntity.created(new URI("/api/hemarthroses/" + hemarthroseDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, hemarthroseDTO.getId().toString()))
            .body(hemarthroseDTO);
    }

    /**
     * {@code PUT  /hemarthroses/:id} : Updates an existing hemarthrose.
     *
     * @param id the id of the hemarthroseDTO to save.
     * @param hemarthroseDTO the hemarthroseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hemarthroseDTO,
     * or with status {@code 400 (Bad Request)} if the hemarthroseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hemarthroseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HemarthroseDTO> updateHemarthrose(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody HemarthroseDTO hemarthroseDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Hemarthrose : {}, {}", id, hemarthroseDTO);
        if (hemarthroseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hemarthroseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hemarthroseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hemarthroseDTO = hemarthroseService.update(hemarthroseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hemarthroseDTO.getId().toString()))
            .body(hemarthroseDTO);
    }

    /**
     * {@code PATCH  /hemarthroses/:id} : Partial updates given fields of an existing hemarthrose, field will ignore if it is null
     *
     * @param id the id of the hemarthroseDTO to save.
     * @param hemarthroseDTO the hemarthroseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hemarthroseDTO,
     * or with status {@code 400 (Bad Request)} if the hemarthroseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hemarthroseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hemarthroseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HemarthroseDTO> partialUpdateHemarthrose(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody HemarthroseDTO hemarthroseDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Hemarthrose partially : {}, {}", id, hemarthroseDTO);
        if (hemarthroseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hemarthroseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hemarthroseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HemarthroseDTO> result = hemarthroseService.partialUpdate(hemarthroseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hemarthroseDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /hemarthroses} : get all the hemarthroses.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hemarthroses in body.
     */
    @GetMapping("")
    public ResponseEntity<List<HemarthroseDTO>> getAllHemarthroses(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("fiche-is-null".equals(filter)) {
            LOG.debug("REST request to get all Hemarthroses where fiche is null");
            return new ResponseEntity<>(hemarthroseService.findAllWhereFicheIsNull(), HttpStatus.OK);
        }
        LOG.debug("REST request to get a page of Hemarthroses");
        Page<HemarthroseDTO> page = hemarthroseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hemarthroses/:id} : get the "id" hemarthrose.
     *
     * @param id the id of the hemarthroseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hemarthroseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HemarthroseDTO> getHemarthrose(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Hemarthrose : {}", id);
        Optional<HemarthroseDTO> hemarthroseDTO = hemarthroseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hemarthroseDTO);
    }

    /**
     * {@code DELETE  /hemarthroses/:id} : delete the "id" hemarthrose.
     *
     * @param id the id of the hemarthroseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHemarthrose(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Hemarthrose : {}", id);
        hemarthroseService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
