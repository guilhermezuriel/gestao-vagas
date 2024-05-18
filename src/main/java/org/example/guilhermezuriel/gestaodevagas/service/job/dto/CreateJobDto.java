package org.example.guilhermezuriel.gestaodevagas.service.job.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.guilhermezuriel.gestaodevagas.enums.LevelEnum;

@Data
public class CreateJobDto {
    @Schema(example = "Vaga para pessoa desenvolvedora júnior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Gympass, plano de saúde", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private LevelEnum level;
}
