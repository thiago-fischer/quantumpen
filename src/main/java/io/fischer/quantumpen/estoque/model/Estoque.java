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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Caneta getProduto() {
        return produto;
    }

    public void setProduto(Caneta produto) {
        this.produto = produto;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
