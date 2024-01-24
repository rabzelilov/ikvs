package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.model.Strain;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface StrainMapper {

    Strain toStrain(CardDto cardDto);

    Strain toStrain(CardShortDto cardShortDto);

    Strain toStrain(StrainAddDto strainAddDto);

    CardDto toCardDto(Strain strain);

    CardShortDto toCardShortDto(Strain strain);

}
