package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.bacteria.BacteriaDto;
import ru.abzelilov.ikvs.model.Bacteria;
import ru.abzelilov.ikvs.dto.BacteriaUpdateDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface BacteriaMapper {

    @Mapping(target = "kindName", source = "cardDto.kind")
    Bacteria toBacteria(CardDto cardDto);

    @Mapping(target = "kindName", source = "bacteriaDto.kind")
    Bacteria toBacteria(BacteriaDto bacteriaDto);


    @Mapping(target = "kindName", source = "bacteriaUpdateDto.kind")
    Bacteria toBacteria(BacteriaUpdateDto bacteriaUpdateDto);

    @Mapping(target = "kind", source = "bacteria.kindName")
    BacteriaDto toBacteriaDto(Bacteria bacteria);

//    @Mapping(target = "kind", source = "bacteria.kindName")
//    CardShortDto toCardShortDto(Bacteria bacteria);

}
