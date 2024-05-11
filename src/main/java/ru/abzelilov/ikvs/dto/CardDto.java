package ru.abzelilov.ikvs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Транспортный объект карточки штамма
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Транспортный объект карточки штамма")
public class CardDto extends CardShortDto {


    /** Номера ампул */
    @Schema(description = "ampouleNumbers")
    private String ampouleNumbers;

    /** Дата лиофилизации */
    @Schema(description = "dateOfLyophilization")
    private LocalDateTime dateOfLyophilization;

    /** Номера криопробирок */
    @Schema(description = "cryoprobeNumbers")
    private String cryoprobeNumbers;

    /** Дата консервации */
    @Schema(description = "dateOfConservation")
    private LocalDateTime dateOfConservation;

    /** Название штамма */
    @Schema(description = "Название штамма")
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
