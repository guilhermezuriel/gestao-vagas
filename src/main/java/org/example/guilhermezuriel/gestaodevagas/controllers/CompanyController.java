package org.example.guilhermezuriel.gestaodevagas.controllers;


import jakarta.validation.Valid;
import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;

import org.example.guilhermezuriel.gestaodevagas.service.company.CompanyService;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;


    @GetMapping("/")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        try {
            var list = this.companyService.getAllCompanies();
            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCompany(@Valid  @RequestBody CompanyEntity companyEntity){
        System.out.println("ENTREI AQUI");
            var result = this.companyService.create(companyEntity);
            return ResponseEntity.ok().body(companyEntity);
    }

}
