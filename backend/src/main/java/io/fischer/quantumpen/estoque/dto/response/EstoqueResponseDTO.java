package io.fischer.quantumpen.estoque.dto.response;

import io.fischer.quantumpen.estoque.model.Estoque;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public record EstoqueResponseDTO(
        Long id,
        Long produtoId,
        String deposito,
        String lote,
        Date dataFabricacao,
        int quantidade
) {
    public static EstoqueResponseDTO fromEntity(Estoque entity) {
        return new EstoqueResponseDTO(
                entity.getId(),
                entity.getProduto().getId(),
                entity.getDeposito(),
                entity.getLote(),
                entity.getDataFabricacao(),
                entity.getQuantidade()
        );
    }

    public static List<EstoqueResponseDTO> fromEntities(List<Estoque> entities) {
        List<EstoqueResponseDTO> listDTO = new ArrayList<>();
        for(Estoque entity : entities) listDTO.add(fromEntity(entity));
        return listDTO;
    }
}
