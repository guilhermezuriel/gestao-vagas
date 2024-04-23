package org.example.guilhermezuriel.gestaodevagas.service.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private UUID id;
    private String username;
    private String email;
    private String name;
    private String description;

    public CompanyDto(CompanyEntity company){
        this.id = company.getId();
        this.username = company.getUsername();
        this.email = company.getEmail();
        this.name = company.getName();
        this.description = company.getDescription();
    }
}
