package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;
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
    public List<CardDto> getAllStrains() {
        return simplestRepository.findAll()
                .stream()
                .map(simplestMapper::toCardDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<CardDto> getStrainById(Long id) {
        return simplestRepository.findById(id)
                .map(simplestMapper::toCardDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<CardShortDto> getAllShortStrains() {
        List<Simplest> archaea = simplestRepository.findAll();
        return archaea.stream().map(simplestMapper::toCardShortDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param archaea штамми (штамми) {@link List< Simplest >}
     * @return штамми (штамми) {@link List< Simplest >}
     */
    public List<Simplest> saveStrains(List<Simplest> archaea) {
        return simplestRepository.saveAll(archaea);
    }


    /**
     * Сохраняет штамм в краткой форме
     *
     * @param cardShortDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardShortDto saveStrain(CardShortDto cardShortDto) {
            Simplest archaea = simplestMapper.toSimplest(cardShortDto);
            Simplest savedBacteria = simplestRepository.save(archaea);

        return simplestMapper.toCardShortDto(savedBacteria);
    }

    /**
     * Сохраняет полный штамм
     *
     * @param cardDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto saveStrain(CardDto cardDto) {
        Simplest archaea = simplestMapper.toSimplest(cardDto);
        Simplest savedBacteria = simplestRepository.save(archaea);

        return simplestMapper.toCardDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект штамма {@link StrainUpdateDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto updateStrain(StrainUpdateDto strainUpdateDto) {

            log.info(String.format("Update strain with id %s",strainUpdateDto.getId()));
            Simplest archaea = simplestMapper.toSimplest(strainUpdateDto);
            Simplest updatedBacteria = simplestRepository.save(archaea);

        return simplestMapper.toCardDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteStrain(Long id) {
            simplestRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        simplestRepository.deleteAll();
    }


    public Page<Simplest> searchArchaea(StrainSearchRequest request) {
        StrainSearchSpecification<Simplest> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return simplestRepository.findAll(specification, pageable);
    }
}
