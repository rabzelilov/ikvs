package ru.abzelilov.ikvs.dto.MushroomDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.antlr.v4.runtime.misc.NotNull;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.model.Mushroom;

import java.time.LocalDateTime;

/**
 * Транспортный объект карточки штамма
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Транспортный объект карточки штамма бактерии")
public class MushroomUpdateDto extends MushroomDto {

    /** Идентификатор филиала */
    @NotNull
    @Schema(description = "Идентификатор штамма")
    private Long id;
}
