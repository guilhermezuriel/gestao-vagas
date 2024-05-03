package org.example.guilhermezuriel.gestaodevagas.service.candidate;

import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateRequestDto;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateResponseDto;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.CandidateDTO;

public interface CandidateService {
    CandidateDTO create(CandidateEntity candidateEntity);
    AuthCandidateResponseDto authenticate(AuthCandidateRequestDto authCandidateRequestDto);
}
