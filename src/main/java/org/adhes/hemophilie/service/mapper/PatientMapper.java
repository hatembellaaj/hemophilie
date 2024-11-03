package org.adhes.hemophilie.service.mapper;

import org.adhes.hemophilie.domain.Patient;
import org.adhes.hemophilie.service.dto.PatientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Patient} and its DTO {@link PatientDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientMapper extends EntityMapper<PatientDTO, Patient> {}
