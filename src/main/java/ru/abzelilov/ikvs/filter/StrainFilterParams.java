package ru.abzelilov.ikvs.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.abzelilov.ikvs.filter.common.PageFilterParams;

/**
 * Параметры для фильтрации штаммов
 */
@Data
@Schema(description = "Параметры для фильтрации штаммов")
@EqualsAndHashCode(callSuper = true)
public class StrainFilterParams extends PageFilterParams {

    /** Наименование */
    @Schema(description = "Наименование")
    private String name;

    /** Консорциум */
    @Schema(description = "Консорциум")
    private Boolean consortium;

    /** Название коллекции */
    @Schema(description = "Название коллекции")
    private String nameCollection;
}
