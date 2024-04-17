package org.example.guilhermezuriel.gestaodevagas.controllers;

import jakarta.validation.Valid;
import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @PostMapping("/")
    public void create(@Valid @RequestBody CandidateEntity candidate){
            System.out.println(candidate);
    }
}
