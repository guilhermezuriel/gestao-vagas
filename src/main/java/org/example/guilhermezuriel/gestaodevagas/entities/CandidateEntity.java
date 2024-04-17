package org.example.guilhermezuriel.gestaodevagas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaços")
    private String username;

    @Email(message = "O campo (email) deve conter um email válido")
    private String email;

    @Length(min = 10, max = 100, message = "Senha deve conter entre (10) e (100) caracteres")
    private String password;

    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime creationDate;


}
