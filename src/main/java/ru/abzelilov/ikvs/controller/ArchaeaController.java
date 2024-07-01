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
import ru.abzelilov.ikvs.dto.archaea.ArchaeaDto;
import ru.abzelilov.ikvs.dto.archaea.ArchaeaUpdateDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.model.Archaea;
import ru.abzelilov.ikvs.service.ArchaeaService;

import java.util.List;
import java.util.Optional;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/archaea")
public class ArchaeaController {

    private final ArchaeaService archaeaService;


    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<List<ArchaeaDto>> getAllStrains() {
        return new ResponseEntity<>(archaeaService.getAllArchaea(), HttpStatus.OK);
    }

    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping("/byId/{id}")
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<Optional<ArchaeaDto>> getStrainById(@PathVariable Long id) {
        return new ResponseEntity<>(archaeaService.getArchaeaById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/search")
    public Page<Archaea> search(@RequestBody StrainSearchRequest request) {
        return archaeaService.searchArchaea(request);
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
//                archaeaService.saveArchaea(strainAddDto), HttpStatus.OK);
//    }

    /**
     * Сохраняет штамм
     *
     * @param archaeaDto траспортный объект {@link StrainAddDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PostMapping("/saveArchaea")
    @Operation(summary = "Создание штамма")
    @PreAuthorize("hasAuthority('admin:create')")
    @CrossOrigin
    public ResponseEntity<ArchaeaDto> saveStrain(@RequestBody ArchaeaDto archaeaDto) {
        return new ResponseEntity<>(
                archaeaService.saveArchaea(archaeaDto), HttpStatus.OK);
    }

    /**
     * Обновляет штамм
     *
     * @param archaeaUpdateDto траспортный объект {@link BacteriaUpdateDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PutMapping("/updateArchaea")
    @Operation(summary = "Редактирование штамма")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<ArchaeaDto> updateStrain(@RequestBody ArchaeaUpdateDto archaeaUpdateDto) {
        return new ResponseEntity<>(
                archaeaService.updateArchaea(archaeaUpdateDto), HttpStatus.OK);
    }

    /**
     * Удаляет штамм.
     * В случае успешного ответа метод возвращает НТТР 200 OK.
     *
     * @param id идентификатор штамма
     */
    @DeleteMapping(value = "deleteArchaea/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteStrain(@PathVariable("id") Long id) {
        archaeaService.deleteArchaea(id);
    }
}
