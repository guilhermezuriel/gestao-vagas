package org.example.guilhermezuriel.gestaodevagas.service.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {

    private String name;
    private String username;
    private String email;
    private String description;
    private String curriculum;

    public CandidateDTO(CandidateEntity candidate) {
        this.name = candidate.getName();
        this.username = candidate.getUsername();
        this.email = candidate.getEmail();
        this.description = candidate.getDescription();
        this.curriculum = candidate.getCurriculum();
    }
}
