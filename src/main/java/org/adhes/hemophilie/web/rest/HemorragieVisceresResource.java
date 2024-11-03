package org.adhes.hemophilie.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.adhes.hemophilie.repository.HemorragieVisceresRepository;
import org.adhes.hemophilie.service.HemorragieVisceresService;
import org.adhes.hemophilie.service.dto.HemorragieVisceresDTO;
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
 * REST controller for managing {@link org.adhes.hemophilie.domain.HemorragieVisceres}.
 */
@RestController
@RequestMapping("/api/hemorragie-visceres")
public class HemorragieVisceresResource {

    private static final Logger LOG = LoggerFactory.getLogger(HemorragieVisceresResource.class);

    private static final String ENTITY_NAME = "hemorragieVisceres";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HemorragieVisceresService hemorragieVisceresService;

    private final HemorragieVisceresRepository hemorragieVisceresRepository;

    public HemorragieVisceresResource(
        HemorragieVisceresService hemorragieVisceresService,
        HemorragieVisceresRepository hemorragieVisceresRepository
    ) {
        this.hemorragieVisceresService = hemorragieVisceresService;
        this.hemorragieVisceresRepository = hemorragieVisceresRepository;
    }

    /**
     * {@code POST  /hemorragie-visceres} : Create a new hemorragieVisceres.
     *
     * @param hemorragieVisceresDTO the hemorragieVisceresDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hemorragieVisceresDTO, or with status {@code 400 (Bad Request)} if the hemorragieVisceres has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HemorragieVisceresDTO> createHemorragieVisceres(@RequestBody HemorragieVisceresDTO hemorragieVisceresDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save HemorragieVisceres : {}", hemorragieVisceresDTO);
        if (hemorragieVisceresDTO.getId() != null) {
            throw new BadRequestAlertException("A new hemorragieVisceres cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hemorragieVisceresDTO = hemorragieVisceresService.save(hemorragieVisceresDTO);
        return ResponseEntity.created(new URI("/api/hemorragie-visceres/" + hemorragieVisceresDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, hemorragieVisceresDTO.getId().toString()))
            .body(hemorragieVisceresDTO);
    }

    /**
     * {@code PUT  /hemorragie-visceres/:id} : Updates an existing hemorragieVisceres.
     *
     * @param id the id of the hemorragieVisceresDTO to save.
     * @param hemorragieVisceresDTO the hemorragieVisceresDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hemorragieVisceresDTO,
     * or with status {@code 400 (Bad Request)} if the hemorragieVisceresDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hemorragieVisceresDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HemorragieVisceresDTO> updateHemorragieVisceres(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HemorragieVisceresDTO hemorragieVisceresDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update HemorragieVisceres : {}, {}", id, hemorragieVisceresDTO);
        if (hemorragieVisceresDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hemorragieVisceresDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hemorragieVisceresRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hemorragieVisceresDTO = hemorragieVisceresService.update(hemorragieVisceresDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hemorragieVisceresDTO.getId().toString()))
            .body(hemorragieVisceresDTO);
    }

    /**
     * {@code PATCH  /hemorragie-visceres/:id} : Partial updates given fields of an existing hemorragieVisceres, field will ignore if it is null
     *
     * @param id the id of the hemorragieVisceresDTO to save.
     * @param hemorragieVisceresDTO the hemorragieVisceresDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hemorragieVisceresDTO,
     * or with status {@code 400 (Bad Request)} if the hemorragieVisceresDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hemorragieVisceresDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hemorragieVisceresDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HemorragieVisceresDTO> partialUpdateHemorragieVisceres(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HemorragieVisceresDTO hemorragieVisceresDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update HemorragieVisceres partially : {}, {}", id, hemorragieVisceresDTO);
        if (hemorragieVisceresDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hemorragieVisceresDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hemorragieVisceresRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HemorragieVisceresDTO> result = hemorragieVisceresService.partialUpdate(hemorragieVisceresDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hemorragieVisceresDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /hemorragie-visceres} : get all the hemorragieVisceres.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hemorragieVisceres in body.
     */
    @GetMapping("")
    public ResponseEntity<List<HemorragieVisceresDTO>> getAllHemorragieVisceres(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("fiche-is-null".equals(filter)) {
            LOG.debug("REST request to get all HemorragieVisceress where fiche is null");
            return new ResponseEntity<>(hemorragieVisceresService.findAllWhereFicheIsNull(), HttpStatus.OK);
        }
        LOG.debug("REST request to get a page of HemorragieVisceres");
        Page<HemorragieVisceresDTO> page = hemorragieVisceresService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hemorragie-visceres/:id} : get the "id" hemorragieVisceres.
     *
     * @param id the id of the hemorragieVisceresDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hemorragieVisceresDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HemorragieVisceresDTO> getHemorragieVisceres(@PathVariable("id") Long id) {
        LOG.debug("REST request to get HemorragieVisceres : {}", id);
        Optional<HemorragieVisceresDTO> hemorragieVisceresDTO = hemorragieVisceresService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hemorragieVisceresDTO);
    }

    /**
     * {@code DELETE  /hemorragie-visceres/:id} : delete the "id" hemorragieVisceres.
     *
     * @param id the id of the hemorragieVisceresDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHemorragieVisceres(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete HemorragieVisceres : {}", id);
        hemorragieVisceresService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
