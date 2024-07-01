package ru.abzelilov.ikvs.dto.MushroomDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.abzelilov.ikvs.dto.CardShortDto;

import java.time.LocalDateTime;

/**
 * Транспортный объект карточки штамма
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Транспортный объект карточки штамма бактерии")
public class MushroomDto extends CardShortDto {

    /** Номера ампул */
    private String ampouleNumbers;

    /** Дата лиофилизации */
    private LocalDateTime dateOfLyophilization;

    /** Номера криопробирок */
    private String cryoprobeNumbers;

    /** Дата консервации */
    private LocalDateTime dateOfConservation;

    /** Субстрат, из которого выделена культура */
    private String substrate;

    /** Аскетичность */
    private String asceticism;

    /** Название штамма */
    private String name;

    /** Видовое название */
    private String kind;

    /** Семейство */
    private String family;

    /** Фила */
    private String fila;

    /** Источник выделения */
    private String selectionSource;

    /** Хозяин */
    private String owner;

    /** GPS координаты */
    private String coordinates;

    /** Страна выделения */
    private String sourceCountry;

    /** Генетический маркер */
    private String markerGenetic;

    /** Номер геномной сборки */
    private String numberGeneticBuild;

    /** Возможность приобритения */
    private String possibilityAcquiring;

    /** Публикации */
    @Column(name = "publications")
    private String publications;
}
