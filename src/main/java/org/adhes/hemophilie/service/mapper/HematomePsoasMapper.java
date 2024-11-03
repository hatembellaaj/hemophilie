package org.adhes.hemophilie.service.mapper;

import org.adhes.hemophilie.domain.HematomePsoas;
import org.adhes.hemophilie.service.dto.HematomePsoasDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HematomePsoas} and its DTO {@link HematomePsoasDTO}.
 */
@Mapper(componentModel = "spring")
public interface HematomePsoasMapper extends EntityMapper<HematomePsoasDTO, HematomePsoas> {}
