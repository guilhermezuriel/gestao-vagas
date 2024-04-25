package org.example.guilhermezuriel.gestaodevagas.service.company.impl;

import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.UserFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.CompanyRepository;
import org.example.guilhermezuriel.gestaodevagas.service.company.CompanyService;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CompanyDto create(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user)->{throw new UserFoundException();
                });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        CompanyEntity company = this.companyRepository.save(companyEntity);
        return new CompanyDto(company);
    }

    public void authCompany(AuthCompanyDto authCompanyDto) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername()).orElseThrow(()->{
            throw new UsernameNotFoundException("Company not found");
        });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(),company.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }


    }
}
