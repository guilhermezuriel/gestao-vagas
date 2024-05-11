package org.example.guilhermezuriel.gestaodevagas.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.CandidateService;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.dto.AuthCandidateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;


    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate){
            try{
                var result = this.candidateService.create(candidate);
                return ResponseEntity.ok().body(result);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }



    @GetMapping("/profile")
    public ResponseEntity<Object> getProfile(HttpServletRequest request) {
        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.candidateService.profile(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
