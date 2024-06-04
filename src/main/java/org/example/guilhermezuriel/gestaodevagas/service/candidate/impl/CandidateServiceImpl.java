package org.example.guilhermezuriel.gestaodevagas.service.candidate.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.example.guilhermezuriel.gestaodevagas.entities.candidate.ApplyJobEntity;
import org.example.guilhermezuriel.gestaodevagas.entities.candidate.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.entities.company.JobEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.JobNotFoundException;
import org.example.guilhermezuriel.gestaodevagas.exceptions.UserFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.ApplyJobRepository;
import org.example.guilhermezuriel.gestaodevagas.repositories.CandidateRepository;
import org.example.guilhermezuriel.gestaodevagas.repositories.JobRepository;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.CandidateService;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.*;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CandidateServiceImpl implements CandidateService {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    private ApplyJobRepository applyJobRepository;
    private CandidateRepository candidateRepository;
    private PasswordEncoder passwordEncoder;
    private JobRepository jobRepository;

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

    @Override
    public AuthCandidateResponseDto authenticate(AuthCandidateRequestDto authCandidateRequestDto) throws RuntimeException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDto.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username/password incorrect"));
        var passwordMatches = passwordEncoder.matches(authCandidateRequestDto.getPassword(), candidate.getPassword());
        if (!passwordMatches) {
            throw new RuntimeException("Username/password incorrect");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);
        System.out.println(token);
        return AuthCandidateResponseDto.builder().acess_token(token).expires_in(expiresIn.toEpochMilli()).build();
    }

    @Override
    public ProfileCandidateResponseDto profile(UUID idCandidate){
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(()-> new UsernameNotFoundException("Candidate not found"));
        return new ProfileCandidateResponseDto(candidate);
    }

    @Override
    public List<JobDto> listAllJobsByFilter(String description){
        List<JobEntity> jobsEntities = this.jobRepository.findAllByDescriptionContaining(description);
        return jobsEntities.stream().map(JobDto::new).toList();
    }

    public ApplyJobResponseDto applyJobByCandidate(UUID candidateId, UUID jobId){
        var candidate = this.candidateRepository.findById(candidateId).orElseThrow(()->new UsernameNotFoundException("Candidate not found"));
        var job = this.jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);
        var appliedjob = this.applyJobRepository.save(ApplyJobEntity.builder().jobId(jobId).candidateId(candidateId).build());
        return new ApplyJobResponseDto(appliedjob);
    }
}
