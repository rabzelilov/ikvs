package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.StrainSearchResponseDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.filter.common.StrainSearchSpecification;
import ru.abzelilov.ikvs.mapper.StrainMapper;
import ru.abzelilov.ikvs.model.Filter;
import ru.abzelilov.ikvs.repository.StrainRepository;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final StrainRepository strainRepository;
    private final StrainMapper strainMapper;

    public Page<StrainSearchResponseDto> search(StrainSearchRequest request) {
        StrainSearchSpecification<Filter> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());

        return strainRepository.findAll(specification, pageable)
                .map(strainMapper::map);
    }
}
