package ru.abzelilov.ikvs.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.abzelilov.ikvs.filter.StrainFilterParams;
import ru.abzelilov.ikvs.model.Strain;
import ru.abzelilov.ikvs.model.Strain_;

import java.util.ArrayList;
import java.util.List;


/**
 * Спецификация для поиска штаммов по фильтру
 */
@RequiredArgsConstructor
public class StrainSpecification implements Specification<Strain> {

    private final StrainFilterParams strainFilterParams;


    @Override
    public Predicate toPredicate(Root<Strain> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (strainFilterParams.getName() != null) predicates.add(getNamePredicate(root, cb));
        if (strainFilterParams.getConsortium() != null) predicates.add(getCollectionNamePredicate(root, cb));
        if (strainFilterParams.getNameCollection() != null) predicates.add(getConsortiumPredicate(root, cb));

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate getNamePredicate(Root<Strain> root, CriteriaBuilder cb) {
        return cb.like(root.get(Strain_.name),"%" + strainFilterParams.getName() + "%");
    }


    private Predicate getConsortiumPredicate(Root<Strain> root, CriteriaBuilder cb) {
        return cb.equal(root.get(Strain_.consortium), strainFilterParams.getConsortium());
    }


    private Predicate getCollectionNamePredicate(Root<Strain> root, CriteriaBuilder cb) {
        return cb.equal(root.get(Strain_.nameCollection), strainFilterParams.getNameCollection());
    }
}
