package org.example.guilhermezuriel.gestaodevagas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Gestão de vagas",
		description = "Api responsável pela gestão de vagas"
))
public class GestaodevagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaodevagasApplication.class, args);
	}

}
