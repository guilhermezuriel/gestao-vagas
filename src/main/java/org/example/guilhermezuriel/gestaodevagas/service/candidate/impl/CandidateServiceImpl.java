package org.example.guilhermezuriel.gestaodevagas.service.candidate.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.UserFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.CandidateRepository;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.CandidateService;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateRequestDto;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateResponseDto;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;


@Service
public class CandidateServiceImpl implements CandidateService {

    @Value("${security.token.secret.candidate}")
    private String secretKey;


    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CandidateDTO create(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user)->{
                    throw new UserFoundException();
                });
        var password = this.passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);
        CandidateEntity candidate = this.candidateRepository.save(candidateEntity);
        return new CandidateDTO(candidate);
    }


    public AuthCandidateResponseDto authCandidate(AuthCandidateRequestDto authCandidateRequestDto) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDto.username())
                .orElseThrow(()-> new UsernameNotFoundException("Username/password incorrect"));
        var passwordMatches = passwordEncoder.matches(authCandidateRequestDto.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException("Username/password incorrect");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("candidate"))
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(algorithm);
        return AuthCandidateResponseDto.builder().acess_token(token).build();
    }
}
