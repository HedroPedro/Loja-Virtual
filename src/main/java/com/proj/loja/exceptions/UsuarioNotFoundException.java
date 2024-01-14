package com.proj.loja.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Usuario não encontrado")
public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(Throwable cause){
        super(cause);
    }
    
    public UsuarioNotFoundException(String msg){
        super(msg);
    }

    public UsuarioNotFoundException(String msg, Throwable cause){
        super(msg, cause, false, true);
    }
}
