package org.example.guilhermezuriel.gestaodevagas.service.candidate;

import org.example.guilhermezuriel.gestaodevagas.entities.candidate.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.*;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.JobDto;

import java.util.List;
import java.util.UUID;

public interface CandidateService {
    CandidateDTO create(CandidateEntity candidateEntity);
    AuthCandidateResponseDto authenticate(AuthCandidateRequestDto authCandidateRequestDto);
    ProfileCandidateResponseDto profile(UUID idCandidate);
    List<JobDto> listAllJobsByFilter(String description);
    ApplyJobResponseDto applyJobByCandidate(UUID candidateId, UUID jobId);
}
