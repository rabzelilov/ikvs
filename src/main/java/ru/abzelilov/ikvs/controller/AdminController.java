package ru.abzelilov.ikvs.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.dto.StrainFullAddDto;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;
import ru.abzelilov.ikvs.service.BacteriaService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final BacteriaService bacteriaService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }

    /**
     * Сохраняет штамм
     *
     * @param strainAddDto траспортный объект {@link StrainAddDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PostMapping
    @Operation(summary = "Создание штамма")
    @PreAuthorize("hasAuthority('admin:create')")
    @CrossOrigin
    public ResponseEntity<CardShortDto> saveStrain(@RequestBody StrainAddDto strainAddDto) {
        return new ResponseEntity<>(
                bacteriaService.saveBacteria(strainAddDto), HttpStatus.OK);
    }

    /**
     * Сохраняет штамм
     *
     * @param strainFullAddDto траспортный объект {@link StrainAddDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PostMapping("/saveStrain")
    @Operation(summary = "Создание штамма")
    @PreAuthorize("hasAuthority('admin:create')")
    @CrossOrigin
    public ResponseEntity<CardDto> saveStrain(@RequestBody StrainFullAddDto strainFullAddDto) {
        return new ResponseEntity<>(
                bacteriaService.saveBacteria(strainFullAddDto), HttpStatus.OK);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект {@link StrainUpdateDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PutMapping
    @Operation(summary = "Редактирование штамма")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<CardDto> updateStrain(@RequestBody StrainUpdateDto strainUpdateDto) {
        return new ResponseEntity<>(
                bacteriaService.updateBacteria(strainUpdateDto), HttpStatus.OK);
    }

    /**
     * Удаляет штамм.
     * В случае успешного ответа метод возвращает НТТР 200 OK.
     *
     * @param id идентификатор штамма
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteStrain(@PathVariable("id") Long id) {
        bacteriaService.deleteBacteria(id);
    }
}
