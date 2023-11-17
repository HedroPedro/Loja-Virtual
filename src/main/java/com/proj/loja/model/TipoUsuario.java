package com.proj.loja.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "Tb_TipoUsuarios")
@AllArgsConstructor
@Getter
public enum TipoUsuario {
    VISITANTE(0, "Visitante"), CLIENTE(1, "Cliente"), VENDEDOR(2, "Vendedor");

    @Id
    private Integer id;
    private String name;
    

}
