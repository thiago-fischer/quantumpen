package io.fischer.quantumpen.dto.response;

import io.fischer.quantumpen.model.Caneta;
import java.util.ArrayList;
import java.util.List;

public record CanetaResponseDTO(
        Long id,
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
) {
    public static CanetaResponseDTO fromEntity(Caneta c) {
        return new CanetaResponseDTO(
                c.getId(),
                c.getNome(),
                c.getMarca(),
                c.getCorTinta(),
                c.getTipo(),
                c.getColecao(),
                c.getEspessuraPonta(),
                c.getPreco(),
                c.getQtdEstoque(),
                c.getCodBarra(),
                c.getPeso(),
                c.getMaterialCorpo(),
                c.getUrlImagem(),
                c.getCorCorpo(),
                c.isRecarregavel(),
                c.getDurabilidade(),
                c.getTipoPonta()
        );
    }

    public static List<CanetaResponseDTO> fromEntities(List<Caneta> list) {
        List<CanetaResponseDTO> listDto = new ArrayList<>();
        for(Caneta c : list) listDto.add(fromEntity(c));
        return listDto;
    }
}

