package io.fischer.quantumpen.shared.mapper;

import io.fischer.quantumpen.shared.dto.EnderecoDTO;
import io.fischer.quantumpen.shared.model.Endereco;

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
