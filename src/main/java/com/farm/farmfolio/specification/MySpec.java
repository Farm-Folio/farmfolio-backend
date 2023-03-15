package com.farm.farmfolio.specification;

import com.farm.farmfolio.dto.pagination.PaginationDTO;
import com.farm.farmfolio.dto.pagination.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MySpec {

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
