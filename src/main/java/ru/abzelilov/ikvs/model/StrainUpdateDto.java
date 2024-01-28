package ru.abzelilov.ikvs.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.antlr.v4.runtime.misc.NotNull;
import ru.abzelilov.ikvs.dto.StrainAddDto;

/**
 * Транспортный объект филиала для операции редактирования
 */
@Data
@Schema(description = "Транспортный объект филиала для операции редактирования")
@EqualsAndHashCode(callSuper = true)
public class StrainUpdateDto extends StrainAddDto {

    /** Идентификатор филиала */
    @NotNull
    @Schema(description = "Идентификатор штамма")
    private Long id;
}
