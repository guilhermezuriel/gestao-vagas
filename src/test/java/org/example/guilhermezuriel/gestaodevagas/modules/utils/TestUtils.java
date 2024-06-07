package org.example.guilhermezuriel.gestaodevagas.modules.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtils {
    public static String objectToJson(Object object) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static String generateToken(UUID idCompany){
        Algorithm algorithm = Algorithm.HMAC256("JAVAGAS_@123#");
        var expiresIn = Instant.now().plus(Duration.ofHours(60));
        var token = JWT.create().withIssuer("javagas")
                .withSubject(idCompany.toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);
        return token;
    }
}
