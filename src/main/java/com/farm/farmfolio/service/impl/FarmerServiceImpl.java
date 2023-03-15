package com.farm.farmfolio.service.impl;

import com.farm.farmfolio.dao.FarmerDAO;
import com.farm.farmfolio.domain.Farmer;
import com.farm.farmfolio.dto.FarmerDTO;
import com.farm.farmfolio.service.FarmerService;
import com.farm.farmfolio.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    public FarmerDAO farmerDAO;

    @Override
    public FarmerDTO save(FarmerDTO farmerDTO) {
        Farmer farmer = Mapper.map(farmerDTO,Farmer.class);
        farmerDAO.save(farmer);
        return farmerDTO;
    }

    private FarmerDTO copyToDTO(Farmer farmer){
        return Mapper.map(farmer,FarmerDTO.class);
    }

    @Override
    public FarmerDTO findById(String id) {
        Optional<Farmer> farmerOptional = farmerDAO.findById(id);
        if(farmerOptional.isPresent()){
            return copyToDTO(farmerOptional.get());
        }
        return null;
    }

    @Override
    public List<FarmerDTO> findAll() {
        return farmerDAO.findAll().stream().map(this::copyToDTO).collect(Collectors.toList());
    }
}
