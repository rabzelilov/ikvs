package ru.abzelilov.ikvs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Транспортный объект карточки штамма
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Транспортный объект карточки штамма")
public class CardDto extends CardShortDto {

    /** Название штамма */
    @Schema(description = "Род")
    private String name;

    /** Консорциум */
    @Schema(description = "Консорциум")
    private Boolean consortium;

    /** Название коллекции */
    @Schema(description = "Название коллекции")
    private String nameCollection;

    /** Семейство */
    @Schema(description = "Семейство")
    private String family;

    /** Фила */
    @Schema(description = "Фила")
    private String fila;

    /** Источник выделения */
    @Schema(description = "Источник выделения")
    private String selectionSource;

    /** Хозяин */
    @Schema(description = "Хозяин")
    private String owner;

    /** GPS координаты */
    @Schema(description = "GPS координаты")
    private String coordinates;

    /** Страна выделения */
    @Schema(description = "Страна выделения")
    private String sourceCountry;

    /** Генетический маркер */
    @Schema(description = "Генетический маркер")
    private String markerGenetic;

    /** Номер геномной сборки */
    @Schema(description = "Номер геномной сборки")
    private String numberGeneticBuild;

    /** Возможность приобритения */
    @Schema(description = "Возможность приобритения")
    private String possibilityAcquiring;

    /** Публикации */
    @Schema(description = "Публикации")
    private String publications;
}
