package ru.abzelilov.ikvs.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.model.Bacteria;
import ru.abzelilov.ikvs.service.BacteriaService;

import java.util.List;
import java.util.Optional;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/bacteria")
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
    public ResponseEntity<List<CardDto>> getAllStrains() {
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
    public ResponseEntity<Optional<CardDto>> getStrainById(@PathVariable Long id) {
        return new ResponseEntity<>(bacteriaService.getBacteriaById(id), HttpStatus.OK);
    }


    @PostMapping(value = "/search")
    public Page<Bacteria> search(@RequestBody StrainSearchRequest request) {
        return bacteriaService.searchBacteria(request);
    }
}
