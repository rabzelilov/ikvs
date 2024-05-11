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
import ru.abzelilov.ikvs.mapper.ArchaeaMapper;
import ru.abzelilov.ikvs.model.Archaea;
import ru.abzelilov.ikvs.repository.ArchaeaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с штаммами
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class ArchaeaService {

    /**
     * Репозиторий для работы с штаммом
     */

    private final ArchaeaRepository  archaeaRepository;

    /**
     * Конвертер для штамма
     */
    private final ArchaeaMapper archaeaMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<CardDto> getAllStrains() {
        return archaeaRepository.findAll()
                .stream()
                .map(archaeaMapper::toCardDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<CardDto> getStrainById(Long id) {
        return archaeaRepository.findById(id)
                .map(archaeaMapper::toCardDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<CardShortDto> getAllShortStrains() {
        List<Archaea> archaea = archaeaRepository.findAll();
        return archaea.stream().map(archaeaMapper::toCardShortDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param archaea штамми (штамми) {@link List< Archaea >}
     * @return штамми (штамми) {@link List< Archaea >}
     */
    public List<Archaea> saveStrains(List<Archaea> archaea) {
        return archaeaRepository.saveAll(archaea);
    }


    /**
     * Сохраняет штамм в краткой форме
     *
     * @param cardShortDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardShortDto saveStrain(CardShortDto cardShortDto) {
            Archaea archaea = archaeaMapper.toArchaea(cardShortDto);
            Archaea savedBacteria = archaeaRepository.save(archaea);

        return archaeaMapper.toCardShortDto(savedBacteria);
    }

    /**
     * Сохраняет полный штамм
     *
     * @param cardDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto saveStrain(CardDto cardDto) {
        Archaea archaea = archaeaMapper.toArchaea(cardDto);
        Archaea savedBacteria = archaeaRepository.save(archaea);

        return archaeaMapper.toCardDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект штамма {@link StrainUpdateDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto updateStrain(StrainUpdateDto strainUpdateDto) {

            log.info(String.format("Update strain with id %s",strainUpdateDto.getId()));
            Archaea archaea = archaeaMapper.toArchaea(strainUpdateDto);
            Archaea updatedBacteria = archaeaRepository.save(archaea);

        return archaeaMapper.toCardDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteStrain(Long id) {
            archaeaRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        archaeaRepository.deleteAll();
    }


    public Page<Archaea> searchArchaea(StrainSearchRequest request) {
        StrainSearchSpecification<Archaea> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return archaeaRepository.findAll(specification, pageable);
    }
}
