package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.BacteriaUpdateDto;
import ru.abzelilov.ikvs.dto.bacteria.BacteriaDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.filter.common.StrainSearchSpecification;
import ru.abzelilov.ikvs.mapper.BacteriaMapper;
import ru.abzelilov.ikvs.model.Bacteria;
import ru.abzelilov.ikvs.repository.BacteriaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с штаммами
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class BacteriaService {

    /**
     * Репозиторий для работы с штаммом
     */
    private final BacteriaRepository bacteriaRepository;


    /**
     * Конвертер для штамма
     */
    private final BacteriaMapper bacteriaMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<BacteriaDto> getAllBacteria() {
        return bacteriaRepository.findAll()
                .stream()
                .map(bacteriaMapper::toBacteriaDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<BacteriaDto> getBacteriaById(Long id) {
        return bacteriaRepository.findById(id)
                .map(bacteriaMapper::toBacteriaDto);
    }

//
//    /**
//     * Возвращает список штаммов
//     *
//     * @return список всех штаммов
//     */
//    public List<BacteriaDto> getAllShortStrains() {
//        List<Bacteria> bacteria = bacteriaRepository.findAll();
//        return bacteria.stream().map(bacteriaMapper::toBacteriaDto).collect(Collectors.toList());
//    }

//    /**
//     * Сохраняет список штаммов (штаммов)
//     *
//     * @param bacteria штамми (штамми) {@link List< Bacteria >}
//     * @return штамми (штамми) {@link List< Bacteria >}
//     */
//    public List<Bacteria> saveStrains(List<Bacteria> bacteria) {
//        return bacteriaRepository.saveAll(bacteria);
//    }


//    /**
//     * Сохраняет штамм в краткой форме
//     *
//     * @param BacteriaDto транспортный объект {@link BacteriaDto}
//     * @return транспортный объект {@link BacteriaDto}
//     */
//    public BacteriaDto saveBacteria(BacteriaDto BacteriaDto) {
//        Bacteria bacteria = bacteriaMapper.toBacteria(BacteriaDto);
//        Bacteria savedBacteria = bacteriaRepository.save(bacteria);
//
//        return bacteriaMapper.toBacteriaDto(savedBacteria);
//    }

    /**
     * Сохраняет полный штамм
     *
     * @param BacteriaDto транспортный объект {@link BacteriaDto}
     * @return транспортный объект {@link BacteriaDto}
     */
    public BacteriaDto saveBacteria(BacteriaDto BacteriaDto) {
        Bacteria bacteria = bacteriaMapper.toBacteria(BacteriaDto);
        Bacteria savedBacteria = bacteriaRepository.save(bacteria);

        return bacteriaMapper.toBacteriaDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект штамма {@link BacteriaUpdateDto}
     * @return транспортный объект {@link BacteriaDto}
     */
    public BacteriaDto updateBacteria(BacteriaUpdateDto strainUpdateDto) {

        log.info(String.format("Update strain with id %s", strainUpdateDto.getId()));
        Bacteria bacteria = bacteriaMapper.toBacteria(strainUpdateDto);
        Bacteria updatedBacteria = bacteriaRepository.save(bacteria);

        return bacteriaMapper.toBacteriaDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteBacteria(Long id) {
        bacteriaRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        bacteriaRepository.deleteAll();
    }

    public Page<Bacteria> searchBacteria(StrainSearchRequest request) {
        StrainSearchSpecification<Bacteria> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return bacteriaRepository.findAll(specification, pageable);
    }
}
