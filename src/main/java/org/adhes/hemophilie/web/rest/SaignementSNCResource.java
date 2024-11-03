package org.adhes.hemophilie.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.adhes.hemophilie.repository.SaignementSNCRepository;
import org.adhes.hemophilie.service.SaignementSNCService;
import org.adhes.hemophilie.service.dto.SaignementSNCDTO;
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
 * REST controller for managing {@link org.adhes.hemophilie.domain.SaignementSNC}.
 */
@RestController
@RequestMapping("/api/saignement-sncs")
public class SaignementSNCResource {

    private static final Logger LOG = LoggerFactory.getLogger(SaignementSNCResource.class);

    private static final String ENTITY_NAME = "saignementSNC";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SaignementSNCService saignementSNCService;

    private final SaignementSNCRepository saignementSNCRepository;

    public SaignementSNCResource(SaignementSNCService saignementSNCService, SaignementSNCRepository saignementSNCRepository) {
        this.saignementSNCService = saignementSNCService;
        this.saignementSNCRepository = saignementSNCRepository;
    }

    /**
     * {@code POST  /saignement-sncs} : Create a new saignementSNC.
     *
     * @param saignementSNCDTO the saignementSNCDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new saignementSNCDTO, or with status {@code 400 (Bad Request)} if the saignementSNC has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SaignementSNCDTO> createSaignementSNC(@RequestBody SaignementSNCDTO saignementSNCDTO) throws URISyntaxException {
        LOG.debug("REST request to save SaignementSNC : {}", saignementSNCDTO);
        if (saignementSNCDTO.getId() != null) {
            throw new BadRequestAlertException("A new saignementSNC cannot already have an ID", ENTITY_NAME, "idexists");
        }
        saignementSNCDTO = saignementSNCService.save(saignementSNCDTO);
        return ResponseEntity.created(new URI("/api/saignement-sncs/" + saignementSNCDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, saignementSNCDTO.getId().toString()))
            .body(saignementSNCDTO);
    }

    /**
     * {@code PUT  /saignement-sncs/:id} : Updates an existing saignementSNC.
     *
     * @param id the id of the saignementSNCDTO to save.
     * @param saignementSNCDTO the saignementSNCDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated saignementSNCDTO,
     * or with status {@code 400 (Bad Request)} if the saignementSNCDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the saignementSNCDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SaignementSNCDTO> updateSaignementSNC(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SaignementSNCDTO saignementSNCDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update SaignementSNC : {}, {}", id, saignementSNCDTO);
        if (saignementSNCDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, saignementSNCDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!saignementSNCRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        saignementSNCDTO = saignementSNCService.update(saignementSNCDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, saignementSNCDTO.getId().toString()))
            .body(saignementSNCDTO);
    }

    /**
     * {@code PATCH  /saignement-sncs/:id} : Partial updates given fields of an existing saignementSNC, field will ignore if it is null
     *
     * @param id the id of the saignementSNCDTO to save.
     * @param saignementSNCDTO the saignementSNCDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated saignementSNCDTO,
     * or with status {@code 400 (Bad Request)} if the saignementSNCDTO is not valid,
     * or with status {@code 404 (Not Found)} if the saignementSNCDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the saignementSNCDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SaignementSNCDTO> partialUpdateSaignementSNC(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SaignementSNCDTO saignementSNCDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update SaignementSNC partially : {}, {}", id, saignementSNCDTO);
        if (saignementSNCDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, saignementSNCDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!saignementSNCRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SaignementSNCDTO> result = saignementSNCService.partialUpdate(saignementSNCDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, saignementSNCDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /saignement-sncs} : get all the saignementSNCS.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of saignementSNCS in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SaignementSNCDTO>> getAllSaignementSNCS(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("fiche-is-null".equals(filter)) {
            LOG.debug("REST request to get all SaignementSNCs where fiche is null");
            return new ResponseEntity<>(saignementSNCService.findAllWhereFicheIsNull(), HttpStatus.OK);
        }
        LOG.debug("REST request to get a page of SaignementSNCS");
        Page<SaignementSNCDTO> page = saignementSNCService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /saignement-sncs/:id} : get the "id" saignementSNC.
     *
     * @param id the id of the saignementSNCDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the saignementSNCDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SaignementSNCDTO> getSaignementSNC(@PathVariable("id") Long id) {
        LOG.debug("REST request to get SaignementSNC : {}", id);
        Optional<SaignementSNCDTO> saignementSNCDTO = saignementSNCService.findOne(id);
        return ResponseUtil.wrapOrNotFound(saignementSNCDTO);
    }

    /**
     * {@code DELETE  /saignement-sncs/:id} : delete the "id" saignementSNC.
     *
     * @param id the id of the saignementSNCDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaignementSNC(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete SaignementSNC : {}", id);
        saignementSNCService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
