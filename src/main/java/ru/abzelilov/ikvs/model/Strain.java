package ru.abzelilov.ikvs.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * Штамм
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "Strain")
public class Strain {

    /** Идентификатор штамма */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "idStrain", nullable = false)
    private Long idStrain;

    /** Видовое название */
    @Column(name = "kind")
    private String kindName;

    /** Синонимы видового названия */
    @Column(name = "synonyms")
    private String synonyms;

    /** Род */
    @Column(name = "genus")
    private String genus;

    /** Типовой/нетиповой штамм */
    @Column(name = "isTypical")
    private boolean isTypical;

    /** Область приминения */
    @Column(name = "application_area")
    private String applicationArea;

    /** Группа патогенности */
    @Column(name = "pathogen_group")
    private String pathogenGroup;

    /** Название штамма */
    @Schema(description = "Род")
    private String name;

    /** Консорциум */
    @Column(name = "consortium")
    private Boolean consortium;

    /** Название коллекции */
    @Column(name = "name_collection")
    private String nameCollection;

    /** Семейство */
    @Column(name = "family")
    private String family;

    /** Фила */
    @Column(name = "fila")
    private String fila;

    /** Источник выделения */
    @Column(name = "selection_source")
    private String selectionSource;

    /** Хозяин */
    @Column(name = "owner")
    private String owner;

    /** GPS координаты */
    @Column(name = "coordinates")
    private String coordinates;

    /** Страна выделения */
    @Column(name = "sourceCountry")
    private String sourceCountry;

    /** Генетический маркер */
    @Column(name = "marker_genetic")
    private String markerGenetic;

    /** Номер геномной сборки */
    @Column(name = "number_genetic_build")
    private String numberGeneticBuild;

    /** Возможность приобритения */
    @Column(name = "possibility_acquiring")
    private String possibilityAcquiring;

    /** Публикации */
    @Column(name = "publications")
    private String publications;
}
