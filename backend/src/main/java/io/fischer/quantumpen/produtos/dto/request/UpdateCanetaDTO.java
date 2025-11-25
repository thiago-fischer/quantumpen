package io.fischer.quantumpen.produtos.dto.request;

import io.fischer.quantumpen.produtos.model.Caneta;

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
) {
    public void applyTo(Caneta c) {
        c.setNome(nome);
        c.setMarca(marca);
        c.setCorTinta(corTinta);
        c.setTipo(tipo);
        c.setColecao(colecao);
        c.setEspessuraPonta(espessuraPonta);
        c.setPreco(preco);
        c.setQtdEstoque(qtdEstoque);
        c.setCodBarra(codBarra);
        c.setPeso(peso);
        c.setMaterialCorpo(materialCorpo);
        c.setUrlImagem(urlImagem);
        c.setCorCorpo(corCorpo);
        c.setRecarregavel(recarregavel);
        c.setDurabilidade(durabilidade);
        c.setTipoPonta(tipoPonta);
    }
}
