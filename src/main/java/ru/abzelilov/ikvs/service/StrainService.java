package ru.abzelilov.ikvs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.abzelilov.ikvs.dto.CardDto;
import ru.abzelilov.ikvs.dto.CardShortDto;
import ru.abzelilov.ikvs.filter.StrainFilterParams;
import ru.abzelilov.ikvs.mapper.StrainMapper;
import ru.abzelilov.ikvs.model.Strain;
import ru.abzelilov.ikvs.dto.StrainAddDto;
import ru.abzelilov.ikvs.model.StrainUpdateDto;
import ru.abzelilov.ikvs.repository.StrainRepository;
import ru.abzelilov.ikvs.specification.StrainSpecification;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с штаммами
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class StrainService {

    /**
     * Репозиторий для работы с штаммом
     */
    private final StrainRepository strainRepository;

    /**
     * Конвертер для штамма
     */
    private final StrainMapper strainMapper;

    /**
     * Возвращает список всех штаммов
     *
     * @return список всех штаммов
     */
    public List<CardDto> getAllStrains() {
        return strainRepository.findAll()
                .stream()
                .map(strainMapper::toCardDto)
                .collect(Collectors.toList());
    }

    
    /**
     * Возвращает список штаммов
     *
     * @return список всех штаммов
     */
    public List<CardShortDto> getAllShortStrains() {
        List<Strain> Strains = strainRepository.findAll();
        return Strains.stream().map(strainMapper::toCardShortDto).collect(Collectors.toList());
    }

    /**
     * Возвращает список штаммов с учетом фильтрации и пейджинации
     *
     * @param pageable параметры для пейджинации
     * @param filterParams параметры для фильтрации {@link StrainFilterParams}
     * @return страницу {@link Page} с траспортными объектами {@link CardShortDto}
     */
    public Page<CardShortDto> getStrains(Pageable pageable, StrainFilterParams filterParams) {
        Page<Strain> strains = strainRepository.findAll(new StrainSpecification(filterParams), pageable);

        return strains.map(strainMapper::toCardShortDto);
    }

    /**
     * Сохраняет список штаммов (штаммов)
     *
     * @param strains штамми (штамми) {@link List<Strain>}
     * @return штамми (штамми) {@link List<Strain>}
     */
    public List<Strain> saveStrains(List<Strain> strains) {
        return strainRepository.saveAll(strains);
    }


    /**
     * Сохраняет штамм
     *
     * @param strainAddDto транспортный объект {@link StrainAddDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardShortDto saveStrain(StrainAddDto strainAddDto) {
            Strain strain = strainMapper.toStrain(strainAddDto);
            Strain savedStrain = strainRepository.save(strain);

        return strainMapper.toCardShortDto(savedStrain);
    }

    /**
     * Обновляет штамм
     *
     * @param strainUpdateDto траспортный объект штамма {@link StrainUpdateDto}
     * @return транспортный объект {@link CardShortDto}
     */
    public CardShortDto updateStrain(StrainUpdateDto strainUpdateDto) {
        
            log.info(String.format("Update strain with id %s",strainUpdateDto.getIdStrain()));
            Strain strain = strainMapper.toStrain(strainUpdateDto);
            Strain updatedStrain = strainRepository.save(strain);


        return strainMapper.toCardShortDto(updatedStrain);
    }

    /**
     * Удаляет штам
     *
     * @param id идентификатор штамма
     */
    public void deleteStrain(Long id) {
            strainRepository.deleteById(id);
    }

    /**
     * Удаляет все штаммы
     */
    public void deleteAllStrains() {
        strainRepository.deleteAll();
    }
}
