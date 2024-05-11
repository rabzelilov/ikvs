package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;
import ru.abzelilov.ikvs.model.Dna;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface DnaMapper {


    @Mapping(target = "idStrain", source = "cardShortDto.idStrain")
    Dna toDna(CardShortDto cardShortDto);

    @Mapping(target = "idStrain", source = "strainUpdateDto.idStrain")
    Dna toDna(StrainUpdateDto strainUpdateDto);

    @Mapping(target = "id", source = "dna.id")
    @Mapping(target = "idStrain", source = "dna.idStrain")
    CardDto toCardDto(Dna dna);

    @Mapping(target = "idStrain", source = "dna.idStrain")
    CardShortDto toCardShortDto(Dna dna);

}
