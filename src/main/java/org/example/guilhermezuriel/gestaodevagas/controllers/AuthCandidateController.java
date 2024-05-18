package org.example.guilhermezuriel.gestaodevagas.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.CandidateService;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateRequestDto;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateResponseDto;
import org.example.guilhermezuriel.gestaodevagas.service.company.dto.AuthCompanyResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Autenticar Candidato")
public class AuthCandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação", description = "Endpoint responsável por autenticar a empresa")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AuthCandidateResponseDto.class)))
    public ResponseEntity<Object> authenticateCandidate(@RequestBody AuthCandidateRequestDto authCandidateRequestDto) {
        try{
            var token = candidateService.authenticate(authCandidateRequestDto);
            return ResponseEntity.ok().body(token);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
