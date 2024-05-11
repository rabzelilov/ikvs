package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.model.Simplest;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface SimplestMapper {

    @Mapping(target = "kindName", source = "cardDto.kind")
    @Mapping(target = "isTypical", source = "cardDto.isTypical")
    Simplest toSimplest(CardDto cardDto);


    @Mapping(target = "kindName", source = "cardShortDto.kind")
    @Mapping(target = "idStrain", source = "cardShortDto.idStrain")
    @Mapping(target = "synonyms", source = "cardShortDto.synonyms")
    @Mapping(target = "genus", source = "cardShortDto.genus")
    @Mapping(target = "isTypical", source = "cardShortDto.isTypical")
    @Mapping(target = "applicationArea", source = "cardShortDto.applicationArea")
    @Mapping(target = "pathogenGroup", source = "cardShortDto.pathogenGroup")
    Simplest toSimplest(CardShortDto cardShortDto);

    @Mapping(target = "kindName", source = "strainUpdateDto.kind")
    @Mapping(target = "idStrain", source = "strainUpdateDto.idStrain")
    Simplest toSimplest(StrainUpdateDto strainUpdateDto);

    @Mapping(target = "id", source = "simplest.id")
    @Mapping(target = "kind", source = "simplest.kindName")
    @Mapping(target = "idStrain", source = "simplest.idStrain")
    @Mapping(target = "synonyms", source = "simplest.synonyms")
    @Mapping(target = "genus", source = "simplest.genus")
    @Mapping(target = "isTypical", source = "simplest.typical")
    @Mapping(target = "applicationArea", source = "simplest.applicationArea")
    @Mapping(target = "pathogenGroup", source = "simplest.pathogenGroup")
    CardDto toCardDto(Simplest simplest);

    @Mapping(target = "kind", source = "simplest.kindName")
    @Mapping(target = "idStrain", source = "simplest.idStrain")
    @Mapping(target = "synonyms", source = "simplest.synonyms")
    @Mapping(target = "genus", source = "simplest.genus")
    @Mapping(target = "isTypical", source = "simplest.typical")
    @Mapping(target = "applicationArea", source = "simplest.applicationArea")
    @Mapping(target = "pathogenGroup", source = "simplest.pathogenGroup")
    CardShortDto toCardShortDto(Simplest simplest);

}
