package ru.abzelilov.ikvs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Транспортный объект штамма для быстрого создания")
public class StrainAddDto {

    /** Идентификатор штамма */
    @Schema(description = "Идентификатор штамма")
    private Long idStrain;

    /** Видовое название */
    @Schema(description = "Видовое название")
    private String kind;
}
