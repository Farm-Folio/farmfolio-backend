package com.farm.farmfolio.controller;

import com.farm.farmfolio.dto.AppUserDTO;
import com.farm.farmfolio.dto.pagination.PaginationDTO;
import com.farm.farmfolio.dto.pagination.TableResponse;
import com.farm.farmfolio.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @PostMapping("/save")
    public ResponseEntity<AppUserDTO> save(@RequestBody AppUserDTO farmerDTO){
        return new ResponseEntity<>(userService.save(farmerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/by-id")
    public ResponseEntity<AppUserDTO> findById(@RequestParam("id") String id){
        return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUserDTO>> findAll(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/by-id-type")
    public ResponseEntity<AppUserDTO> findByIdAndType(@RequestParam("id") String id,@RequestParam("type") Integer type){
        return new ResponseEntity<>(userService.findByIdAndType(id,type),HttpStatus.OK);
    }

    @GetMapping("/all-by-type")
    public ResponseEntity<List<AppUserDTO>> findAllByType(@RequestParam("type") Integer type){
        return new ResponseEntity<>(userService.findAllByType(type),HttpStatus.OK);
    }

    @PostMapping("/page")
    public ResponseEntity<TableResponse> findPage(@RequestBody PaginationDTO pagination)  {
        return new ResponseEntity<>(userService.findPagination(pagination), HttpStatus.OK);
    }
}
