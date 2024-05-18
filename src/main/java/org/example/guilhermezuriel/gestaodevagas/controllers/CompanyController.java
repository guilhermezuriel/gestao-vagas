package org.example.guilhermezuriel.gestaodevagas.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;

import org.example.guilhermezuriel.gestaodevagas.service.company.CompanyService;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.CompanyDto;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/company")
@Tag(name = "Empresa", description = "Endpoints relacionado as ações da empresa")
public class CompanyController {
    @Autowired
    private CompanyService companyService;


    @GetMapping("/")
    @Operation(summary = "Listagem de empresas", description = "Endpoint responsável por listar todas as empresas")
    @ApiResponse(responseCode = "200", description = "", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CompanyDto.class))))
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        try {
            var list = this.companyService.getAllCompanies();
            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Cadastro de empresa", description = "Endpoint responsável por criar a empresa no sistema")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = CompanyDto.class)))
    public ResponseEntity<Object> createCompany(@Valid @RequestBody CompanyEntity companyEntity){
       try {
           var result = this.companyService.create(companyEntity);
           return ResponseEntity.status(HttpStatus.CREATED).body(result);
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       }

    }

}
