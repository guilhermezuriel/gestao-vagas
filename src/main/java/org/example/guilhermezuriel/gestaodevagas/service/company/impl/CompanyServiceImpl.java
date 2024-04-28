package org.example.guilhermezuriel.gestaodevagas.service.company.impl;


import com.auth0.jwt.algorithms.Algorithm;
import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.UserFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.CompanyRepository;
import org.example.guilhermezuriel.gestaodevagas.service.company.CompanyService;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Value("${security.token.secret}")
    private String secretKey;

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
    @Override
    public String authCompany(AuthCompanyDto authCompanyDto) throws RuntimeException {
        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername()).orElseThrow(()->{
            throw new RuntimeException("Username/password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(),company.getPassword());

        if(!passwordMatches){
            throw new RuntimeException("Username/password incorrect");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = com.auth0.jwt.JWT.create().
                withIssuer("javagas").
                withSubject(company.getId().toString())
                .sign(algorithm);

        return token;
    }
}
