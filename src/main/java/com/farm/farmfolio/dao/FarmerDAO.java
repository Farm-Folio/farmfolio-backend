package com.farm.farmfolio.dao;

import com.farm.farmfolio.domain.Farmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface FarmerDAO {
    Farmer save(Farmer farmer);

    Optional<Farmer> findById(String id);

    List<Farmer> findAll();

    Page<Farmer> findPagination(Specification specification, Pageable pageable);
}
