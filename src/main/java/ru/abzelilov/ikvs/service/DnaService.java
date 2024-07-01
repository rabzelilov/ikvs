package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.dna.DnaDto;
import ru.abzelilov.ikvs.dto.dna.DnaUpdateDto;
import ru.abzelilov.ikvs.filter.common.StrainSearchRequest;
import ru.abzelilov.ikvs.filter.common.StrainSearchSpecification;
import ru.abzelilov.ikvs.mapper.DnaMapper;
import ru.abzelilov.ikvs.model.Dna;
import ru.abzelilov.ikvs.repository.DnaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с штаммами
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class DnaService {

    /**
     * Репозиторий для работы с штаммом
     */

    private final DnaRepository dnaRepository;

    /**
     * Конвертер для штамма
     */
    private final DnaMapper dnaMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<DnaDto> getAllDna() {
        return dnaRepository.findAll()
                .stream()
                .map(dnaMapper::toDnaDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<DnaDto> getDnaById(Long id) {
        return dnaRepository.findById(id)
                .map(dnaMapper::toDnaDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<DnaDto> getAllShortDnas() {
        List<Dna> dna = dnaRepository.findAll();
        return dna.stream().map(dnaMapper::toDnaDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param dna штамми (штамми) {@link List<Dna>}
     * @return штамми (штамми) {@link List<Dna>}
     */
    public List<Dna> saveDnas(List<Dna> dna) {
        return dnaRepository.saveAll(dna);
    }


    /**
     * Сохраняет штамм в краткой форме
     *
     * @param DnaDto транспортный объект {@link DnaDto}
     * @return транспортный объект {@link DnaDto}
     */
    public DnaDto saveDna(DnaDto DnaDto) {
            Dna dna = dnaMapper.toDna(DnaDto);
            Dna savedDna = dnaRepository.save(dna);

        return dnaMapper.toDnaDto(savedDna);
    }

//    /**
//     * Сохраняет полный штамм
//     *
//     * @param DnaDto транспортный объект {@link StrainAddDto}
//     * @return транспортный объект {@link DnaDto}
//     */
//    public DnaDto saveDna(DnaDto DnaDto) {
//        Dna dna = dnaMapper.toDna(DnaDto);
//        Dna savedBacteria = dnaRepository.save(dna);
//
//        return dnaMapper.toDnaDto(savedBacteria);
//    }

    /**
     * Обновляет штамм
     *
     * @param dnaUpdateDto траспортный объект штамма {@link DnaUpdateDto}
     * @return транспортный объект {@link DnaDto}
     */
    public DnaDto updateDna(DnaUpdateDto dnaUpdateDto) {

            log.info(String.format("Update Dna with id %s", dnaUpdateDto.getId()));
            Dna dna = dnaMapper.toDna(dnaUpdateDto);
            Dna updatedBacteria = dnaRepository.save(dna);

        return dnaMapper.toDnaDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteDna(Long id) {
            dnaRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllDnas() {
        dnaRepository.deleteAll();
    }


    public Page<Dna> searchDna(StrainSearchRequest request) {
        StrainSearchSpecification<Dna> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return dnaRepository.findAll(specification, pageable);
    }
}
