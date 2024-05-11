package org.example.guilhermezuriel.gestaodevagas.controllers;

import org.example.guilhermezuriel.gestaodevagas.service.candidate.CandidateService;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("candidate")
public class AuthCandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/auth")
    public ResponseEntity<Object> authenticateCandidate(@RequestBody AuthCandidateRequestDto authCandidateRequestDto) {
        try{
            var token = candidateService.authenticate(authCandidateRequestDto);
            return ResponseEntity.ok().body(token);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
