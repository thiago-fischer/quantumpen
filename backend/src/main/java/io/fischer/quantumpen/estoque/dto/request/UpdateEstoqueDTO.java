package io.fischer.quantumpen.estoque.dto.request;

import io.fischer.quantumpen.estoque.model.Estoque;
import io.fischer.quantumpen.produtos.model.Caneta;

import java.sql.Date;

public record UpdateEstoqueDTO(
        Long produtoId,
        String deposito,
        String lote,
        Date dataFabricacao,
        int quantidade
) {
    public void applyTo(Estoque entity, Caneta produto) {
        entity.setProduto(produto);
        entity.setDeposito(deposito);
        entity.setLote(lote);
        entity.setDataFabricacao(dataFabricacao);
        entity.setQuantidade(quantidade);
    }
}
