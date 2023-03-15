package com.farm.farmfolio.dao;

import com.farm.farmfolio.domain.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AppUserDAO {
    AppUser save(AppUser farmer);

    Optional<AppUser> findById(String id);

    List<AppUser> findAll();

    Page<AppUser> findPagination(Specification specification, Pageable pageable);

    AppUser findByIdAndType(String id,Integer type);

    List<AppUser> findAllByType(Integer type);
}
