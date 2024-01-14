package com.proj.loja.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Produto não encontrado")
public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(Throwable cause){
        super(cause);
    }
    
    public ProdutoNotFoundException(String msg){
        super(msg);
    }

    public ProdutoNotFoundException(String msg, Throwable cause){
        super(msg, cause, false, true);
    }
}
