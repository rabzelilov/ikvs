package ru.abzelilov.ikvs.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.abzelilov.ikvs.dto.BacteriaUpdateDto;
import ru.abzelilov.ikvs.dto.Seaweed.SeaweedUpdateDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.dto.StrainFullAddDto;
import ru.abzelilov.ikvs.dto.simplest.SimplestDto;
import ru.abzelilov.ikvs.dto.simplest.SimplestUpdateDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.model.Simplest;
import ru.abzelilov.ikvs.service.SimplestService;

import java.util.List;
import java.util.Optional;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/simplest")
public class SimplestController {

    private final SimplestService simplestService;


    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<List<SimplestDto>> getAllStrains() {
        return new ResponseEntity<>(simplestService.getAllSimplest(), HttpStatus.OK);
    }

    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping("/byId/{id}")
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<Optional<SimplestDto>> getStrainById(@PathVariable Long id) {
        return new ResponseEntity<>(simplestService.getSimplestById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/search")
    public Page<Simplest> search(@RequestBody StrainSearchRequest request) {
        return simplestService.searchSimplest(request);
    }

    //    /**
//     * Сохраняет штамм
//     *
//     * @param strainAddDto траспортный объект {@link StrainAddDto}
//     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
//     */
//    @PostMapping
//    @Operation(summary = "Создание штамма")
//    @PreAuthorize("hasAuthority('admin:create')")
//    @CrossOrigin
//    public ResponseEntity<CardShortDto> saveStrain(@RequestBody StrainAddDto strainAddDto) {
//        return new ResponseEntity<>(
//                dnaService.saveDna(strainAddDto), HttpStatus.OK);
//    }

    /**
     * Сохраняет штамм
     *
     * @param simplestDto траспортный объект {@link StrainAddDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PostMapping("/saveSimplest")
    @Operation(summary = "Создание штамма")
    @PreAuthorize("hasAuthority('admin:create')")
    @CrossOrigin
    public ResponseEntity<SimplestDto> saveStrain(@RequestBody SimplestDto simplestDto) {
        return new ResponseEntity<>(
                simplestService.saveSimplest(simplestDto), HttpStatus.OK);
    }

    /**
     * Обновляет штамм
     *
     * @param simplestUpdateDto траспортный объект {@link BacteriaUpdateDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PutMapping("/updateSimplest")
    @Operation(summary = "Редактирование штамма")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<SimplestDto> updateStrain(@RequestBody SimplestUpdateDto simplestUpdateDto) {
        return new ResponseEntity<>(
                simplestService.updateSimplest(simplestUpdateDto), HttpStatus.OK);
    }

    /**
     * Удаляет штамм.
     * В случае успешного ответа метод возвращает НТТР 200 OK.
     *
     * @param id идентификатор штамма
     */
    @DeleteMapping(value = "deleteSimplest/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteStrain(@PathVariable("id") Long id) {
        simplestService.deleteSimplest(id);
    }
}
