package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.*;
import ru.abzelilov.ikvs.dto.MushroomDto.MushroomDto;
import ru.abzelilov.ikvs.dto.MushroomDto.MushroomUpdateDto;
import ru.abzelilov.ikvs.mapper.MushroomMapper;
import ru.abzelilov.ikvs.mapper.StrainMapper;
import ru.abzelilov.ikvs.model.Mushroom;
import ru.abzelilov.ikvs.repository.MushroomRepository;
import ru.abzelilov.ikvs.repository.StrainRepository;

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
    private final StrainRepository strainRepository;
    /**
     * Конвертер для штамма
     */
    private final MushroomMapper mushroomMapper;

    private final StrainMapper strainMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<MushroomDto> getAllMushrooms() {
        return mushroomRepository.findAll()
                .stream()
                .map(mushroomMapper::toMushroomDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<MushroomDto> getMushroomById(Long id) {
        return mushroomRepository.findById(id)
                .map(mushroomMapper::toMushroomDto);
    }


    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<MushroomDto> getAllShortStrains() {
        List<Mushroom> archaea = mushroomRepository.findAll();
        return archaea.stream().map(mushroomMapper::toMushroomDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param archaea штамми (штамми) {@link List< Mushroom >}
     * @return штамми (штамми) {@link List<Mushroom>}
     */
    public List<Mushroom> saveStrains(List<Mushroom> archaea) {
        return mushroomRepository.saveAll(archaea);
    }


//    /**
//     * Сохраняет штамм в краткой форме
//     *
//     * @param MushroomDto транспортный объект {@link StrainAddDto}
//     * @return транспортный объект {@link MushroomDto}
//     */
//    public MushroomDto saveMushroom(MushroomDto MushroomDto) {
//        Mushroom archaea = mushroomMapper.toMushroom(MushroomDto);
//        Mushroom savedBacteria = mushroomRepository.save(archaea);
//
//        return mushroomMapper.toMushroomDto(savedBacteria);
//    }

    /**
     * Сохраняет полный штамм
     *
     * @param MushroomDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link MushroomDto}
     */
    public MushroomDto saveMushroom(MushroomDto MushroomDto) {
        Mushroom archaea = mushroomMapper.toMushroom(MushroomDto);
        Mushroom savedBacteria = mushroomRepository.save(archaea);

        return mushroomMapper.toMushroomDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param mushroomUpdateDto траспортный объект штамма {@link BacteriaUpdateDto}
     * @return транспортный объект {@link MushroomDto}
     */
    public MushroomDto updateMushroom(MushroomUpdateDto mushroomUpdateDto) {

        log.info(String.format("Update strain with id %s", mushroomUpdateDto.getId()));
        Mushroom archaea = mushroomMapper.toMushroom(mushroomUpdateDto);
        Mushroom updatedBacteria = mushroomRepository.save(archaea);

        return mushroomMapper.toMushroomDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteMushroom(Long id) {
        mushroomRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        mushroomRepository.deleteAll();
    }



}
