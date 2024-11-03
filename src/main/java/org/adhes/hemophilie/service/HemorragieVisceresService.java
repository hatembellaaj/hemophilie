package org.adhes.hemophilie.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.adhes.hemophilie.domain.HemorragieVisceres;
import org.adhes.hemophilie.repository.HemorragieVisceresRepository;
import org.adhes.hemophilie.service.dto.HemorragieVisceresDTO;
import org.adhes.hemophilie.service.mapper.HemorragieVisceresMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.adhes.hemophilie.domain.HemorragieVisceres}.
 */
@Service
@Transactional
public class HemorragieVisceresService {

    private static final Logger LOG = LoggerFactory.getLogger(HemorragieVisceresService.class);

    private final HemorragieVisceresRepository hemorragieVisceresRepository;

    private final HemorragieVisceresMapper hemorragieVisceresMapper;

    public HemorragieVisceresService(
        HemorragieVisceresRepository hemorragieVisceresRepository,
        HemorragieVisceresMapper hemorragieVisceresMapper
    ) {
        this.hemorragieVisceresRepository = hemorragieVisceresRepository;
        this.hemorragieVisceresMapper = hemorragieVisceresMapper;
    }

    /**
     * Save a hemorragieVisceres.
     *
     * @param hemorragieVisceresDTO the entity to save.
     * @return the persisted entity.
     */
    public HemorragieVisceresDTO save(HemorragieVisceresDTO hemorragieVisceresDTO) {
        LOG.debug("Request to save HemorragieVisceres : {}", hemorragieVisceresDTO);
        HemorragieVisceres hemorragieVisceres = hemorragieVisceresMapper.toEntity(hemorragieVisceresDTO);
        hemorragieVisceres = hemorragieVisceresRepository.save(hemorragieVisceres);
        return hemorragieVisceresMapper.toDto(hemorragieVisceres);
    }

    /**
     * Update a hemorragieVisceres.
     *
     * @param hemorragieVisceresDTO the entity to save.
     * @return the persisted entity.
     */
    public HemorragieVisceresDTO update(HemorragieVisceresDTO hemorragieVisceresDTO) {
        LOG.debug("Request to update HemorragieVisceres : {}", hemorragieVisceresDTO);
        HemorragieVisceres hemorragieVisceres = hemorragieVisceresMapper.toEntity(hemorragieVisceresDTO);
        hemorragieVisceres = hemorragieVisceresRepository.save(hemorragieVisceres);
        return hemorragieVisceresMapper.toDto(hemorragieVisceres);
    }

    /**
     * Partially update a hemorragieVisceres.
     *
     * @param hemorragieVisceresDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HemorragieVisceresDTO> partialUpdate(HemorragieVisceresDTO hemorragieVisceresDTO) {
        LOG.debug("Request to partially update HemorragieVisceres : {}", hemorragieVisceresDTO);

        return hemorragieVisceresRepository
            .findById(hemorragieVisceresDTO.getId())
            .map(existingHemorragieVisceres -> {
                hemorragieVisceresMapper.partialUpdate(existingHemorragieVisceres, hemorragieVisceresDTO);

                return existingHemorragieVisceres;
            })
            .map(hemorragieVisceresRepository::save)
            .map(hemorragieVisceresMapper::toDto);
    }

    /**
     * Get all the hemorragieVisceres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HemorragieVisceresDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all HemorragieVisceres");
        return hemorragieVisceresRepository.findAll(pageable).map(hemorragieVisceresMapper::toDto);
    }

    /**
     *  Get all the hemorragieVisceres where Fiche is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HemorragieVisceresDTO> findAllWhereFicheIsNull() {
        LOG.debug("Request to get all hemorragieVisceres where Fiche is null");
        return StreamSupport.stream(hemorragieVisceresRepository.findAll().spliterator(), false)
            .filter(hemorragieVisceres -> hemorragieVisceres.getFiche() == null)
            .map(hemorragieVisceresMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one hemorragieVisceres by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HemorragieVisceresDTO> findOne(Long id) {
        LOG.debug("Request to get HemorragieVisceres : {}", id);
        return hemorragieVisceresRepository.findById(id).map(hemorragieVisceresMapper::toDto);
    }

    /**
     * Delete the hemorragieVisceres by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete HemorragieVisceres : {}", id);
        hemorragieVisceresRepository.deleteById(id);
    }
}
