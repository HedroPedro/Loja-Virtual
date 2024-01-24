package com.proj.loja.model;

import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public enum TipoUsuario {
    Cliente(0, "Cliente"),
    Vendendor(1, "Vendendor");
    
    @Id
    private Integer id;
    private String name;
    
    private TipoUsuario(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public static TipoUsuario fromId(Integer id) {
        switch (id) {
            case 0:
                return TipoUsuario.Cliente;
            case 1:
                return TipoUsuario.Vendendor;
            default:
                throw new IllegalArgumentException("Id ["+ id +"] não suportado");
        }
    }
}
