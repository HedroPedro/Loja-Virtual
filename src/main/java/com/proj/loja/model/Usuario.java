package com.proj.loja.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tb_Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="tipoUsuario")
    private TipoUsuario tipoUsuario;
    private String name;
    @Column(unique = true)
    private String email;
    private String senha;
    @Column(unique = true)
    private String CPF;
    private String salt;
}
