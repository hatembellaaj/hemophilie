package org.adhes.hemophilie.service.mapper;

import org.adhes.hemophilie.domain.Fiche;
import org.adhes.hemophilie.domain.Hemarthrose;
import org.adhes.hemophilie.domain.HematomePsoas;
import org.adhes.hemophilie.domain.HematomeSuperficiel;
import org.adhes.hemophilie.domain.HemorragieVisceres;
import org.adhes.hemophilie.domain.HemorragiesCutaneoMuqueuses;
import org.adhes.hemophilie.domain.Patient;
import org.adhes.hemophilie.domain.SaignementSNC;
import org.adhes.hemophilie.domain.User;
import org.adhes.hemophilie.service.dto.FicheDTO;
import org.adhes.hemophilie.service.dto.HemarthroseDTO;
import org.adhes.hemophilie.service.dto.HematomePsoasDTO;
import org.adhes.hemophilie.service.dto.HematomeSuperficielDTO;
import org.adhes.hemophilie.service.dto.HemorragieVisceresDTO;
import org.adhes.hemophilie.service.dto.HemorragiesCutaneoMuqueusesDTO;
import org.adhes.hemophilie.service.dto.PatientDTO;
import org.adhes.hemophilie.service.dto.SaignementSNCDTO;
import org.adhes.hemophilie.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fiche} and its DTO {@link FicheDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheMapper extends EntityMapper<FicheDTO, Fiche> {
    @Mapping(target = "hemarthrose", source = "hemarthrose", qualifiedByName = "hemarthroseId")
    @Mapping(target = "hematomeSuperficiel", source = "hematomeSuperficiel", qualifiedByName = "hematomeSuperficielId")
    @Mapping(target = "hematomePsoas", source = "hematomePsoas", qualifiedByName = "hematomePsoasId")
    @Mapping(
        target = "hemorragiesCutaneoMuqueuses",
        source = "hemorragiesCutaneoMuqueuses",
        qualifiedByName = "hemorragiesCutaneoMuqueusesId"
    )
    @Mapping(target = "hemorragieVisceres", source = "hemorragieVisceres", qualifiedByName = "hemorragieVisceresId")
    @Mapping(target = "saignementSNC", source = "saignementSNC", qualifiedByName = "saignementSNCId")
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "patient", source = "patient", qualifiedByName = "patientId")
    FicheDTO toDto(Fiche s);

    @Named("hemarthroseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HemarthroseDTO toDtoHemarthroseId(Hemarthrose hemarthrose);

    @Named("hematomeSuperficielId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HematomeSuperficielDTO toDtoHematomeSuperficielId(HematomeSuperficiel hematomeSuperficiel);

    @Named("hematomePsoasId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HematomePsoasDTO toDtoHematomePsoasId(HematomePsoas hematomePsoas);

    @Named("hemorragiesCutaneoMuqueusesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HemorragiesCutaneoMuqueusesDTO toDtoHemorragiesCutaneoMuqueusesId(HemorragiesCutaneoMuqueuses hemorragiesCutaneoMuqueuses);

    @Named("hemorragieVisceresId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HemorragieVisceresDTO toDtoHemorragieVisceresId(HemorragieVisceres hemorragieVisceres);

    @Named("saignementSNCId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SaignementSNCDTO toDtoSaignementSNCId(SaignementSNC saignementSNC);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("patientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PatientDTO toDtoPatientId(Patient patient);
}
