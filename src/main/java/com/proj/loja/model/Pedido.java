package com.proj.loja.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tb_Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @Column(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "produtos_ids")
    List<Produto> produtos;

    private Timestamp dataCriação;

    private Date dataEntrega;
}
