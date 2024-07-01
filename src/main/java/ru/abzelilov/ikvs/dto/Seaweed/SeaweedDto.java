package ru.abzelilov.ikvs.dto.Seaweed;

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
public class SeaweedDto extends CardShortDto {

    /** Номера ампул */
    private String ampouleNumbers;

    /** Дата лиофилизации */
    private LocalDateTime dateOfLyophilization;

    /** Номера криопробирок */
    private String cryoprobeNumbers;

    /** Дата консервации */
    private LocalDateTime dateOfConservation;

    /** Видовое название */
    private String kindName;

    /** Синонимы видового названия */
    private String synonyms;

    /** Субстрат, из которого выделена культура */
    private String substrate;

    /** Аксеничность */
    private String asceticism;

    /** Типовой/нетиповой штамм */
    private boolean isTypical;

    /** Область приминения */
    private String applicationArea;

    /** Группа патогенности */
    private String pathogenGroup;

    /** Название штамма */
    private String name;

    /** Семейство */
    private String family;

    /** Класс */
    private String clazz;

    /** Таксон */
    private String taxon;

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
    private String publications;
}
