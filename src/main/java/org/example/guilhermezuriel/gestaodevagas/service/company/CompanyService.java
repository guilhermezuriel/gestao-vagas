package org.example.guilhermezuriel.gestaodevagas.service.company;

import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyResponseDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto create(CompanyEntity companyEntity);
     AuthCompanyResponseDto authCompany(AuthCompanyDto authCompanyDto);
     List<CompanyDto> getAllCompanies();
}
