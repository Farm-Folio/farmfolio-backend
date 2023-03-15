package com.farm.farmfolio.repository;

import com.farm.farmfolio.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String>, JpaSpecificationExecutor<AppUser> {

    AppUser findByIdAndType(String id,Integer type);

    List<AppUser> findAllByType(Integer type);
}
