package org.mssc.order.msscsodaorderservice.specification;

import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SodaOrderSpecification implements Specification<SodaOrder> {


    @Override
    public Predicate toPredicate(Root<SodaOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
