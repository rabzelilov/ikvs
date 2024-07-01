package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.simplest.SimplestDto;
import ru.abzelilov.ikvs.dto.simplest.SimplestUpdateDto;
import ru.abzelilov.ikvs.model.Simplest;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface SimplestMapper {

    @Mapping(target = "kindName", source = "SimplestDto.kind")
    Simplest toSimplest(SimplestDto SimplestDto);

    @Mapping(target = "kindName", source = "simplestUpdateDto.kind")
    Simplest toSimplest(SimplestUpdateDto simplestUpdateDto);

    @Mapping(target = "kind", source = "simplest.kindName")
    SimplestDto toSimplestDto(Simplest simplest);


}
