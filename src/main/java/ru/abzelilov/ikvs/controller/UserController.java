package ru.abzelilov.ikvs.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.PageDto;
import ru.abzelilov.ikvs.filter.StrainFilterParams;
import ru.abzelilov.ikvs.service.StrainService;

import java.util.List;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/demo-controller")
public class UserController {

    private final StrainService strainService;


    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<List<CardDto>> getAllStrains() {
        return new ResponseEntity<>(strainService.getAllStrains(), HttpStatus.OK);
    }


    /**
     * Возвращает список штаммов с учетом фильтрации и пагинации
     *
     * @param departmentFilterParams параметры для фильтрации {@link StrainFilterParams}
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @PostMapping(value = "/filter")
    @Operation(summary = "Получение списка штаммов с учетом фильтрации и пагинации")
    public ResponseEntity<PageDto<CardShortDto>> getStrains(
            @RequestBody StrainFilterParams departmentFilterParams) {
        Page<CardShortDto> page = strainService.getStrains(
                PageRequest.of(departmentFilterParams.getPageNumber(),
                        departmentFilterParams.getPageSize()), departmentFilterParams);

        return new ResponseEntity<>(new PageDto<>(page.getTotalElements(), page.getContent()), HttpStatus.OK);
    }
}
