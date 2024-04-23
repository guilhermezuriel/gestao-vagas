package org.example.guilhermezuriel.gestaodevagas.service.job.dto;

import org.example.guilhermezuriel.gestaodevagas.enums.LevelEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class JobDto {
    private UUID id;
    private String description;
    private String benefits;
    private LevelEnum level;
    private UUID companyId;
    private LocalDateTime createdAt;
}
