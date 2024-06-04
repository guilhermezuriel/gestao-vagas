package org.example.guilhermezuriel.gestaodevagas.exceptions;


public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(){
        super("Job não existe");
    }
}
