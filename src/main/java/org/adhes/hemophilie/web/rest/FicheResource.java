package org.adhes.hemophilie.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.adhes.hemophilie.repository.FicheRepository;
import org.adhes.hemophilie.service.FicheService;
import org.adhes.hemophilie.service.dto.FicheDTO;
import org.adhes.hemophilie.web.rest.errors.BadRequestAlertException;
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

/**
 * REST controller for managing {@link org.adhes.hemophilie.domain.Fiche}.
 */
@RestController
@RequestMapping("/api/fiches")
public class FicheResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheResource.class);

    private static final String ENTITY_NAME = "fiche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheService ficheService;

    private final FicheRepository ficheRepository;

    public FicheResource(FicheService ficheService, FicheRepository ficheRepository) {
        this.ficheService = ficheService;
        this.ficheRepository = ficheRepository;
    }

    /**
     * {@code POST  /fiches} : Create a new fiche.
     *
     * @param ficheDTO the ficheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheDTO, or with status {@code 400 (Bad Request)} if the fiche has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheDTO> createFiche(@Valid @RequestBody FicheDTO ficheDTO) throws URISyntaxException {
        LOG.debug("REST request to save Fiche : {}", ficheDTO);
        if (ficheDTO.getId() != null) {
            throw new BadRequestAlertException("A new fiche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheDTO = ficheService.save(ficheDTO);
        return ResponseEntity.created(new URI("/api/fiches/" + ficheDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheDTO.getId().toString()))
            .body(ficheDTO);
    }

    /**
     * {@code PUT  /fiches/:id} : Updates an existing fiche.
     *
     * @param id the id of the ficheDTO to save.
     * @param ficheDTO the ficheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheDTO,
     * or with status {@code 400 (Bad Request)} if the ficheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheDTO> updateFiche(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheDTO ficheDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Fiche : {}, {}", id, ficheDTO);
        if (ficheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheDTO = ficheService.update(ficheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheDTO.getId().toString()))
            .body(ficheDTO);
    }

    /**
     * {@code PATCH  /fiches/:id} : Partial updates given fields of an existing fiche, field will ignore if it is null
     *
     * @param id the id of the ficheDTO to save.
     * @param ficheDTO the ficheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheDTO,
     * or with status {@code 400 (Bad Request)} if the ficheDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheDTO> partialUpdateFiche(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheDTO ficheDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Fiche partially : {}, {}", id, ficheDTO);
        if (ficheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheDTO> result = ficheService.partialUpdate(ficheDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiches} : get all the fiches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fiches in body.
     */
    @GetMapping("")
    public ResponseEntity<List<FicheDTO>> getAllFiches(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of Fiches");
        Page<FicheDTO> page = ficheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fiches/:id} : get the "id" fiche.
     *
     * @param id the id of the ficheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheDTO> getFiche(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Fiche : {}", id);
        Optional<FicheDTO> ficheDTO = ficheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheDTO);
    }

    /**
     * {@code DELETE  /fiches/:id} : delete the "id" fiche.
     *
     * @param id the id of the ficheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiche(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Fiche : {}", id);
        ficheService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
