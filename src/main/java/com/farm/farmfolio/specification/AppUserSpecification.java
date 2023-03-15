package com.farm.farmfolio.specification;

import com.farm.farmfolio.domain.AppUser;
import com.farm.farmfolio.dto.pagination.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AppUserSpecification extends BaseSpecification implements Specification<AppUser> {

    private SearchCriteria criteria;

    public AppUserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<AppUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return getPredicate(criteria, root, query, criteriaBuilder);
    }
}
