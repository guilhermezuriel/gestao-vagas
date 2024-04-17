package org.example.guilhermezuriel.gestaodevagas.useCases;

import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.UserFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;


    public CandidateEntity execute(CandidateEntity candidate) throws UserFoundException {
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user)->{
                    throw new UserFoundException();
                });
        return this.candidateRepository.save(candidate);
    }
}
