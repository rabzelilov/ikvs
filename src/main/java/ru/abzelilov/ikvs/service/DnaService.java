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
    public List<CardDto> getAllStrains() {
        return dnaRepository.findAll()
                .stream()
                .map(dnaMapper::toCardDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public Optional<CardDto> getStrainById(Long id) {
        return dnaRepository.findById(id)
                .map(dnaMapper::toCardDto);
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<CardShortDto> getAllShortStrains() {
        List<Dna> dna = dnaRepository.findAll();
        return dna.stream().map(dnaMapper::toCardShortDto).collect(Collectors.toList());
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param dna штамми (штамми) {@link List<Dna>}
     * @return штамми (штамми) {@link List<Dna>}
     */
    public List<Dna> saveStrains(List<Dna> dna) {
        return dnaRepository.saveAll(dna);
    }


    /**
     * Сохраняет штамм в краткой форме
     *
     * @param cardShortDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardShortDto saveStrain(CardShortDto cardShortDto) {
            Dna dna = dnaMapper.toDna(cardShortDto);
            Dna savedBacteria = dnaRepository.save(dna);

        return dnaMapper.toCardShortDto(savedBacteria);
    }

    /**
     * Сохраняет полный штамм
     *
     * @param cardDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto saveStrain(CardDto cardDto) {
        Dna dna = dnaMapper.toDna(cardDto);
        Dna savedBacteria = dnaRepository.save(dna);

        return dnaMapper.toCardDto(savedBacteria);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект штамма {@link StrainUpdateDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardDto updateStrain(StrainUpdateDto strainUpdateDto) {

            log.info(String.format("Update strain with id %s",strainUpdateDto.getId()));
            Dna dna = dnaMapper.toDna(strainUpdateDto);
            Dna updatedBacteria = dnaRepository.save(dna);

        return dnaMapper.toCardDto(updatedBacteria);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteStrain(Long id) {
            dnaRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        dnaRepository.deleteAll();
    }


    public Page<Dna> searchArchaea(StrainSearchRequest request) {
        StrainSearchSpecification<Dna> specification = new StrainSearchSpecification<>(request);
        Pageable pageable = StrainSearchSpecification.getPageable(request.getPage(), request.getSize());
        return dnaRepository.findAll(specification, pageable);
    }
}
