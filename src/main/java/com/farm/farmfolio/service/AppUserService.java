package com.farm.farmfolio.service;

import com.farm.farmfolio.domain.AppUser;
import com.farm.farmfolio.dto.AppUserDTO;
import com.farm.farmfolio.dto.pagination.PaginationDTO;
import com.farm.farmfolio.dto.pagination.TableResponse;

import java.util.List;

public interface AppUserService {

    AppUserDTO save(AppUserDTO farmerDTO);

    AppUserDTO findById(String id);

    List<AppUserDTO> findAll();

    AppUserDTO findByIdAndType(String id, Integer type);

    List<AppUserDTO> findAllByType(Integer type);

    TableResponse findPagination(PaginationDTO pagination);

}
