package com.farm.farmfolio.dto.pagination;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO {
    private Integer draw;
    private Integer pageNo;
    private Integer pageSize;
    private List<com.farm.farmfolio.dto.pagination.SearchCriteria> filter;
    private String report;
}
