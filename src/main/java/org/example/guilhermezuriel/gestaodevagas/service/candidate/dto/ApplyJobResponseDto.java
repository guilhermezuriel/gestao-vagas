package org.example.guilhermezuriel.gestaodevagas.service.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.guilhermezuriel.gestaodevagas.entities.candidate.ApplyJobEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApplyJobResponseDto {

    private UUID id;
    private UUID candidateId;
    private UUID jobId;
    private LocalDateTime createdAt;

    public ApplyJobResponseDto(ApplyJobEntity applyJobEntity){
                this.id = applyJobEntity.getId();
                this.candidateId = applyJobEntity.getCandidateId();
                this.jobId = applyJobEntity.getJobId();
                this.createdAt = applyJobEntity.getCreatedAt();
    }
}
