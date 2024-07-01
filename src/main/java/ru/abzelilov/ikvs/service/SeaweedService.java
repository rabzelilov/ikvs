package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.BacteriaUpdateDto;
import ru.abzelilov.ikvs.dto.Seaweed.SeaweedDto;
import ru.abzelilov.ikvs.dto.Seaweed.SeaweedUpdateDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
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
    public List<SeaweedDto> getAllSeaweed() {
        return seaweedRepository.findAll()
                .stream()
                .map(seaweedMapper::toSeaweedDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<SeaweedDto> getSeaweedById(Long id) {
        return seaweedRepository.findById(id)
                .map(seaweedMapper::toSeaweedDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<SeaweedDto> getAllShortStrains() {
        List<Seaweed> archaea = seaweedRepository.findAll();
        return archaea.stream().map(seaweedMapper::toSeaweedDto).collect(Collectors.toList());
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


//    /**
//     * Сохраняет штамм в краткой форме
//     *
//     * @param SeaweedDto транспортный объект {@link StrainAddDto}
//     * @return транспортный объект {@link SeaweedDto}
//     */
//    public SeaweedDto saveSeaweed(SeaweedDto SeaweedDto) {
//            Seaweed archaea = seaweedMapper.toSeaweed(SeaweedDto);
//            Seaweed savedBacteria = seaweedRepository.save(archaea);
//
//        return seaweedMapper.toSeaweedDto(savedBacteria);
//    }

    /**
     * Сохраняет полный штамм
     *
     * @param SeaweedDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link SeaweedDto}
     */
    public SeaweedDto saveSeaweed(SeaweedDto SeaweedDto) {
        Seaweed archaea = seaweedMapper.toSeaweed(SeaweedDto);
        Seaweed savedBacteria = seaweedRepository.save(archaea);

        return seaweedMapper.toSeaweedDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param seaweedUpdateDto траспортный объект штамма {@link BacteriaUpdateDto}
     * @return транспортный объект {@link SeaweedDto}
     */
    public SeaweedDto updateSeaweed(SeaweedUpdateDto seaweedUpdateDto) {

            log.info(String.format("Update strain with id %s", seaweedUpdateDto.getId()));
            Seaweed archaea = seaweedMapper.toSeaweed(seaweedUpdateDto);
            Seaweed updatedBacteria = seaweedRepository.save(archaea);

        return seaweedMapper.toSeaweedDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteSeaweed(Long id) {
            seaweedRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        seaweedRepository.deleteAll();
    }


    public Page<Seaweed> searchSeaweed(StrainSearchRequest request) {
        StrainSearchSpecification<Seaweed> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return seaweedRepository.findAll(specification, pageable);
    }
}
