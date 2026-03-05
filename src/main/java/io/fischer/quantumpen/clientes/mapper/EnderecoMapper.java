package io.fischer.quantumpen.clientes.mapper;

import io.fischer.quantumpen.clientes.dto.common.EnderecoDTO;
import io.fischer.quantumpen.clientes.model.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoDTO dto) {
        Endereco entity = new Endereco();
        entity.setLogradouro(dto.logradouro());
        entity.setBairro(dto.bairro());
        entity.setCidade(dto.cidade());
        entity.setEstado(dto.estado());
        entity.setCep(dto.cep());
        return entity;
    }

    public static EnderecoDTO fromEntity(Endereco entity) {
        return new EnderecoDTO(
                entity.getLogradouro(),
                entity.getBairro(),
                entity.getCidade(),
                entity.getEstado(),
                entity.getCep()
        );
    }
}
