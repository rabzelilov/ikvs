package ru.abzelilov.ikvs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class StrainSearchResponseDto {
    private Long id;
    private String genus;
    private Boolean consortium;
    private String nameCollection;
    private Long idStrain;
    private boolean isVisible;
    private String ampouleNumbers;
    private LocalDateTime dateOfLyophilization;
    private String cryoprobeNumbers;
    private LocalDateTime dateOfConservation;
    private String kindName;
    private String synonyms;
    private String substrate;
    private String asceticism;
    private boolean isTypical;
    private String applicationArea;
    private String pathogenGroup;
    private String name;
    private String family;
    private String fila;
    private String selectionSource;
    private String owner;
    private String coordinates;
    private String clazz;
    private String taxon;
    private String geographyOfSelection;
    private LocalDateTime dateOfSelection;
}
