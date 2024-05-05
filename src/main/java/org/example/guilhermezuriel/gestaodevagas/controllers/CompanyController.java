package org.example.guilhermezuriel.gestaodevagas.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;

import org.example.guilhermezuriel.gestaodevagas.service.company.CompanyService;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @PostMapping("/create")
    public ResponseEntity<Object> createCompany(@Valid  @RequestBody CompanyEntity companyEntity){
        try {
            var result = this.companyService.create(companyEntity);
            return ResponseEntity.ok().body(companyEntity);
        }catch (Exception e){
            e.printStackTrace();
           return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> create(HttpServletRequest request,  @RequestBody AuthCompanyDto authCompanyDto) throws AuthenticationException {
        try {
            var result = this.companyService.authCompany(authCompanyDto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
