package ru.abzelilov.ikvs.filter.common;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;


public enum StrainSortDirection {

    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, StrainSortRequest request) {
            return cb.asc(root.get(request.getKey()));
        }
    },
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, StrainSortRequest request) {
            return cb.desc(root.get(request.getKey()));
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, StrainSortRequest request);

}
