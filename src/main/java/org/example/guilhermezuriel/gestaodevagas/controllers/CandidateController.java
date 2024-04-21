package org.example.guilhermezuriel.gestaodevagas.controllers;

import jakarta.validation.Valid;
import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.useCases.candidate.CreateCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;


    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate){
            try{
                var result = this.createCandidateUseCase.execute(candidate);
                return ResponseEntity.ok().body(result);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
    }
}
