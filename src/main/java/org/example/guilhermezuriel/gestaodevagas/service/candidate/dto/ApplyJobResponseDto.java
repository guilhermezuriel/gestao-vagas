package org.example.guilhermezuriel.gestaodevagas.service.candidate.dto;

import org.example.guilhermezuriel.gestaodevagas.entities.candidate.ApplyJobEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record ApplyJobResponseDto(
        UUID candidateId, UUID jobId, LocalDateTime createdAt
) {
    public ApplyJobResponseDto(ApplyJobEntity applyJobEntity){
        this(
                applyJobEntity.getCandidateId(),
                applyJobEntity.getJobId(),
                applyJobEntity.getCreatedAt()
        );
    }
}
