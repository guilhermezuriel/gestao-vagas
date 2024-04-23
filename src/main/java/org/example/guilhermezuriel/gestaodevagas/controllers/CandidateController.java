package org.example.guilhermezuriel.gestaodevagas.controllers;

import jakarta.validation.Valid;
import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.CandidateService;
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
}
