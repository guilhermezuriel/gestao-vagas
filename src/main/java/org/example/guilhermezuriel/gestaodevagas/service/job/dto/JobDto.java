package org.example.guilhermezuriel.gestaodevagas.service.job.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.guilhermezuriel.gestaodevagas.entities.company.JobEntity;
import org.example.guilhermezuriel.gestaodevagas.enums.LevelEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private UUID id;
    private String description;
    private String benefits;
    private LevelEnum level;
    private UUID companyId;
    private LocalDateTime createdAt;

    public JobDto (JobEntity jobEntity){
        this.id = jobEntity.getId();
        this.description = jobEntity.getDescription();
        this.benefits = jobEntity.getBenefits();
        this.level = jobEntity.getLevel();
        this.companyId = jobEntity.getCompanyId();
        this.createdAt = jobEntity.getCreatedAt();
    }
}
