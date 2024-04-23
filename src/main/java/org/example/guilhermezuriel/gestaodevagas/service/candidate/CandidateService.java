package org.example.guilhermezuriel.gestaodevagas.service.candidate;

import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.CandidateDTO;

public interface CandidateService {
    public CandidateDTO create(CandidateEntity candidateEntity);
}
