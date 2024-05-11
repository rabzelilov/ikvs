package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.model.Bacteria;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface BacteriaMapper {

    @Mapping(target = "kindName", source = "cardDto.kind")
    @Mapping(target = "isTypical", source = "cardDto.isTypical")
    Bacteria toStrain(CardDto cardDto);

    @Mapping(target = "kindName", source = "cardShortDto.kind")
    @Mapping(target = "idStrain", source = "cardShortDto.idStrain")
    @Mapping(target = "synonyms", source = "cardShortDto.synonyms")
    @Mapping(target = "genus", source = "cardShortDto.genus")
    @Mapping(target = "isTypical", source = "cardShortDto.isTypical")
    @Mapping(target = "applicationArea", source = "cardShortDto.applicationArea")
    @Mapping(target = "pathogenGroup", source = "cardShortDto.pathogenGroup")
    Bacteria toStrain(CardShortDto cardShortDto);


    @Mapping(target = "kindName", source = "strainUpdateDto.kind")
    @Mapping(target = "idStrain", source = "strainUpdateDto.idStrain")
    Bacteria toStrain(StrainUpdateDto strainUpdateDto);

    @Mapping(target = "id", source = "bacteria.id")
    @Mapping(target = "kind", source = "bacteria.kindName")
    @Mapping(target = "idStrain", source = "bacteria.idStrain")
    @Mapping(target = "synonyms", source = "bacteria.synonyms")
    @Mapping(target = "genus", source = "bacteria.genus")
    @Mapping(target = "isTypical", source = "bacteria.typical")
    @Mapping(target = "applicationArea", source = "bacteria.applicationArea")
    @Mapping(target = "pathogenGroup", source = "bacteria.pathogenGroup")
    CardDto toCardDto(Bacteria bacteria);

    @Mapping(target = "kind", source = "bacteria.kindName")
    @Mapping(target = "idStrain", source = "bacteria.idStrain")
    @Mapping(target = "synonyms", source = "bacteria.synonyms")
    @Mapping(target = "genus", source = "bacteria.genus")
    @Mapping(target = "isTypical", source = "bacteria.typical")
    @Mapping(target = "applicationArea", source = "bacteria.applicationArea")
    @Mapping(target = "pathogenGroup", source = "bacteria.pathogenGroup")
    CardShortDto toCardShortDto(Bacteria bacteria);

}
