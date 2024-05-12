package org.example.guilhermezuriel.gestaodevagas.service.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthCompanyResponseDto {
    private String acess_token;
    private Long expires_in;
}
