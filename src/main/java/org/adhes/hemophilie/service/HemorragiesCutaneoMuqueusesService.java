package org.adhes.hemophilie.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueuses;
import org.adhes.hemophilie.repository.HemorragiesCutaneoMuqueusesRepository;
import org.adhes.hemophilie.service.dto.HemorragiesCutaneoMuqueusesDTO;
import org.adhes.hemophilie.service.mapper.HemorragiesCutaneoMuqueusesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueuses}.
 */
@Service
@Transactional
public class HemorragiesCutaneoMuqueusesService {

    private static final Logger LOG = LoggerFactory.getLogger(HemorragiesCutaneoMuqueusesService.class);

    private final HemorragiesCutaneoMuqueusesRepository hemorragiesCutaneoMuqueusesRepository;

    private final HemorragiesCutaneoMuqueusesMapper hemorragiesCutaneoMuqueusesMapper;

    public HemorragiesCutaneoMuqueusesService(
        HemorragiesCutaneoMuqueusesRepository hemorragiesCutaneoMuqueusesRepository,
        HemorragiesCutaneoMuqueusesMapper hemorragiesCutaneoMuqueusesMapper
    ) {
        this.hemorragiesCutaneoMuqueusesRepository = hemorragiesCutaneoMuqueusesRepository;
        this.hemorragiesCutaneoMuqueusesMapper = hemorragiesCutaneoMuqueusesMapper;
    }

    /**
     * Save a hemorragiesCutaneoMuqueuses.
     *
     * @param hemorragiesCutaneoMuqueusesDTO the entity to save.
     * @return the persisted entity.
     */
    public HemorragiesCutaneoMuqueusesDTO save(HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO) {
        LOG.debug("Request to save HemorragiesCutaneoMuqueuses : {}", hemorragiesCutaneoMuqueusesDTO);
        HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesMapper.toEntity(
            hemorragiesCutaneoMuqueusesDTO
        );
        hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository.save(hemorragiesCutaneoMuqueuses);
        return hemorragiesCutaneoMuqueusesMapper.toDto(hemorragiesCutaneoMuqueuses);
    }

    /**
     * Update a hemorragiesCutaneoMuqueuses.
     *
     * @param hemorragiesCutaneoMuqueusesDTO the entity to save.
     * @return the persisted entity.
     */
    public HemorragiesCutaneoMuqueusesDTO update(HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO) {
        LOG.debug("Request to update HemorragiesCutaneoMuqueuses : {}", hemorragiesCutaneoMuqueusesDTO);
        HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesMapper.toEntity(
            hemorragiesCutaneoMuqueusesDTO
        );
        hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueusesRepository.save(hemorragiesCutaneoMuqueuses);
        return hemorragiesCutaneoMuqueusesMapper.toDto(hemorragiesCutaneoMuqueuses);
    }

    /**
     * Partially update a hemorragiesCutaneoMuqueuses.
     *
     * @param hemorragiesCutaneoMuqueusesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<HemorragiesCutaneoMuqueusesDTO> partialUpdate(HemorragiesCutaneoMuqueusesDTO hemorragiesCutaneoMuqueusesDTO) {
        LOG.debug("Request to partially update HemorragiesCutaneoMuqueuses : {}", hemorragiesCutaneoMuqueusesDTO);

        return hemorragiesCutaneoMuqueusesRepository
            .findById(hemorragiesCutaneoMuqueusesDTO.getId())
            .map(existingHemorragiesCutaneoMuqueuses -> {
                hemorragiesCutaneoMuqueusesMapper.partialUpdate(existingHemorragiesCutaneoMuqueuses, hemorragiesCutaneoMuqueusesDTO);

                return existingHemorragiesCutaneoMuqueuses;
            })
            .map(hemorragiesCutaneoMuqueusesRepository::save)
            .map(hemorragiesCutaneoMuqueusesMapper::toDto);
    }

    /**
     * Get all the hemorragiesCutaneoMuqueuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HemorragiesCutaneoMuqueusesDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all HemorragiesCutaneoMuqueuses");
        return hemorragiesCutaneoMuqueusesRepository.findAll(pageable).map(hemorragiesCutaneoMuqueusesMapper::toDto);
    }

    /**
     *  Get all the hemorragiesCutaneoMuqueuses where Fiche is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HemorragiesCutaneoMuqueusesDTO> findAllWhereFicheIsNull() {
        LOG.debug("Request to get all hemorragiesCutaneoMuqueuses where Fiche is null");
        return StreamSupport.stream(hemorragiesCutaneoMuqueusesRepository.findAll().spliterator(), false)
            .filter(hemorragiesCutaneoMuqueuses -> hemorragiesCutaneoMuqueuses.getFiche() == null)
            .map(hemorragiesCutaneoMuqueusesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one hemorragiesCutaneoMuqueuses by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HemorragiesCutaneoMuqueusesDTO> findOne(Long id) {
        LOG.debug("Request to get HemorragiesCutaneoMuqueuses : {}", id);
        return hemorragiesCutaneoMuqueusesRepository.findById(id).map(hemorragiesCutaneoMuqueusesMapper::toDto);
    }

    /**
     * Delete the hemorragiesCutaneoMuqueuses by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete HemorragiesCutaneoMuqueuses : {}", id);
        hemorragiesCutaneoMuqueusesRepository.deleteById(id);
    }
}
