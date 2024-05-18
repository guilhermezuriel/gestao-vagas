package org.example.guilhermezuriel.gestaodevagas.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.guilhermezuriel.gestaodevagas.service.company.CompanyService;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Tag(name = "Autenticar Empresa")
public class AuthCompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação", description = "Endpoint responsável por autenticar a empresa")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AuthCompanyResponseDto.class)))
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDto authCompanyDto){
        try {
            var result = this.companyService.authCompany(authCompanyDto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
