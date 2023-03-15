package com.farm.farmfolio.dao.impl;

import com.farm.farmfolio.dao.FarmerDAO;
import com.farm.farmfolio.domain.Farmer;
import com.farm.farmfolio.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FarmerDAOImpl implements FarmerDAO{

    @Autowired
    private FarmerRepository farmerRepository;

    @Override
    public Farmer save(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    @Override
    public Optional<Farmer> findById(String id) {
        return farmerRepository.findById(id);
    }

    @Override
    public List<Farmer> findAll() {
        return farmerRepository.findAll();
    }

    @Override
    public Page<Farmer> findPagination(Specification specification, Pageable pageable) {
        return farmerRepository.findAll(specification,pageable);
    }
}
