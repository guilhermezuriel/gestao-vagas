package org.example.guilhermezuriel.gestaodevagas.exceptions;


public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(){
        super("Company não existe");
    }
}

