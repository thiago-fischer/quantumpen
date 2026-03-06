package io.fischer.quantumpen.funcionarios.model;

import io.fischer.quantumpen.shared.model.Endereco;
import io.fischer.quantumpen.users.model.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    private LocalDate dataNascimento;

    private LocalDate dataContratacao = LocalDate.now();

    @NotBlank
    private String cargo;

    @NotNull
    private Double salario;

    @Embedded
    @Valid
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuarios_id")
    private User user;

    public Funcionario() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public String getCargo() {
        return cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setUser(User user) {
        this.user = user;
    }
}