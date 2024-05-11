package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.filter.common.StrainSearchSpecification;
import ru.abzelilov.ikvs.mapper.BacteriaMapper;
import ru.abzelilov.ikvs.model.Bacteria;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.dto.StrainUpdateDto;
import ru.abzelilov.ikvs.repository.ArchaeaRepository;
import ru.abzelilov.ikvs.repository.BacteriaRepository;
import ru.abzelilov.ikvs.repository.MushroomRepository;

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
    public List<CardDto> getAllBacteria() {
        return bacteriaRepository.findAll()
                .stream()
                .map(bacteriaMapper::toCardDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<CardDto> getBacteriaById(Long id) {
        return bacteriaRepository.findById(id)
                .map(bacteriaMapper::toCardDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<CardShortDto> getAllShortStrains() {
        List<Bacteria> bacteria = bacteriaRepository.findAll();
        return bacteria.stream().map(bacteriaMapper::toCardShortDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param bacteria штамми (штамми) {@link List< Bacteria >}
     * @return штамми (штамми) {@link List< Bacteria >}
     */
    public List<Bacteria> saveStrains(List<Bacteria> bacteria) {
        return bacteriaRepository.saveAll(bacteria);
    }


    /**
     * Сохраняет штамм в краткой форме
     *
     * @param cardShortDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardShortDto saveBacteria(CardShortDto cardShortDto) {
            Bacteria bacteria = bacteriaMapper.toStrain(cardShortDto);
            Bacteria savedBacteria = bacteriaRepository.save(bacteria);

        return bacteriaMapper.toCardShortDto(savedBacteria);
    }

    /**
     * Сохраняет полный штамм
     *
     * @param cardDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto saveBacteria(CardDto cardDto) {
        Bacteria bacteria = bacteriaMapper.toStrain(cardDto);
        Bacteria savedBacteria = bacteriaRepository.save(bacteria);

        return bacteriaMapper.toCardDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект штамма {@link StrainUpdateDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto updateBacteria(StrainUpdateDto strainUpdateDto) {

            log.info(String.format("Update strain with id %s",strainUpdateDto.getId()));
            Bacteria bacteria = bacteriaMapper.toStrain(strainUpdateDto);
            Bacteria updatedBacteria = bacteriaRepository.save(bacteria);

        return bacteriaMapper.toCardDto(updatedBacteria);
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
