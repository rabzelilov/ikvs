package ru.abzelilov.ikvs.dto.dna;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.antlr.v4.runtime.misc.NotNull;
import ru.abzelilov.ikvs.dto.bacteria.BacteriaDto;

/**
 * Транспортный объект карточки штамма
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Транспортный объект карточки штамма")
public class DnaUpdateDto extends DnaDto {

    /** Идентификатор филиала */
    @NotNull
    @Schema(description = "Идентификатор штамма")
    private Long id;
}
