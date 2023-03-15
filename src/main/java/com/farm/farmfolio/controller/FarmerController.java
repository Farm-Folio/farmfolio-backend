package com.farm.farmfolio.controller;

import com.farm.farmfolio.domain.Farmer;
import com.farm.farmfolio.dto.FarmerDTO;
import com.farm.farmfolio.service.FarmerService;
import org.apache.coyote.Response;
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
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping("/save")
    public ResponseEntity<FarmerDTO> save(@RequestBody FarmerDTO farmerDTO){
        return new ResponseEntity<>(farmerService.save(farmerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/by-id")
    public ResponseEntity<FarmerDTO> findById(@RequestParam("id") String id){
        return new ResponseEntity<>(farmerService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FarmerDTO>> findAll(){
        return new ResponseEntity<>(farmerService.findAll(),HttpStatus.OK);
    }
}
