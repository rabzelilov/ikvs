package ru.abzelilov.ikvs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "Транспортный объект штамма для быстрого создания")
@EqualsAndHashCode(callSuper = true)
public class StrainFullAddDto extends CardDto{

}
