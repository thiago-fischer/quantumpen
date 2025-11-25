package io.fischer.quantumpen.estoque.model;

import io.fischer.quantumpen.produtos.model.Caneta;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Caneta produto;
    private String deposito;
    private String lote;
    private Date dataFabricacao;
    private int quantidade;
}
