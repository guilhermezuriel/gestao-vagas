package org.example.guilhermezuriel.gestaodevagas.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.example.guilhermezuriel.gestaodevagas.entities.company.JobEntity;
import org.example.guilhermezuriel.gestaodevagas.service.job.JobService;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.CreateJobDto;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
@Tag(name = "Vagas", description = "Endpoints relacionados as vagas")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Cadastro de vaga", description = "Endpoint responsável por realizar o cadastro da vaga")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = JobDto.class)))
    public ResponseEntity<Object> createJob(@RequestBody CreateJobDto createJobDto, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        var jobEntity = JobEntity.builder()
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDto.getDescription())
                .level(createJobDto.getLevel())
                .benefits(createJobDto.getBenefits())
                .build();
        try {
            JobDto result = this.jobService.create(jobEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
