package org.adhes.hemophilie.service.mapper;

import org.adhes.hemophilie.domain.HemorragieVisceres;
import org.adhes.hemophilie.service.dto.HemorragieVisceresDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HemorragieVisceres} and its DTO {@link HemorragieVisceresDTO}.
 */
@Mapper(componentModel = "spring")
public interface HemorragieVisceresMapper extends EntityMapper<HemorragieVisceresDTO, HemorragieVisceres> {}
