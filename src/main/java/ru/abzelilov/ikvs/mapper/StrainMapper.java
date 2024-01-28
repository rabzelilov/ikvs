package ru.abzelilov.ikvs.mapper;

import io.swagger.v3.oas.annotations.media.Schema;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.model.Strain;
import ru.abzelilov.ikvs.model.StrainUpdateDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface StrainMapper {

    Strain toStrain(CardDto cardDto);


    @Mapping(target = "kindName", source = "cardShortDto.kind")
    @Mapping(target = "idStrain", source = "cardShortDto.idStrain")
    @Mapping(target = "synonyms", source = "cardShortDto.synonyms")
    @Mapping(target = "genus", source = "cardShortDto.genus")
    @Mapping(target = "typical", source = "cardShortDto.isTypical")
    @Mapping(target = "applicationArea", source = "cardShortDto.applicationArea")
    @Mapping(target = "pathogenGroup", source = "cardShortDto.pathogenGroup")
    Strain toStrain(CardShortDto cardShortDto);

    @Mapping(target = "kindName", source = "strainUpdateDto.kind")
    @Mapping(target = "idStrain", source = "strainUpdateDto.idStrain")
    Strain toStrain(StrainUpdateDto strainUpdateDto);


    @Mapping(target = "kind", source = "strain.kindName")
    @Mapping(target = "idStrain", source = "strain.idStrain")
    @Mapping(target = "synonyms", source = "strain.synonyms")
    @Mapping(target = "genus", source = "strain.genus")
    @Mapping(target = "isTypical", source = "strain.typical")
    @Mapping(target = "applicationArea", source = "strain.applicationArea")
    @Mapping(target = "pathogenGroup", source = "strain.pathogenGroup")
    CardDto toCardDto(Strain strain);

    @Mapping(target = "kind", source = "strain.kindName")
    @Mapping(target = "idStrain", source = "strain.idStrain")
    @Mapping(target = "synonyms", source = "strain.synonyms")
    @Mapping(target = "genus", source = "strain.genus")
    @Mapping(target = "isTypical", source = "strain.typical")
    @Mapping(target = "applicationArea", source = "strain.applicationArea")
    @Mapping(target = "pathogenGroup", source = "strain.pathogenGroup")
    CardShortDto toCardShortDto(Strain strain);

}
