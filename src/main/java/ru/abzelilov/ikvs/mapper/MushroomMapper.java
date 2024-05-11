package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;
import ru.abzelilov.ikvs.model.Mushroom;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface MushroomMapper {

    @Mapping(target = "kindName", source = "cardDto.kind")
    @Mapping(target = "isTypical", source = "cardDto.isTypical")
    Mushroom toMushroom(CardDto cardDto);

    @Mapping(target = "kindName", source = "cardShortDto.kind")
    @Mapping(target = "idStrain", source = "cardShortDto.idStrain")
    @Mapping(target = "synonyms", source = "cardShortDto.synonyms")
    @Mapping(target = "genus", source = "cardShortDto.genus")
    @Mapping(target = "isTypical", source = "cardShortDto.isTypical")
    @Mapping(target = "applicationArea", source = "cardShortDto.applicationArea")
    @Mapping(target = "pathogenGroup", source = "cardShortDto.pathogenGroup")
    Mushroom toMushroom(CardShortDto cardShortDto);


    @Mapping(target = "kindName", source = "strainUpdateDto.kind")
    @Mapping(target = "idStrain", source = "strainUpdateDto.idStrain")
    Mushroom toMushroom(StrainUpdateDto strainUpdateDto);

    @Mapping(target = "id", source = "mushroom.id")
    @Mapping(target = "kind", source = "mushroom.kindName")
    @Mapping(target = "idStrain", source = "mushroom.idStrain")
    @Mapping(target = "synonyms", source = "mushroom.synonyms")
    @Mapping(target = "genus", source = "mushroom.genus")
    @Mapping(target = "isTypical", source = "mushroom.typical")
    @Mapping(target = "applicationArea", source = "mushroom.applicationArea")
    @Mapping(target = "pathogenGroup", source = "mushroom.pathogenGroup")
    CardDto toCardDto(Mushroom mushroom);

    @Mapping(target = "kind", source = "mushroom.kindName")
    @Mapping(target = "idStrain", source = "mushroom.idStrain")
    @Mapping(target = "synonyms", source = "mushroom.synonyms")
    @Mapping(target = "genus", source = "mushroom.genus")
    @Mapping(target = "isTypical", source = "mushroom.typical")
    @Mapping(target = "applicationArea", source = "mushroom.applicationArea")
    @Mapping(target = "pathogenGroup", source = "mushroom.pathogenGroup")
    CardShortDto toCardShortDto(Mushroom mushroom);

}
