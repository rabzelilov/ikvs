package ru.abzelilov.ikvs.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * ДНК
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "Dna")
public class Dna implements Serializable {

    @Serial
    private static final long serialVersionUID = -1730538653948604633L;

    /** Идентификатор штамма */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "idStrain", nullable = false)
    private Long idStrain;

    /** Досттуп для внешних пользователей */
    @Column(name = "isVisible")
    private boolean isVisible;

    /** Номера криопробирок */
    @Column(name = "cryoprobeNumbers")
    private String cryoprobeNumbers;

    /** Дата консервации */
    @Column(name = "dateOfConservation")
    private LocalDateTime dateOfConservation;

    /** Дата отбора образца */
    @Column(name = "substrate")
    private LocalDateTime dateOfSelection;

    /** Географическое название места отбора */
    @Column(name = "geographyOfSelection")
    private String geographyOfSelection;

    /** Название штамма */
    @Schema(description = "Название")
    private String name;

    /** Название коллекции */
    @Column(name = "name_collection")
    private String nameCollection;

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
