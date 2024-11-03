package org.adhes.hemophilie.service.mapper;

import org.adhes.hemophilie.domain.Hemarthrose;
import org.adhes.hemophilie.service.dto.HemarthroseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Hemarthrose} and its DTO {@link HemarthroseDTO}.
 */
@Mapper(componentModel = "spring")
public interface HemarthroseMapper extends EntityMapper<HemarthroseDTO, Hemarthrose> {}
