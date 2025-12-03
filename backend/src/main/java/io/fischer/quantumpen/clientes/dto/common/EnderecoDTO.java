package io.fischer.quantumpen.clientes.dto.common;

import io.fischer.quantumpen.clientes.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank String logradouro,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank String estado,
        @NotBlank String cep
) {
    public Endereco toEntity() {
        Endereco entity = new Endereco();
        entity.setLogradouro(logradouro);
        entity.setBairro(bairro);
        entity.setCidade(cidade);
        entity.setEstado(estado);
        entity.setCep(cep);
        return entity;
    }
}
