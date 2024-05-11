package ru.abzelilov.ikvs.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.abzelilov.ikvs.filter.StrainFilterParams;
import ru.abzelilov.ikvs.model.Archaea;
import ru.abzelilov.ikvs.model.Archaea_;

import java.util.ArrayList;
import java.util.List;


/**
 * Спецификация для поиска штаммов по фильтру
 */
@RequiredArgsConstructor
public class ArchaeaSpecification implements Specification<Archaea> {

    private final StrainFilterParams strainFilterParams;


    @Override
    public Predicate toPredicate(Root<Archaea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (strainFilterParams.getGenus() != null) predicates.add(getGenusPredicate(root, cb));
        if (strainFilterParams.getConsortium() != null) predicates.add(getCollectionNamePredicate(root, cb));
        if (strainFilterParams.getNameCollection() != null) predicates.add(getConsortiumPredicate(root, cb));

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getGenusPredicate(Root<Archaea> root, CriteriaBuilder cb) {
        return cb.like(root.get(Archaea_.genus),"%" + strainFilterParams.getGenus() + "%");
    }


    private Predicate getConsortiumPredicate(Root<Archaea> root, CriteriaBuilder cb) {
        return cb.equal(root.get(Archaea_.consortium), "%" + strainFilterParams.getConsortium() + "%");
    }


    private Predicate getCollectionNamePredicate(Root<Archaea> root, CriteriaBuilder cb) {
        return cb.equal(root.get(Archaea_.nameCollection), "%" + strainFilterParams.getNameCollection() + "%");
    }
}
