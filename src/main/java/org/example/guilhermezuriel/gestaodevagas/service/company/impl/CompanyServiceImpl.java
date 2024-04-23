package org.example.guilhermezuriel.gestaodevagas.service.company.impl;

import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.UserFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.CompanyRepository;
import org.example.guilhermezuriel.gestaodevagas.service.company.CompanyService;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyDto create(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user)->{throw new UserFoundException();
                });
        CompanyEntity company = this.companyRepository.save(companyEntity);
        return new CompanyDto(company);
    }
}
