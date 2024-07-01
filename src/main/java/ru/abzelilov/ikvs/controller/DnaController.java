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
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.dto.dna.DnaDto;
import ru.abzelilov.ikvs.dto.dna.DnaUpdateDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.model.Dna;
import ru.abzelilov.ikvs.service.DnaService;

import java.util.List;
import java.util.Optional;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/dna")
public class DnaController {

    private final DnaService dnaService;


    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<List<DnaDto>> getAllStrains() {
        return new ResponseEntity<>(dnaService.getAllDna(), HttpStatus.OK);
    }

    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping("/byId/{id}")
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<Optional<DnaDto>> getStrainById(@PathVariable Long id) {
        return new ResponseEntity<>(dnaService.getDnaById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/search")
    public Page<Dna> search(@RequestBody StrainSearchRequest request) {
        return dnaService.searchDna(request);
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
     * @param dnaDto траспортный объект {@link StrainAddDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PostMapping("/saveDna")
    @Operation(summary = "Создание штамма")
    @PreAuthorize("hasAuthority('admin:create')")
    @CrossOrigin
    public ResponseEntity<DnaDto> saveStrain(@RequestBody DnaDto dnaDto) {
        return new ResponseEntity<>(
                dnaService.saveDna(dnaDto), HttpStatus.OK);
    }

    /**
     * Обновляет штамм
     *
     * @param dnaUpdateDto траспортный объект {@link BacteriaUpdateDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PutMapping("/updateDna")
    @Operation(summary = "Редактирование штамма")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<DnaDto> updateStrain(@RequestBody DnaUpdateDto dnaUpdateDto) {
        return new ResponseEntity<>(
                dnaService.updateDna(dnaUpdateDto), HttpStatus.OK);
    }

    /**
     * Удаляет штамм.
     * В случае успешного ответа метод возвращает НТТР 200 OK.
     *
     * @param id идентификатор штамма
     */
    @DeleteMapping(value = "deleteDna/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteStrain(@PathVariable("id") Long id) {
        dnaService.deleteDna(id);
    }
    
    
}
