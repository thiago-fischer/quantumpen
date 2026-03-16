package io.fischer.quantumpen.pedidos.dto.response;

public record ItemPedidoResponseDTO(

        Long produtoId,
        String nomeProduto,
        int quantidade,
        double precoUnitario,
        double subtotal

) {}