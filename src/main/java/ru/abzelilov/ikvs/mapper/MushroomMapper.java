package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.MushroomDto.MushroomDto;
import ru.abzelilov.ikvs.dto.MushroomDto.MushroomUpdateDto;
import ru.abzelilov.ikvs.model.Mushroom;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface MushroomMapper {

    @Mapping(target = "kindName", source = "MushroomDto.kind")
    Mushroom toMushroom(MushroomDto MushroomDto);


    @Mapping(target = "kindName", source = "mushroomUpdateDto.kind")
    Mushroom toMushroom(MushroomUpdateDto mushroomUpdateDto);

    @Mapping(target = "kind", source = "mushroom.kindName")
    MushroomDto toMushroomDto(Mushroom mushroom);

}
