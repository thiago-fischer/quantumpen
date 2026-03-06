package io.fischer.quantumpen.shared.mapper;

import io.fischer.quantumpen.shared.dto.TelefoneDTO;
import io.fischer.quantumpen.shared.model.Telefone;

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
