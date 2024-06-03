package org.example.guilhermezuriel.gestaodevagas.service.candidate;

import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateRequestDto;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateResponseDto;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.CandidateDTO;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.ProfileCandidateResponseDto;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.JobDto;

import java.util.List;
import java.util.UUID;

public interface CandidateService {
    CandidateDTO create(CandidateEntity candidateEntity);
    AuthCandidateResponseDto authenticate(AuthCandidateRequestDto authCandidateRequestDto);
    ProfileCandidateResponseDto profile(UUID idCandidate);
    List<JobDto> listAllJobsByFilter(String description);
    void applyJobByCandidate(UUID candidateId, UUID jobId);
}
