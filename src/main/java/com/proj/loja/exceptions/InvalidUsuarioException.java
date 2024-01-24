package com.proj.loja.exceptions;

public class InvalidUsuarioException extends RuntimeException{
    public InvalidUsuarioException(String message){
        super(message);
    }

    public InvalidUsuarioException(String message, Throwable cause){
        super(message, cause, false, true);
    }
}
