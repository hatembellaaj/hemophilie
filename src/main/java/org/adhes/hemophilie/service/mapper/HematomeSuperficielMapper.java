package org.adhes.hemophilie.service.mapper;

import org.adhes.hemophilie.domain.HematomeSuperficiel;
import org.adhes.hemophilie.service.dto.HematomeSuperficielDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HematomeSuperficiel} and its DTO {@link HematomeSuperficielDTO}.
 */
@Mapper(componentModel = "spring")
public interface HematomeSuperficielMapper extends EntityMapper<HematomeSuperficielDTO, HematomeSuperficiel> {}
