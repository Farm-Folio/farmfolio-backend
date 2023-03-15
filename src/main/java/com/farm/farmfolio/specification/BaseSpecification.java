package com.farm.farmfolio.specification;


import com.farm.farmfolio.domain.AppUser;
import com.farm.farmfolio.dto.pagination.PaginationDTO;
import com.farm.farmfolio.dto.pagination.SearchCriteria;
import com.farm.farmfolio.util.DateUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseSpecification {

    Predicate getPredicate(SearchCriteria criteria, From root,
                           CriteriaQuery<?> query,
                           CriteriaBuilder builder) {
        Predicate predicate = null;
        switch (criteria.getOperation()) {
            case ">":
                predicate = builder.greaterThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
                break;
            case "<":
                predicate = builder.lessThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
                break;
            case ":":
                predicate = getContainsPredicate(criteria, root, builder);
                break;
            case "<>":
                predicate = getDatePredicate(criteria, root, builder);
                break;
            case "::":
                predicate = root.get(criteria.getKey()).in(criteria.getValues());
                break;
            case "=":
                predicate = getEqualsPredicate(criteria, root, builder);
                break;
            default:

        }
        return predicate;
    }

    private Predicate getDatePredicate(SearchCriteria criteria, From root, CriteriaBuilder builder) {
        String startDateStr = criteria.getValue().toString().split("-")[0];
        String endDateStr = criteria.getValue().toString().split("-")[1];
        try {
            return builder.between(root.get(criteria.getKey()), DateUtil.filterToDate(startDateStr), DateUtil.filterToDate(endDateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private Predicate getContainsPredicate(SearchCriteria criteria, From root, CriteriaBuilder builder) {
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            Predicate predicate = null;
            if(!ObjectUtils.isEmpty(criteria.getValue())){
                Expression<String> path = root.get(criteria.getKey());
                Expression<String> lowerCase = builder.lower(path);
                predicate = builder.like(lowerCase , "%" + criteria.getValue().toString().toLowerCase() + "%" );
            }
            return predicate;
        } else {
            Expression<String> filterKeyExp = root.get(criteria.getKey()).as(String.class);
            Predicate predicate = null;
            if(!ObjectUtils.isEmpty(criteria.getValue())){
                filterKeyExp = builder.lower(filterKeyExp);
                 predicate = builder.like(filterKeyExp ,"%" + criteria.getValue().toString().trim().toLowerCase() + "%");
//            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
            return predicate;
        }
    }


    private Predicate getEqualsPredicate(SearchCriteria criteria, From root, CriteriaBuilder builder) {
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            Predicate predicate = null;
            if(!ObjectUtils.isEmpty(criteria.getValue())){
                Expression<String> path = root.get(criteria.getKey());
                Expression<String> lowerCase = builder.lower(path);
                predicate = builder.equal(lowerCase,criteria.getValue().toString());
            }
            return predicate;
        } else {
            Expression<String> filterKeyExp = root.get(criteria.getKey()).as(String.class);
            Predicate predicate = null;
            if(!ObjectUtils.isEmpty(criteria.getValue())){
                filterKeyExp = builder.lower(filterKeyExp);
                predicate = builder.like(filterKeyExp ,"%" + criteria.getValue().toString().trim().toLowerCase() + "%");
//            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
            return predicate;
        }
    }

    public static <T> Specification<T> getSpec(PaginationDTO pagination,Class<T> clazz) {
        List<SearchCriteria> params = new ArrayList<>();
        pagination.getFilter().forEach(searchCriteria -> {
            params.add(searchCriteria);
        });

        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(obj -> {
                    try { return (Specification) clazz.getDeclaredConstructor(SearchCriteria.class).newInstance(obj); }
                    catch (Exception e) { e.printStackTrace();}
                    return null;
                }).collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }

        return result;
    }

}
