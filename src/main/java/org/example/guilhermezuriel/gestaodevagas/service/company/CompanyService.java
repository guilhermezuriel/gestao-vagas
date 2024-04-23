package org.example.guilhermezuriel.gestaodevagas.service.company;

import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.CompanyDto;

public interface CompanyService {
    public CompanyDto create(CompanyEntity companyEntity);
}
