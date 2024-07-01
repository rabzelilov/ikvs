package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.dto.simplest.SimplestDto;
import ru.abzelilov.ikvs.dto.simplest.SimplestUpdateDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.filter.common.StrainSearchSpecification;
import ru.abzelilov.ikvs.mapper.SimplestMapper;
import ru.abzelilov.ikvs.model.Simplest;
import ru.abzelilov.ikvs.repository.SimplestRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с штаммами
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class SimplestService {

    /**
     * Репозиторий для работы с штаммом
     */

    private final SimplestRepository simplestRepository;

    /**
     * Конвертер для штамма
     */
    private final SimplestMapper simplestMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<SimplestDto> getAllSimplest() {
        return simplestRepository.findAll()
                .stream()
                .map(simplestMapper::toSimplestDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<SimplestDto> getSimplestById(Long id) {
        return simplestRepository.findById(id)
                .map(simplestMapper::toSimplestDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<SimplestDto> getAllShortStrains() {
        List<Simplest> archaea = simplestRepository.findAll();
        return archaea.stream().map(simplestMapper::toSimplestDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param archaea штамми (штамми) {@link List<Simplest>}
     * @return штамми (штамми) {@link List<Simplest>}
     */
    public List<Simplest> saveStrains(List<Simplest> archaea) {
        return simplestRepository.saveAll(archaea);
    }


//    /**
//     * Сохраняет штамм в краткой форме
//     *
//     * @param SimplestDto транспортный объект {@link StrainAddDto}
//     * @return транспортный объект {@link SimplestDto}
//     */
//    public SimplestDto saveSimplest(SimplestDto SimplestDto) {
//            Simplest archaea = simplestMapper.toSimplest(SimplestDto);
//            Simplest savedBacteria = simplestRepository.save(archaea);
//
//        return simplestMapper.toSimplestDto(savedBacteria);
//    }

    /**
     * Сохраняет полный штамм
     *
     * @param SimplestDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link SimplestDto}
     */
    public SimplestDto saveSimplest(SimplestDto SimplestDto) {
        Simplest archaea = simplestMapper.toSimplest(SimplestDto);
        Simplest savedBacteria = simplestRepository.save(archaea);

        return simplestMapper.toSimplestDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param simplestUpdateDto траспортный объект штамма {@link SimplestUpdateDto}
     * @return транспортный объект {@link SimplestDto}
     */
    public SimplestDto updateSimplest(SimplestUpdateDto simplestUpdateDto) {

            log.info(String.format("Update strain with id %s", simplestUpdateDto.getId()));
            Simplest archaea = simplestMapper.toSimplest(simplestUpdateDto);
            Simplest updatedBacteria = simplestRepository.save(archaea);

        return simplestMapper.toSimplestDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteSimplest(Long id) {
            simplestRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        simplestRepository.deleteAll();
    }


    public Page<Simplest> searchSimplest(StrainSearchRequest request) {
        StrainSearchSpecification<Simplest> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return simplestRepository.findAll(specification, pageable);
    }
}
