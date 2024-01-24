package ru.abzelilov.ikvs.filter.common;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Параметры для пагинации
 */
@Data
@Schema(description = "Параметры для пагинации")
public class PageFilterParams {

    /** Номер страницы */
    @Schema(description = "Номер страницы")
    private Integer pageNumber;

    /** Количество записей на странице */
    @Schema(description = "Количество записей на странице")
    private Integer pageSize;

    /**
     * Возвращает номер страницы
     *
     * @return номер страницы
     */
    @JsonAlias({"page", "pageNumber"})
    public Integer getPageNumber() {
        return pageNumber;
    }
}
