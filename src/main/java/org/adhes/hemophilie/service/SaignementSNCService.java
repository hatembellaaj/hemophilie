package org.adhes.hemophilie.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.adhes.hemophilie.domain.SaignementSNC;
import org.adhes.hemophilie.repository.SaignementSNCRepository;
import org.adhes.hemophilie.service.dto.SaignementSNCDTO;
import org.adhes.hemophilie.service.mapper.SaignementSNCMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.adhes.hemophilie.domain.SaignementSNC}.
 */
@Service
@Transactional
public class SaignementSNCService {

    private static final Logger LOG = LoggerFactory.getLogger(SaignementSNCService.class);

    private final SaignementSNCRepository saignementSNCRepository;

    private final SaignementSNCMapper saignementSNCMapper;

    public SaignementSNCService(SaignementSNCRepository saignementSNCRepository, SaignementSNCMapper saignementSNCMapper) {
        this.saignementSNCRepository = saignementSNCRepository;
        this.saignementSNCMapper = saignementSNCMapper;
    }

    /**
     * Save a saignementSNC.
     *
     * @param saignementSNCDTO the entity to save.
     * @return the persisted entity.
     */
    public SaignementSNCDTO save(SaignementSNCDTO saignementSNCDTO) {
        LOG.debug("Request to save SaignementSNC : {}", saignementSNCDTO);
        SaignementSNC saignementSNC = saignementSNCMapper.toEntity(saignementSNCDTO);
        saignementSNC = saignementSNCRepository.save(saignementSNC);
        return saignementSNCMapper.toDto(saignementSNC);
    }

    /**
     * Update a saignementSNC.
     *
     * @param saignementSNCDTO the entity to save.
     * @return the persisted entity.
     */
    public SaignementSNCDTO update(SaignementSNCDTO saignementSNCDTO) {
        LOG.debug("Request to update SaignementSNC : {}", saignementSNCDTO);
        SaignementSNC saignementSNC = saignementSNCMapper.toEntity(saignementSNCDTO);
        saignementSNC = saignementSNCRepository.save(saignementSNC);
        return saignementSNCMapper.toDto(saignementSNC);
    }

    /**
     * Partially update a saignementSNC.
     *
     * @param saignementSNCDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SaignementSNCDTO> partialUpdate(SaignementSNCDTO saignementSNCDTO) {
        LOG.debug("Request to partially update SaignementSNC : {}", saignementSNCDTO);

        return saignementSNCRepository
            .findById(saignementSNCDTO.getId())
            .map(existingSaignementSNC -> {
                saignementSNCMapper.partialUpdate(existingSaignementSNC, saignementSNCDTO);

                return existingSaignementSNC;
            })
            .map(saignementSNCRepository::save)
            .map(saignementSNCMapper::toDto);
    }

    /**
     * Get all the saignementSNCS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SaignementSNCDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all SaignementSNCS");
        return saignementSNCRepository.findAll(pageable).map(saignementSNCMapper::toDto);
    }

    /**
     *  Get all the saignementSNCS where Fiche is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SaignementSNCDTO> findAllWhereFicheIsNull() {
        LOG.debug("Request to get all saignementSNCS where Fiche is null");
        return StreamSupport.stream(saignementSNCRepository.findAll().spliterator(), false)
            .filter(saignementSNC -> saignementSNC.getFiche() == null)
            .map(saignementSNCMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one saignementSNC by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SaignementSNCDTO> findOne(Long id) {
        LOG.debug("Request to get SaignementSNC : {}", id);
        return saignementSNCRepository.findById(id).map(saignementSNCMapper::toDto);
    }

    /**
     * Delete the saignementSNC by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete SaignementSNC : {}", id);
        saignementSNCRepository.deleteById(id);
    }
}
