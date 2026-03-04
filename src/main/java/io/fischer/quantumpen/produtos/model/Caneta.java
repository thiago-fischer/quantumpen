package io.fischer.quantumpen.produtos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "canetas")
public class Caneta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String marca;
    private String corTinta;
    private String tipo;
    private String colecao;
    private double espessuraPonta;
    private double preco;
    private String codBarra;
    private double peso;
    private String materialCorpo;
    private String urlImagem;
    private String corCorpo;
    private boolean recarregavel;
    private double durabilidade;
    private String tipoPonta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCorTinta() {
        return corTinta;
    }

    public void setCorTinta(String corTinta) {
        this.corTinta = corTinta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColecao() {
        return colecao;
    }

    public void setColecao(String colecao) {
        this.colecao = colecao;
    }

    public double getEspessuraPonta() {
        return espessuraPonta;
    }

    public void setEspessuraPonta(double espessuraPonta) {
        this.espessuraPonta = espessuraPonta;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getMaterialCorpo() {
        return materialCorpo;
    }

    public void setMaterialCorpo(String materialCorpo) {
        this.materialCorpo = materialCorpo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getCorCorpo() {
        return corCorpo;
    }

    public void setCorCorpo(String corCorpo) {
        this.corCorpo = corCorpo;
    }

    public boolean isRecarregavel() {
        return recarregavel;
    }

    public void setRecarregavel(boolean recarregavel) {
        this.recarregavel = recarregavel;
    }

    public double getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(double durabilidade) {
        this.durabilidade = durabilidade;
    }

    public String getTipoPonta() {
        return tipoPonta;
    }

    public void setTipoPonta(String tipoPonta) {
        this.tipoPonta = tipoPonta;
    }
}

