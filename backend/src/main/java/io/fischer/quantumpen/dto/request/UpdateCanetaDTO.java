package io.fischer.quantumpen.dto.request;

import io.fischer.quantumpen.model.Caneta;

public record UpdateCanetaDTO(
        String nome,
        String marca,
        String corTinta,
        String tipo,
        String colecao,
        double espessuraPonta,
        double preco,
        int qtdEstoque,
        String codBarra,
        double peso,
        String materialCorpo,
        String urlImagem,
        String corCorpo,
        boolean recarregavel,
        double durabilidade,
        String tipoPonta
) {}
