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
import ru.abzelilov.ikvs.dto.Seaweed.SeaweedDto;
import ru.abzelilov.ikvs.dto.Seaweed.SeaweedUpdateDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.dto.StrainFullAddDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.model.Seaweed;
import ru.abzelilov.ikvs.service.SeaweedService;

import java.util.List;
import java.util.Optional;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seaweed")
public class SeaweedController {

    private final SeaweedService seaweedService;


    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<List<SeaweedDto>> getAllStrains() {
        return new ResponseEntity<>(seaweedService.getAllSeaweed(), HttpStatus.OK);
    }

    /**
     * Возвращает список всех штаммаов
     *
     * @return ответ на запрос, в случае успешного ответа метод возвращает список штаммов и НТТР 200 OK
     */
    @CrossOrigin
    @GetMapping("/byId/{id}")
    @Operation(summary = "Получение списка всех штаммов")
    public ResponseEntity<Optional<SeaweedDto>> getStrainById(@PathVariable Long id) {
        return new ResponseEntity<>(seaweedService.getSeaweedById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/search")
    public Page<Seaweed> search(@RequestBody StrainSearchRequest request) {
        return seaweedService.searchSeaweed(request);
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
     * @param seaweedDto траспортный объект {@link StrainAddDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PostMapping("/saveSeaweed")
    @Operation(summary = "Создание штамма")
    @PreAuthorize("hasAuthority('admin:create')")
    @CrossOrigin
    public ResponseEntity<SeaweedDto> saveStrain(@RequestBody SeaweedDto seaweedDto) {
        return new ResponseEntity<>(
                seaweedService.saveSeaweed(seaweedDto), HttpStatus.OK);
    }

    /**
     * Обновляет штамм
     *
     * @param seaweedUpdateDto траспортный объект {@link BacteriaUpdateDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PutMapping("/updateSeaweed")
    @Operation(summary = "Редактирование штамма")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<SeaweedDto> updateStrain(@RequestBody SeaweedUpdateDto seaweedUpdateDto) {
        return new ResponseEntity<>(
                seaweedService.updateSeaweed(seaweedUpdateDto), HttpStatus.OK);
    }

    /**
     * Удаляет штамм.
     * В случае успешного ответа метод возвращает НТТР 200 OK.
     *
     * @param id идентификатор штамма
     */
    @DeleteMapping(value = "deleteSeaweed/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin:delete')")
    public void deleteStrain(@PathVariable("id") Long id) {
        seaweedService.deleteSeaweed(id);
    }
}
