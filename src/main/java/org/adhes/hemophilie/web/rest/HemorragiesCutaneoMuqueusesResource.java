package org.adhes.hemophilie.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.adhes.hemophilie.repository.HemorragiesCutaneoMuqueusesRepository;
import org.adhes.hemophilie.service.HemorragiesCutaneoMuqueusesService;
import org.adhes.hemophilie.service.dto.HemorragiesCutaneoMuqueusesDTO;
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
 * REST controller for managing {@link org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueuses}.
 */
@RestController
@RequestMapping("/api/hemorragies-cutaneo-muqueuses")
public class HemorragiesCutaneoMuqueusesResource {

    private static final Logger LOG = LoggerFactory.getLogger(HemorragiesCutaneoMuqueusesResource.class);

    private static final String ENTITY_NAME = "hemorragiesCutaneoMuqueuses";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HemorragiesCutaneoMuqueusesService hemorragiesCutaneoMuqueusesService;

    private final HemorragiesCutaneoMuqueusesRepository hemorragiesCutaneoMuqueusesRepository;

    public HemorragiesCutaneoMuqueusesResource(
        HemorragiesCutaneoMuqueusesService hemorragiesCutaneoMuqueusesService,
        HemorragiesCutaneoMuqueusesRepository hemorragiesCutaneoMuqueusesRepository
    ) {
        this.hemorragiesCutaneoMuqueusesService = hemorragiesCutaneoMuqueusesService;
        this.hemorragiesCutaneoMuqueusesRepository = hemorragiesCutaneoMuqueusesRepository;
    }

    /**
     * {@code POST  /hemorragies-cutaneo-muqueuses} : Create a new hemorragiesCutaneoMuqueuses.
     *
     * @param hemorragiesCutaneoMuqueusesDTO the hemorragiesCutaneoMuqueusesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hemorragiesCutaneoMuqueusesDTO, or with status {@code 400 (Bad Request)} if the hemorragiesCutaneoMuqueuses has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<HemorragiesCutaneoMuqueusesDTO> createHemorragiesCutaneoMuqueuses(
        @RequestBody HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save HemorragiesCutaneoMuqueuses : {}", hemorragiesCutaneoMuqueusesDTO);
        if (hemorragiesCutaneoMuqueusesDTO.getId() != null) {
            throw new BadRequestAlertException("A new hemorragiesCutaneoMuqueuses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesService.save(hemorragiesCutaneoMuqueusesDTO);
        return ResponseEntity.created(new URI("/api/hemorragies-cutaneo-muqueuses/" + hemorragiesCutaneoMuqueusesDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, hemorragiesCutaneoMuqueusesDTO.getId().toString())
            )
            .body(hemorragiesCutaneoMuqueusesDTO);
    }

    /**
     * {@code PUT  /hemorragies-cutaneo-muqueuses/:id} : Updates an existing hemorragiesCutaneoMuqueuses.
     *
     * @param id the id of the hemorragiesCutaneoMuqueusesDTO to save.
     * @param hemorragiesCutaneoMuqueusesDTO the hemorragiesCutaneoMuqueusesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hemorragiesCutaneoMuqueusesDTO,
     * or with status {@code 400 (Bad Request)} if the hemorragiesCutaneoMuqueusesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hemorragiesCutaneoMuqueusesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<HemorragiesCutaneoMuqueusesDTO> updateHemorragiesCutaneoMuqueuses(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update HemorragiesCutaneoMuqueuses : {}, {}", id, hemorragiesCutaneoMuqueusesDTO);
        if (hemorragiesCutaneoMuqueusesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hemorragiesCutaneoMuqueusesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hemorragiesCutaneoMuqueusesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesService.update(hemorragiesCutaneoMuqueusesDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hemorragiesCutaneoMuqueusesDTO.getId().toString())
            )
            .body(hemorragiesCutaneoMuqueusesDTO);
    }

    /**
     * {@code PATCH  /hemorragies-cutaneo-muqueuses/:id} : Partial updates given fields of an existing hemorragiesCutaneoMuqueuses, field will ignore if it is null
     *
     * @param id the id of the hemorragiesCutaneoMuqueusesDTO to save.
     * @param hemorragiesCutaneoMuqueusesDTO the hemorragiesCutaneoMuqueusesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hemorragiesCutaneoMuqueusesDTO,
     * or with status {@code 400 (Bad Request)} if the hemorragiesCutaneoMuqueusesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hemorragiesCutaneoMuqueusesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hemorragiesCutaneoMuqueusesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HemorragiesCutaneoMuqueusesDTO> partialUpdateHemorragiesCutaneoMuqueuses(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update HemorragiesCutaneoMuqueuses partially : {}, {}", id, hemorragiesCutaneoMuqueusesDTO);
        if (hemorragiesCutaneoMuqueusesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hemorragiesCutaneoMuqueusesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hemorragiesCutaneoMuqueusesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HemorragiesCutaneoMuqueusesDTO> result = hemorragiesCutaneoMuqueusesService.partialUpdate(hemorragiesCutaneoMuqueusesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hemorragiesCutaneoMuqueusesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /hemorragies-cutaneo-muqueuses} : get all the hemorragiesCutaneoMuqueuses.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hemorragiesCutaneoMuqueuses in body.
     */
    @GetMapping("")
    public ResponseEntity<List<HemorragiesCutaneoMuqueusesDTO>> getAllHemorragiesCutaneoMuqueuses(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("fiche-is-null".equals(filter)) {
            LOG.debug("REST request to get all HemorragiesCutaneoMuqueusess where fiche is null");
            return new ResponseEntity<>(hemorragiesCutaneoMuqueusesService.findAllWhereFicheIsNull(), HttpStatus.OK);
        }
        LOG.debug("REST request to get a page of HemorragiesCutaneoMuqueuses");
        Page<HemorragiesCutaneoMuqueusesDTO> page = hemorragiesCutaneoMuqueusesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hemorragies-cutaneo-muqueuses/:id} : get the "id" hemorragiesCutaneoMuqueuses.
     *
     * @param id the id of the hemorragiesCutaneoMuqueusesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hemorragiesCutaneoMuqueusesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<HemorragiesCutaneoMuqueusesDTO> getHemorragiesCutaneoMuqueuses(@PathVariable("id") Long id) {
        LOG.debug("REST request to get HemorragiesCutaneoMuqueuses : {}", id);
        Optional<HemorragiesCutaneoMuqueusesDTO> hemorragiesCutaneoMuqueusesDTO = hemorragiesCutaneoMuqueusesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hemorragiesCutaneoMuqueusesDTO);
    }

    /**
     * {@code DELETE  /hemorragies-cutaneo-muqueuses/:id} : delete the "id" hemorragiesCutaneoMuqueuses.
     *
     * @param id the id of the hemorragiesCutaneoMuqueusesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHemorragiesCutaneoMuqueuses(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete HemorragiesCutaneoMuqueuses : {}", id);
        hemorragiesCutaneoMuqueusesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
