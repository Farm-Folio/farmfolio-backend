package com.farm.farmfolio.repository;

import com.farm.farmfolio.domain.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, String>, JpaSpecificationExecutor<Farmer> {
}
