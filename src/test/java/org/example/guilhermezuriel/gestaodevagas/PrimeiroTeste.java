package org.example.guilhermezuriel.gestaodevagas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeiroTeste {

    public static double calculate(double num1, double num2){
        return num1 + num2;
    }
    @Test
    public void deve_ser_possivel_calcular_dois_numeros(){
        var resultado = calculate(1, 2);
        assertEquals(resultado, 3);
    }
}
