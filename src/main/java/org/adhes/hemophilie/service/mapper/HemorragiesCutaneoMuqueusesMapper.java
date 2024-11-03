package org.adhes.hemophilie.service.mapper;

import org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueuses;
import org.adhes.hemophilie.service.dto.HemorragiesCutaneoMuqueusesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HemorragiesCutaneoMuqueuses} and its DTO {@link HemorragiesCutaneoMuqueusesDTO}.
 */
@Mapper(componentModel = "spring")
public interface HemorragiesCutaneoMuqueusesMapper extends EntityMapper<HemorragiesCutaneoMuqueusesDTO, HemorragiesCutaneoMuqueuses> {}
