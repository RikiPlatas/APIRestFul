package com.dex.testRestFul.excepciones;

public class NoSeHaEncontradoException extends Exception {

    public NoSeHaEncontradoException(String mensaje){
        super(mensaje);
    }

    public NoSeHaEncontradoException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
