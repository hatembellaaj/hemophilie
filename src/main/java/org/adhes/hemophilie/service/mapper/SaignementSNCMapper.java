package org.adhes.hemophilie.service.mapper;

import org.adhes.hemophilie.domain.SaignementSNC;
import org.adhes.hemophilie.service.dto.SaignementSNCDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SaignementSNC} and its DTO {@link SaignementSNCDTO}.
 */
@Mapper(componentModel = "spring")
public interface SaignementSNCMapper extends EntityMapper<SaignementSNCDTO, SaignementSNC> {}
