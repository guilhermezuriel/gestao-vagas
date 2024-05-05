package org.example.guilhermezuriel.gestaodevagas.service.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.guilhermezuriel.gestaodevagas.entities.CandidateEntity;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDto {
    private String description;
    private String username;
    private String email;
    private UUID id;
    private String name;

    public ProfileCandidateResponseDto(CandidateEntity candidate) {
        this.description = candidate.getDescription();
        this.username = candidate.getUsername();
        this.email = candidate.getEmail();
        this.id = candidate.getId();
        this.name = candidate.getName();
    }
}
