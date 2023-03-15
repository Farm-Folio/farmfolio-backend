package com.farm.farmfolio.service;

import com.farm.farmfolio.dto.FarmerDTO;

import java.util.List;

public interface FarmerService {

    FarmerDTO save(FarmerDTO farmerDTO);

    FarmerDTO findById(String id);

    List<FarmerDTO> findAll();

}
