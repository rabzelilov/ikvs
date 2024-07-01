package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.archaea.ArchaeaDto;
import ru.abzelilov.ikvs.dto.archaea.ArchaeaUpdateDto;
import ru.abzelilov.ikvs.model.Archaea;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ArchaeaMapper {

    @Mapping(target = "kindName", source = "archaeaDto.kind")
    Archaea toArchaea(ArchaeaDto archaeaDto);

    @Mapping(target = "kindName", source = "cardShortDto.kind")
    Archaea toArchaea(CardShortDto cardShortDto);

    @Mapping(target = "kindName", source = "archaeaUpdateDto.kind")
    Archaea toArchaea(ArchaeaUpdateDto archaeaUpdateDto);

    @Mapping(target = "kind", source = "archaea.kindName")
    ArchaeaDto toArchaeaDto(Archaea archaea);

//    @Mapping(target = "kind", source = "archaea.kindName")
//    CardShortDto toCardShortDto(Archaea archaea);

}
