package org.adhes.hemophilie.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.adhes.hemophilie.domain.HematomeSuperficiel;
import org.adhes.hemophilie.repository.HematomeSuperficielRepository;
import org.adhes.hemophilie.service.dto.HematomeSuperficielDTO;
import org.adhes.hemophilie.service.mapper.HematomeSuperficielMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.adhes.hemophilie.domain.HematomeSuperficiel}.
 */
@Service
@Transactional
public class HematomeSuperficielService {

    private static final Logger LOG = LoggerFactory.getLogger(HematomeSuperficielService.class);

    private final HematomeSuperficielRepository hematomeSuperficielRepository;

    private final HematomeSuperficielMapper hematomeSuperficielMapper;

    public HematomeSuperficielService(
        HematomeSuperficielRepository hematomeSuperficielRepository,
        HematomeSuperficielMapper hematomeSuperficielMapper
    ) {
        this.hematomeSuperficielRepository = hematomeSuperficielRepository;
        this.hematomeSuperficielMapper = hematomeSuperficielMapper;
    }

    /**
     * Save a hematomeSuperficiel.
     *
     * @param hematomeSuperficielDTO the entity to save.
     * @return the persisted entity.
     */
    public HematomeSuperficielDTO save(HematomeSuperficielDTO hematomeSuperficielDTO) {
        LOG.debug("Request to save HematomeSuperficiel : {}", hematomeSuperficielDTO);
        HematomeSuperficiel hematomeSuperficiel = hematomeSuperficielMapper.toEntity(hematomeSuperficielDTO);
        hematomeSuperficiel = hematomeSuperficielRepository.save(hematomeSuperficiel);
        return hematomeSuperficielMapper.toDto(hematomeSuperficiel);
    }

    /**
     * Update a hematomeSuperficiel.
     *
     * @param hematomeSuperficielDTO the entity to save.
     * @return the persisted entity.
     */
    public HematomeSuperficielDTO update(HematomeSuperficielDTO hematomeSuperficielDTO) {
        LOG.debug("Request to update HematomeSuperficiel : {}", hematomeSuperficielDTO);
        HematomeSuperficiel hematomeSuperficiel = hematomeSuperficielMapper.toEntity(hematomeSuperficielDTO);
        hematomeSuperficiel = hematomeSuperficielRepository.save(hematomeSuperficiel);
        return hematomeSuperficielMapper.toDto(hematomeSuperficiel);
    }

    /**
     * Partially update a hematomeSuperficiel.
     *
     * @param hematomeSuperficielDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HematomeSuperficielDTO> partialUpdate(HematomeSuperficielDTO hematomeSuperficielDTO) {
        LOG.debug("Request to partially update HematomeSuperficiel : {}", hematomeSuperficielDTO);

        return hematomeSuperficielRepository
            .findById(hematomeSuperficielDTO.getId())
            .map(existingHematomeSuperficiel -> {
                hematomeSuperficielMapper.partialUpdate(existingHematomeSuperficiel, hematomeSuperficielDTO);

                return existingHematomeSuperficiel;
            })
            .map(hematomeSuperficielRepository::save)
            .map(hematomeSuperficielMapper::toDto);
    }

    /**
     * Get all the hematomeSuperficiels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HematomeSuperficielDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all HematomeSuperficiels");
        return hematomeSuperficielRepository.findAll(pageable).map(hematomeSuperficielMapper::toDto);
    }

    /**
     *  Get all the hematomeSuperficiels where Fiche is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HematomeSuperficielDTO> findAllWhereFicheIsNull() {
        LOG.debug("Request to get all hematomeSuperficiels where Fiche is null");
        return StreamSupport.stream(hematomeSuperficielRepository.findAll().spliterator(), false)
            .filter(hematomeSuperficiel -> hematomeSuperficiel.getFiche() == null)
            .map(hematomeSuperficielMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one hematomeSuperficiel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HematomeSuperficielDTO> findOne(Long id) {
        LOG.debug("Request to get HematomeSuperficiel : {}", id);
        return hematomeSuperficielRepository.findById(id).map(hematomeSuperficielMapper::toDto);
    }

    /**
     * Delete the hematomeSuperficiel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete HematomeSuperficiel : {}", id);
        hematomeSuperficielRepository.deleteById(id);
    }
}
