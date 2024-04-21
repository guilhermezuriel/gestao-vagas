package org.example.guilhermezuriel.gestaodevagas.useCases.company;

import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.UserFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public void execute(CompanyEntity companyEntity){
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user)->{throw new UserFoundException();
                });
        this.companyRepository.save(companyEntity);
    }
}
