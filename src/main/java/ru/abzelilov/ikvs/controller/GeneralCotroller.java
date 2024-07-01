package ru.abzelilov.ikvs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.abzelilov.ikvs.dto.StrainSearchResponseDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.service.SearchService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/general")
public class GeneralCotroller {

    private final SearchService searchService;

    @CrossOrigin
    @PostMapping(value = "/search")
    public Page<StrainSearchResponseDto> search(@RequestBody StrainSearchRequest request) {
        return searchService.search(request);
    }
}
