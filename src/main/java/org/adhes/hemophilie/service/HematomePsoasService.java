package org.adhes.hemophilie.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.adhes.hemophilie.domain.HematomePsoas;
import org.adhes.hemophilie.repository.HematomePsoasRepository;
import org.adhes.hemophilie.service.dto.HematomePsoasDTO;
import org.adhes.hemophilie.service.mapper.HematomePsoasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.adhes.hemophilie.domain.HematomePsoas}.
 */
@Service
@Transactional
public class HematomePsoasService {

    private static final Logger LOG = LoggerFactory.getLogger(HematomePsoasService.class);

    private final HematomePsoasRepository hematomePsoasRepository;

    private final HematomePsoasMapper hematomePsoasMapper;

    public HematomePsoasService(HematomePsoasRepository hematomePsoasRepository, HematomePsoasMapper hematomePsoasMapper) {
        this.hematomePsoasRepository = hematomePsoasRepository;
        this.hematomePsoasMapper = hematomePsoasMapper;
    }

    /**
     * Save a hematomePsoas.
     *
     * @param hematomePsoasDTO the entity to save.
     * @return the persisted entity.
     */
    public HematomePsoasDTO save(HematomePsoasDTO hematomePsoasDTO) {
        LOG.debug("Request to save HematomePsoas : {}", hematomePsoasDTO);
        HematomePsoas hematomePsoas = hematomePsoasMapper.toEntity(hematomePsoasDTO);
        hematomePsoas = hematomePsoasRepository.save(hematomePsoas);
        return hematomePsoasMapper.toDto(hematomePsoas);
    }

    /**
     * Update a hematomePsoas.
     *
     * @param hematomePsoasDTO the entity to save.
     * @return the persisted entity.
     */
    public HematomePsoasDTO update(HematomePsoasDTO hematomePsoasDTO) {
        LOG.debug("Request to update HematomePsoas : {}", hematomePsoasDTO);
        HematomePsoas hematomePsoas = hematomePsoasMapper.toEntity(hematomePsoasDTO);
        hematomePsoas = hematomePsoasRepository.save(hematomePsoas);
        return hematomePsoasMapper.toDto(hematomePsoas);
    }

    /**
     * Partially update a hematomePsoas.
     *
     * @param hematomePsoasDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HematomePsoasDTO> partialUpdate(HematomePsoasDTO hematomePsoasDTO) {
        LOG.debug("Request to partially update HematomePsoas : {}", hematomePsoasDTO);

        return hematomePsoasRepository
            .findById(hematomePsoasDTO.getId())
            .map(existingHematomePsoas -> {
                hematomePsoasMapper.partialUpdate(existingHematomePsoas, hematomePsoasDTO);

                return existingHematomePsoas;
            })
            .map(hematomePsoasRepository::save)
            .map(hematomePsoasMapper::toDto);
    }

    /**
     * Get all the hematomePsoas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HematomePsoasDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all HematomePsoas");
        return hematomePsoasRepository.findAll(pageable).map(hematomePsoasMapper::toDto);
    }

    /**
     *  Get all the hematomePsoas where Fiche is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HematomePsoasDTO> findAllWhereFicheIsNull() {
        LOG.debug("Request to get all hematomePsoas where Fiche is null");
        return StreamSupport.stream(hematomePsoasRepository.findAll().spliterator(), false)
            .filter(hematomePsoas -> hematomePsoas.getFiche() == null)
            .map(hematomePsoasMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one hematomePsoas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HematomePsoasDTO> findOne(Long id) {
        LOG.debug("Request to get HematomePsoas : {}", id);
        return hematomePsoasRepository.findById(id).map(hematomePsoasMapper::toDto);
    }

    /**
     * Delete the hematomePsoas by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete HematomePsoas : {}", id);
        hematomePsoasRepository.deleteById(id);
    }
}
