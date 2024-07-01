package ru.abzelilov.ikvs.dto.Seaweed;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.antlr.v4.runtime.misc.NotNull;
import ru.abzelilov.ikvs.dto.MushroomDto.MushroomDto;

/**
 * Транспортный объект карточки штамма
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Транспортный объект карточки штамма бактерии")
public class SeaweedUpdateDto extends SeaweedDto {

    /** Идентификатор филиала */
    @NotNull
    @Schema(description = "Идентификатор штамма")
    private Long id;
}
