package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;
import ru.abzelilov.ikvs.model.Archaea;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ArchaeaMapper {

    @Mapping(target = "kindName", source = "cardDto.kind")
    @Mapping(target = "isTypical", source = "cardDto.isTypical")
    Archaea toArchaea(CardDto cardDto);

    @Mapping(target = "kindName", source = "cardShortDto.kind")
    @Mapping(target = "idStrain", source = "cardShortDto.idStrain")
    @Mapping(target = "synonyms", source = "cardShortDto.synonyms")
    @Mapping(target = "genus", source = "cardShortDto.genus")
    @Mapping(target = "isTypical", source = "cardShortDto.isTypical")
    @Mapping(target = "applicationArea", source = "cardShortDto.applicationArea")
    @Mapping(target = "pathogenGroup", source = "cardShortDto.pathogenGroup")
    Archaea toArchaea(CardShortDto cardShortDto);

    @Mapping(target = "kindName", source = "strainUpdateDto.kind")
    @Mapping(target = "idStrain", source = "strainUpdateDto.idStrain")
    Archaea toArchaea(StrainUpdateDto strainUpdateDto);

    @Mapping(target = "id", source = "archaea.id")
    @Mapping(target = "kind", source = "archaea.kindName")
    @Mapping(target = "idStrain", source = "archaea.idStrain")
    @Mapping(target = "synonyms", source = "archaea.synonyms")
    @Mapping(target = "genus", source = "archaea.genus")
    @Mapping(target = "isTypical", source = "archaea.typical")
    @Mapping(target = "applicationArea", source = "archaea.applicationArea")
    @Mapping(target = "pathogenGroup", source = "archaea.pathogenGroup")
    CardDto toCardDto(Archaea archaea);

    @Mapping(target = "kind", source = "archaea.kindName")
    @Mapping(target = "idStrain", source = "archaea.idStrain")
    @Mapping(target = "synonyms", source = "archaea.synonyms")
    @Mapping(target = "genus", source = "archaea.genus")
    @Mapping(target = "isTypical", source = "archaea.typical")
    @Mapping(target = "applicationArea", source = "archaea.applicationArea")
    @Mapping(target = "pathogenGroup", source = "archaea.pathogenGroup")
    CardShortDto toCardShortDto(Archaea archaea);

}
