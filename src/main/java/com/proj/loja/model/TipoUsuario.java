package com.proj.loja.model;

import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public enum TipoUsuario {
    Visitante(0, "Visitante"),
    Cliente(1, "Cliente"),
    Vendendor(2, "Vendendor");
    
    @Id
    @ReadOnlyProperty
    private Integer id;
    @ReadOnlyProperty
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
                return TipoUsuario.Visitante;
            case 1:
                return TipoUsuario.Cliente;
            case 2:
                return TipoUsuario.Vendendor;
            default:
                throw new IllegalArgumentException("Id ["+ id +"] não suportado");
        }
    }
}
