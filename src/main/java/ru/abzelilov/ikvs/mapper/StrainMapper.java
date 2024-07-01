package ru.abzelilov.ikvs.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import ru.abzelilov.ikvs.dto.StrainSearchResponseDto;
import ru.abzelilov.ikvs.model.*;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN, nullValueCheckStrategy = ALWAYS)
public interface StrainMapper {

    @InheritInverseConfiguration
    StrainSearchResponseDto map(Bacteria bacteria);

    @InheritInverseConfiguration
    StrainSearchResponseDto map(Archaea archaea);

    @InheritInverseConfiguration
    StrainSearchResponseDto map(Dna dna);

    @InheritInverseConfiguration
    StrainSearchResponseDto map(Mushroom mushroom);

    @InheritInverseConfiguration
    StrainSearchResponseDto map(Seaweed seaweed);

    @InheritInverseConfiguration
    StrainSearchResponseDto map(Simplest simplest);


    default StrainSearchResponseDto map(Filter filter) {
        if (filter instanceof Bacteria) {
            return this.map((Bacteria) filter);
        } else if (filter instanceof Archaea) {
            return this.map((Archaea) filter);
        } else if (filter instanceof Dna) {
            return this.map((Dna) filter);
        } else if (filter instanceof Mushroom) {
            return this.map((Mushroom) filter);
        } else if (filter instanceof Seaweed) {
            return this.map((Seaweed) filter);
        } else if (filter instanceof Simplest) {
            return this.map((Simplest) filter);
        }

        throw new RuntimeException();
    }

}
