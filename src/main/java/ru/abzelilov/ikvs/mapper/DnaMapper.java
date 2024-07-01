package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.dna.DnaDto;
import ru.abzelilov.ikvs.model.Dna;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface DnaMapper {


    @Mapping(target = "idStrain", source = "DnaDto.idStrain")
    Dna toDna(DnaDto DnaDto);

    @Mapping(target = "idStrain", source = "dna.idStrain")
    DnaDto toDnaDto(Dna dna);

}
