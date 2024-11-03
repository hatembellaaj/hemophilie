package org.adhes.hemophilie.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.adhes.hemophilie.domain.Hemarthrose;
import org.adhes.hemophilie.repository.HemarthroseRepository;
import org.adhes.hemophilie.service.dto.HemarthroseDTO;
import org.adhes.hemophilie.service.mapper.HemarthroseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.adhes.hemophilie.domain.Hemarthrose}.
 */
@Service
@Transactional
public class HemarthroseService {

    private static final Logger LOG = LoggerFactory.getLogger(HemarthroseService.class);

    private final HemarthroseRepository hemarthroseRepository;

    private final HemarthroseMapper hemarthroseMapper;

    public HemarthroseService(HemarthroseRepository hemarthroseRepository, HemarthroseMapper hemarthroseMapper) {
        this.hemarthroseRepository = hemarthroseRepository;
        this.hemarthroseMapper = hemarthroseMapper;
    }

    /**
     * Save a hemarthrose.
     *
     * @param hemarthroseDTO the entity to save.
     * @return the persisted entity.
     */
    public HemarthroseDTO save(HemarthroseDTO hemarthroseDTO) {
        LOG.debug("Request to save Hemarthrose : {}", hemarthroseDTO);
        Hemarthrose hemarthrose = hemarthroseMapper.toEntity(hemarthroseDTO);
        hemarthrose = hemarthroseRepository.save(hemarthrose);
        return hemarthroseMapper.toDto(hemarthrose);
    }

    /**
     * Update a hemarthrose.
     *
     * @param hemarthroseDTO the entity to save.
     * @return the persisted entity.
     */
    public HemarthroseDTO update(HemarthroseDTO hemarthroseDTO) {
        LOG.debug("Request to update Hemarthrose : {}", hemarthroseDTO);
        Hemarthrose hemarthrose = hemarthroseMapper.toEntity(hemarthroseDTO);
        hemarthrose = hemarthroseRepository.save(hemarthrose);
        return hemarthroseMapper.toDto(hemarthrose);
    }

    /**
     * Partially update a hemarthrose.
     *
     * @param hemarthroseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HemarthroseDTO> partialUpdate(HemarthroseDTO hemarthroseDTO) {
        LOG.debug("Request to partially update Hemarthrose : {}", hemarthroseDTO);

        return hemarthroseRepository
            .findById(hemarthroseDTO.getId())
            .map(existingHemarthrose -> {
                hemarthroseMapper.partialUpdate(existingHemarthrose, hemarthroseDTO);

                return existingHemarthrose;
            })
            .map(hemarthroseRepository::save)
            .map(hemarthroseMapper::toDto);
    }

    /**
     * Get all the hemarthroses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HemarthroseDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Hemarthroses");
        return hemarthroseRepository.findAll(pageable).map(hemarthroseMapper::toDto);
    }

    /**
     *  Get all the hemarthroses where Fiche is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HemarthroseDTO> findAllWhereFicheIsNull() {
        LOG.debug("Request to get all hemarthroses where Fiche is null");
        return StreamSupport.stream(hemarthroseRepository.findAll().spliterator(), false)
            .filter(hemarthrose -> hemarthrose.getFiche() == null)
            .map(hemarthroseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one hemarthrose by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HemarthroseDTO> findOne(Long id) {
        LOG.debug("Request to get Hemarthrose : {}", id);
        return hemarthroseRepository.findById(id).map(hemarthroseMapper::toDto);
    }

    /**
     * Delete the hemarthrose by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Hemarthrose : {}", id);
        hemarthroseRepository.deleteById(id);
    }
}
