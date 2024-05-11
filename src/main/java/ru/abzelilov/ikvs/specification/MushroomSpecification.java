package ru.abzelilov.ikvs.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.abzelilov.ikvs.filter.StrainFilterParams;
import ru.abzelilov.ikvs.model.Dna;
import ru.abzelilov.ikvs.model.Mushroom;
import ru.abzelilov.ikvs.model.Mushroom_;

import java.util.ArrayList;
import java.util.List;


/**
 * Спецификация для поиска штаммов по фильтру
 */
@RequiredArgsConstructor
public class MushroomSpecification implements Specification<Mushroom> {

    private final StrainFilterParams strainFilterParams;


    @Override
    public Predicate toPredicate(Root<Mushroom> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (strainFilterParams.getGenus() != null) predicates.add(getGenusPredicate(root, cb));
        if (strainFilterParams.getConsortium() != null) predicates.add(getCollectionNamePredicate(root, cb));
        if (strainFilterParams.getNameCollection() != null) predicates.add(getConsortiumPredicate(root, cb));

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getGenusPredicate(Root<Mushroom> root, CriteriaBuilder cb) {
        return cb.like(root.get(Mushroom_.genus),"%" + strainFilterParams.getGenus() + "%");
    }


    private Predicate getConsortiumPredicate(Root<Mushroom> root, CriteriaBuilder cb) {
        return cb.equal(root.get(Mushroom_.consortium), "%" + strainFilterParams.getConsortium() + "%");
    }


    private Predicate getCollectionNamePredicate(Root<Mushroom> root, CriteriaBuilder cb) {
        return cb.equal(root.get(Mushroom_.nameCollection), "%" + strainFilterParams.getNameCollection() + "%");
    }
}
