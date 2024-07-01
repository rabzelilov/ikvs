package ru.abzelilov.ikvs.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.abzelilov.ikvs.dto.*;
import ru.abzelilov.ikvs.dto.bacteria.BacteriaDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.model.Bacteria;
import ru.abzelilov.ikvs.service.BacteriaService;

import java.util.List;
import java.util.Optional;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bacteria")
@CrossOrigin
public class BacteriaController {

    private final BacteriaService bacteriaService;


    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<List<BacteriaDto>> getAllStrains() {
        return new ResponseEntity<>(bacteriaService.getAllBacteria(), HttpStatus.OK);
    }

    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping("/byId/{id}")
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<Optional<BacteriaDto>> getStrainById(@PathVariable Long id) {
        return new ResponseEntity<>(bacteriaService.getBacteriaById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/search")
    public Page<Bacteria> search(@RequestBody StrainSearchRequest request) {
        return bacteriaService.searchBacteria(request);
    }

    /**
     * Сохраняет штамм
     *
     * @param bacteriaDto траспортный объект {@link StrainAddDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PostMapping("/saveBacteria")
    @Operation(summary = "Создание штамма")
    @PreAuthorize("hasAuthority('admin:create')")
    @CrossOrigin
    public ResponseEntity<BacteriaDto> saveStrain(@RequestBody BacteriaDto bacteriaDto) {
        return new ResponseEntity<>(
                bacteriaService.saveBacteria(bacteriaDto), HttpStatus.OK);
    }

    /**
     * Обновляет штамм
     *
     * @param bacteriaUpdateDto траспортный объект {@link BacteriaUpdateDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PutMapping("/updateBacteria")
    @Operation(summary = "Редактирование штамма")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<BacteriaDto> updateStrain(@RequestBody BacteriaUpdateDto bacteriaUpdateDto) {
        return new ResponseEntity<>(
                bacteriaService.updateBacteria(bacteriaUpdateDto), HttpStatus.OK);
    }

    /**
     * Удаляет штамм.
     * В случае успешного ответа метод возвращает НТТР 200 OK.
     *
     * @param id идентификатор штамма
     */
    @DeleteMapping(value = "deleteBacteria/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteStrain(@PathVariable("id") Long id) {
        bacteriaService.deleteBacteria(id);
    }
}
