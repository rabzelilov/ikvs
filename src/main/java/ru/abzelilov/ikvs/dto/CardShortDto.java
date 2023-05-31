package ru.abzelilov.ikvs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Транспортный объект краткой карточки штамма
 */
@Data
@Schema(description = "Транспортный объект краткой карточки штамма")
public class CardShortDto {

    /** Идентификатор штамма */
    @Schema(description = "Идентификатор штамма")
    private Long id;

    /** Видовое название */
    @Schema(description = "Видовое название")
    private String kind;

    /** Синонимы видового названия */
    @Schema(description = "Синонимы видового названия")
    private String synonyms;

    /** Род */
    @Schema(description = "Род")
    private String genus;

    /** Типовой/нетиповой штамм */
    @Schema(description = "Типовой/нетиповой штамм")
    private Boolean isTypical;

    /** Область приминения */
    @Schema(description = "Область приминения")
    private String applicationArea;

    /** Группа патогенности */
    @Schema(description = "Группа патогенности")
    private String pathogenGroup;
}
