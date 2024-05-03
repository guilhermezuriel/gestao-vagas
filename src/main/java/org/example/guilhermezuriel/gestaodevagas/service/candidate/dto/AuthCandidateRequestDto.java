package org.example.guilhermezuriel.gestaodevagas.service.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthCandidateRequestDto{
    private String username;
    private String password;
}
