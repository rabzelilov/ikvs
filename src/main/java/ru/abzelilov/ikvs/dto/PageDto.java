package ru.abzelilov.ikvs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Транспортный объект для отфильтрованных данных с учетом пагинации
 *
 * @param <D> тип объекта
 */
@Data
@Schema(description = "Траспортный объект для данных указанного типа с учетом пагинации")
@AllArgsConstructor
public class PageDto<D> {

    /** Общее количество записей */
    @Schema(description = "Общее количество записей")
    Long count;

    /** Данные */
    @Schema(description = "Список данных")
    List<D> data;
}
