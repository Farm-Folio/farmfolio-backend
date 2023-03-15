package com.farm.farmfolio.dao.impl;

import com.farm.farmfolio.dao.AppUserDAO;
import com.farm.farmfolio.domain.AppUser;
import com.farm.farmfolio.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AppUserDAOImpl implements AppUserDAO {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public AppUser save(AppUser farmer) {
        return userRepository.save(farmer);
    }

    @Override
    public Optional<AppUser> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<AppUser> findPagination(Specification specification, Pageable pageable) {
        return userRepository.findAll(specification,pageable);
    }

    @Override
    public AppUser findByIdAndType(String id, Integer type) {
        return userRepository.findByIdAndType(id,type);
    }

    @Override
    public List<AppUser> findAllByType(Integer type) {
        return userRepository.findAllByType(type);
    }
}
