package org.example.guilhermezuriel.gestaodevagas.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.guilhermezuriel.gestaodevagas.entities.candidate.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.CandidateService;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.CandidateDTO;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.ProfileCandidateResponseDto;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Endpoints relacionado as ações do candidato")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/")
    @Operation(summary = "Cadastro do Candidato", description = "Endpoint responsável pela criação do candidato")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = CandidateDTO.class)))
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate){
            try{
                CandidateDTO result = this.candidateService.create(candidate);
                return ResponseEntity.status(HttpStatus.CREATED).body(result);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }



    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Retornar perfil", description = "Endpoint responsável por retornar o perfil do candidato")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProfileCandidateResponseDto.class)))
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> getProfile(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.candidateService.profile(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = JobDto.class))))
    @Operation(summary = "Listagem de jobs",description = "Endpoint responsável pela listagem de jobs, a partir da description")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> getJobs(@RequestParam String description) {
        var jobs = this.candidateService.listAllJobsByFilter(description);
        return ResponseEntity.ok(jobs);
   }
}
