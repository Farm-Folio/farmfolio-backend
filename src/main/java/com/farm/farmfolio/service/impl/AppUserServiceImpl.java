package com.farm.farmfolio.service.impl;

import com.farm.farmfolio.dao.AppUserDAO;
import com.farm.farmfolio.domain.AppUser;
import com.farm.farmfolio.dto.AppUserDTO;
import com.farm.farmfolio.dto.pagination.PaginationDTO;
import com.farm.farmfolio.dto.pagination.TableResponse;
import com.farm.farmfolio.service.AppUserService;
import com.farm.farmfolio.specification.AppUserSpecification;
import com.farm.farmfolio.specification.BaseSpecification;
import com.farm.farmfolio.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    public AppUserDAO userDAO;

    @Override
    public AppUserDTO save(AppUserDTO farmerDTO) {
        AppUser user = Mapper.map(farmerDTO, AppUser.class);
        Mapper.setAuditable(user);
        userDAO.save(user);
        return farmerDTO;
    }

    private AppUserDTO copyToDTO(AppUser user){
        return Mapper.map(user, AppUserDTO.class);
    }

    @Override
    public AppUserDTO findById(String id) {
        Optional<AppUser> userOptional = userDAO.findById(id);
        if(userOptional.isPresent()){
            return copyToDTO(userOptional.get());
        }
        return null;
    }

    @Override
    public List<AppUserDTO> findAll() {
        return userDAO.findAll().stream().map(this::copyToDTO).collect(Collectors.toList());
    }

    @Override
    public AppUserDTO findByIdAndType(String id, Integer type) {
        AppUser user = userDAO.findByIdAndType(id,type);
        return copyToDTO(user);
    }

    @Override
    public List<AppUserDTO> findAllByType(Integer type) {
        return userDAO.findAllByType(type).stream().map(this::copyToDTO).collect(Collectors.toList());
    }

    @Override
    public TableResponse findPagination(PaginationDTO pagination) {
        Pageable paging = PageRequest.of(pagination.getPageNo() - 1, pagination.getPageSize());
        Page<AppUser> userPage = userDAO.findPagination(BaseSpecification.getSpec(pagination,AppUserSpecification.class),paging);
        return userPage.hasContent()
                ? new TableResponse(0, (int) userPage.getTotalElements(), (int) userPage.getTotalElements(),
                userPage.getContent().stream().map(this::copyToDTO).collect(Collectors.toList()))
                : new TableResponse(0, (int) userPage.getTotalElements(), (int) userPage.getTotalElements(),
                new ArrayList<>());
    }
}
