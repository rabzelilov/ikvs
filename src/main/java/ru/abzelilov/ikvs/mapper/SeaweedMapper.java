package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.model.Seaweed;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface SeaweedMapper {

    @Mapping(target = "kindName", source = "cardDto.kind")
    @Mapping(target = "isTypical", source = "cardDto.isTypical")
    Seaweed toSeaweed(CardDto cardDto);


    @Mapping(target = "kindName", source = "cardShortDto.kind")
    @Mapping(target = "idStrain", source = "cardShortDto.idStrain")
    @Mapping(target = "synonyms", source = "cardShortDto.synonyms")
    @Mapping(target = "genus", source = "cardShortDto.genus")
    @Mapping(target = "isTypical", source = "cardShortDto.isTypical")
    @Mapping(target = "applicationArea", source = "cardShortDto.applicationArea")
    @Mapping(target = "pathogenGroup", source = "cardShortDto.pathogenGroup")
    Seaweed toSeaweed(CardShortDto cardShortDto);

    @Mapping(target = "kindName", source = "strainUpdateDto.kind")
    @Mapping(target = "idStrain", source = "strainUpdateDto.idStrain")
    Seaweed toSeaweed(StrainUpdateDto strainUpdateDto);

    @Mapping(target = "id", source = "seaweed.id")
    @Mapping(target = "kind", source = "seaweed.kindName")
    @Mapping(target = "idStrain", source = "seaweed.idStrain")
    @Mapping(target = "synonyms", source = "seaweed.synonyms")
    @Mapping(target = "genus", source = "seaweed.genus")
    @Mapping(target = "isTypical", source = "seaweed.typical")
    @Mapping(target = "applicationArea", source = "seaweed.applicationArea")
    @Mapping(target = "pathogenGroup", source = "seaweed.pathogenGroup")
    CardDto toCardDto(Seaweed seaweed);

    @Mapping(target = "kind", source = "seaweed.kindName")
    @Mapping(target = "idStrain", source = "seaweed.idStrain")
    @Mapping(target = "synonyms", source = "seaweed.synonyms")
    @Mapping(target = "genus", source = "seaweed.genus")
    @Mapping(target = "isTypical", source = "seaweed.typical")
    @Mapping(target = "applicationArea", source = "seaweed.applicationArea")
    @Mapping(target = "pathogenGroup", source = "seaweed.pathogenGroup")
    CardShortDto toCardShortDto(Seaweed seaweed);

}
