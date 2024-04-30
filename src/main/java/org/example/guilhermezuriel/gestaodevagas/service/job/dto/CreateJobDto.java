package org.example.guilhermezuriel.gestaodevagas.service.job.dto;

import lombok.Data;
import org.example.guilhermezuriel.gestaodevagas.enums.LevelEnum;

@Data
public class CreateJobDto {
    private String description;
    private String benefits;
    private LevelEnum level;
}
