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
import ru.abzelilov.ikvs.mapper.SeaweedMapper;
import ru.abzelilov.ikvs.model.Seaweed;
import ru.abzelilov.ikvs.repository.SeaweedRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с штаммами
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class SeaweedService {

    /**
     * Репозиторий для работы с штаммом
     */

    private final SeaweedRepository seaweedRepository;

    /**
     * Конвертер для штамма
     */
    private final SeaweedMapper seaweedMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<CardDto> getAllStrains() {
        return seaweedRepository.findAll()
                .stream()
                .map(seaweedMapper::toCardDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<CardDto> getStrainById(Long id) {
        return seaweedRepository.findById(id)
                .map(seaweedMapper::toCardDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<CardShortDto> getAllShortStrains() {
        List<Seaweed> archaea = seaweedRepository.findAll();
        return archaea.stream().map(seaweedMapper::toCardShortDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param archaea штамми (штамми) {@link List< Seaweed >}
     * @return штамми (штамми) {@link List< Seaweed >}
     */
    public List<Seaweed> saveStrains(List<Seaweed> archaea) {
        return seaweedRepository.saveAll(archaea);
    }


    /**
     * Сохраняет штамм в краткой форме
     *
     * @param cardShortDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardShortDto saveStrain(CardShortDto cardShortDto) {
            Seaweed archaea = seaweedMapper.toSeaweed(cardShortDto);
            Seaweed savedBacteria = seaweedRepository.save(archaea);

        return seaweedMapper.toCardShortDto(savedBacteria);
    }

    /**
     * Сохраняет полный штамм
     *
     * @param cardDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto saveStrain(CardDto cardDto) {
        Seaweed archaea = seaweedMapper.toSeaweed(cardDto);
        Seaweed savedBacteria = seaweedRepository.save(archaea);

        return seaweedMapper.toCardDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект штамма {@link StrainUpdateDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto updateStrain(StrainUpdateDto strainUpdateDto) {

            log.info(String.format("Update strain with id %s",strainUpdateDto.getId()));
            Seaweed archaea = seaweedMapper.toSeaweed(strainUpdateDto);
            Seaweed updatedBacteria = seaweedRepository.save(archaea);

        return seaweedMapper.toCardDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteStrain(Long id) {
            seaweedRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        seaweedRepository.deleteAll();
    }


    public Page<Seaweed> searchArchaea(StrainSearchRequest request) {
        StrainSearchSpecification<Seaweed> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return seaweedRepository.findAll(specification, pageable);
    }
}
