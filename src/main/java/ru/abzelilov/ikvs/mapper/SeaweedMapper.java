package ru.abzelilov.ikvs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.abzelilov.ikvs.dto.Seaweed.SeaweedDto;
import ru.abzelilov.ikvs.dto.Seaweed.SeaweedUpdateDto;
import ru.abzelilov.ikvs.model.Seaweed;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface SeaweedMapper {

    @Mapping(target = "kindName", source = "SeaweedDto.kind")
    Seaweed toSeaweed(SeaweedDto SeaweedDto);

    @Mapping(target = "kindName", source = "seaweedUpdateDto.kind")
    Seaweed toSeaweed(SeaweedUpdateDto seaweedUpdateDto);

    @Mapping(target = "kind", source = "seaweed.kindName")
    SeaweedDto toSeaweedDto(Seaweed seaweed);

}
