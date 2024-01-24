package ru.abzelilov.ikvs.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.model.StrainUpdateDto;
import ru.abzelilov.ikvs.service.StrainService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final StrainService strainService;

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET:: admin controller";
    }

    /**
     * Сохраняет штамм
     *
     * @param departmentAddDto траспортный объект {@link StrainAddDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PostMapping
    @Operation(summary = "Создание штамма")
    @PreAuthorize("hasAuthority('admin:create')")
    @CrossOrigin
    public ResponseEntity<CardShortDto> saveStrain(@RequestBody StrainAddDto departmentAddDto) {
        return new ResponseEntity<>(
                strainService.saveStrain(departmentAddDto), HttpStatus.OK);
    }

    /**
     * Обновляет штамм
     *
     * @param departmentUpdateDto траспортный объект {@link StrainUpdateDto}
     * @return ответ на запрос, в случае успешного ответа, метод возвращает штамм и НТТР 200 OK
     */
    @PutMapping
    @Operation(summary = "Редактирование штамма")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<CardShortDto> updateStrain(@RequestBody StrainUpdateDto departmentUpdateDto) {
        return new ResponseEntity<>(
                strainService.updateStrain(departmentUpdateDto), HttpStatus.OK);
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
        strainService.deleteStrain(id);
    }
}
