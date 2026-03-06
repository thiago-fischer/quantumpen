package io.fischer.quantumpen.clientes.mapper;

import io.fischer.quantumpen.clientes.dto.common.TelefoneDTO;
import io.fischer.quantumpen.clientes.model.Telefone;

public class TelefoneMapper {

    public static Telefone toEntity(TelefoneDTO dto) {
        Telefone entity = new Telefone();
        entity.setTelefone(dto.telefone());
        return entity;
    }

    public static TelefoneDTO fromEntity(Telefone entity) {
        return new TelefoneDTO(
                entity.getTelefone()
        );
    }
}
