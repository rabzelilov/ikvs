package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.BacteriaUpdateDto;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.dto.archaea.ArchaeaDto;
import ru.abzelilov.ikvs.dto.archaea.ArchaeaUpdateDto;
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
    private final ArchaeaRepository archaeaRepository;


    /**
     * Конвертер для штамма
     */
    private final ArchaeaMapper archaeaMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<ArchaeaDto> getAllArchaea() {
        return archaeaRepository.findAll()
                .stream()
                .map(archaeaMapper::toArchaeaDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<ArchaeaDto> getArchaeaById(Long id) {
        return archaeaRepository.findById(id)
                .map(archaeaMapper::toArchaeaDto);
    }


    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
//    public List<CardShortDto> getAllShortStrains() {
//        List<Archaea> Archaea = archaeaRepository.findAll();
//        return Archaea.stream().map(archaeaMapper::toArchaeaDto).collect(Collectors.toList());
//    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param Archaea штамми (штамми) {@link List< Archaea >}
     * @return штамми (штамми) {@link List< Archaea >}
     */
    public List<Archaea> saveStrains(List<Archaea> Archaea) {
        return archaeaRepository.saveAll(Archaea);
    }


//    /**
//     * Сохраняет штамм в краткой форме
//     *
//     * @param archaeaDto транспортный объект {@link StrainAddDto}
//     * @return транспортный объект {@link CardShortDto}
//     */
//    public ArchaeaDto saveArchaea(ArchaeaDto archaeaDto) {
//        Archaea Archaea = archaeaMapper.toArchaea(archaeaDto);
//        Archaea savedArchaea = archaeaRepository.save(Archaea);
//
//        return archaeaMapper.toArchaeaDto(savedArchaea);
//    }

    /**
     * Сохраняет полный штамм
     *
     * @param archaeaDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link ArchaeaDto}
     */
    public ArchaeaDto saveArchaea(ArchaeaDto archaeaDto) {
        Archaea Archaea = archaeaMapper.toArchaea(archaeaDto);
        Archaea savedArchaea = archaeaRepository.save(Archaea);

        return archaeaMapper.toArchaeaDto(savedArchaea);
    }

    /**
     * Обновляет штамм
     *
     * @param archaeaUpdateDto траспортный объект штамма {@link BacteriaUpdateDto}
     * @return транспортный объект {@link ArchaeaDto}
     */
    public ArchaeaDto updateArchaea(ArchaeaUpdateDto archaeaUpdateDto) {

        log.info(String.format("Update strain with id %s", archaeaUpdateDto.getId()));
        Archaea Archaea = archaeaMapper.toArchaea(archaeaUpdateDto);
        Archaea updatedArchaea = archaeaRepository.save(Archaea);

        return archaeaMapper.toArchaeaDto(updatedArchaea);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteArchaea(Long id) {
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
