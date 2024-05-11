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
import ru.abzelilov.ikvs.mapper.MushroomMapper;
import ru.abzelilov.ikvs.model.Mushroom;
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
public class MushroomService {

    /**
     * Репозиторий для работы с штаммом
     */

    private final MushroomRepository mushroomRepository;

    /**
     * Конвертер для штамма
     */
    private final MushroomMapper mushroomMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<CardDto> getAllStrains() {
        return mushroomRepository.findAll()
                .stream()
                .map(mushroomMapper::toCardDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<CardDto> getStrainById(Long id) {
        return mushroomRepository.findById(id)
                .map(mushroomMapper::toCardDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<CardShortDto> getAllShortStrains() {
        List<Mushroom> archaea = mushroomRepository.findAll();
        return archaea.stream().map(mushroomMapper::toCardShortDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param archaea штамми (штамми) {@link List< Mushroom >}
     * @return штамми (штамми) {@link List< Mushroom >}
     */
    public List<Mushroom> saveStrains(List<Mushroom> archaea) {
        return mushroomRepository.saveAll(archaea);
    }


    /**
     * Сохраняет штамм в краткой форме
     *
     * @param cardShortDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardShortDto saveStrain(CardShortDto cardShortDto) {
            Mushroom archaea = mushroomMapper.toMushroom(cardShortDto);
            Mushroom savedBacteria = mushroomRepository.save(archaea);

        return mushroomMapper.toCardShortDto(savedBacteria);
    }

    /**
     * Сохраняет полный штамм
     *
     * @param cardDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto saveStrain(CardDto cardDto) {
        Mushroom archaea = mushroomMapper.toMushroom(cardDto);
        Mushroom savedBacteria = mushroomRepository.save(archaea);

        return mushroomMapper.toCardDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект штамма {@link StrainUpdateDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto updateStrain(StrainUpdateDto strainUpdateDto) {

            log.info(String.format("Update strain with id %s",strainUpdateDto.getId()));
            Mushroom archaea = mushroomMapper.toMushroom(strainUpdateDto);
            Mushroom updatedBacteria = mushroomRepository.save(archaea);

        return mushroomMapper.toCardDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteStrain(Long id) {
            mushroomRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        mushroomRepository.deleteAll();
    }


    public Page<Mushroom> searchArchaea(StrainSearchRequest request) {
        StrainSearchSpecification<Mushroom> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return mushroomRepository.findAll(specification, pageable);
    }
}
