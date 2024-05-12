package org.example.guilhermezuriel.gestaodevagas.service.company.impl;


import com.auth0.jwt.algorithms.Algorithm;
import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.UserFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.CompanyRepository;
import org.example.guilhermezuriel.gestaodevagas.service.company.CompanyService;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyResponseDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.CompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

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
    public AuthCompanyResponseDto authCompany(AuthCompanyDto authCompanyDto) throws RuntimeException {
        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername()).orElseThrow(()-> new RuntimeException("Username/password incorrect"));

        var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(),company.getPassword());

        if(!passwordMatches){
            throw new RuntimeException("Username/password incorrect");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = com.auth0.jwt.JWT.create().
                withIssuer("javagas")
                .withSubject(company.getId().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);
        return AuthCompanyResponseDto.builder().acess_token(token).expires_in(expiresIn.toEpochMilli()).build();
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        List<CompanyEntity> list  = this.companyRepository.findAll();
        return list.stream().map(CompanyDto::new).toList();
    }
}
