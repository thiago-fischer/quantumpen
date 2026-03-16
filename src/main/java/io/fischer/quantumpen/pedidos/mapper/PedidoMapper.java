package io.fischer.quantumpen.pedidos.mapper;

import io.fischer.quantumpen.pedidos.dto.response.*;
import io.fischer.quantumpen.pedidos.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoResponseDTO toDTO(Pedido pedido) {

        List<ItemPedidoResponseDTO> itens = pedido.getItens()
                .stream()
                .map(PedidoMapper::toItemDTO)
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                itens
        );
    }

    private static ItemPedidoResponseDTO toItemDTO(ItemPedido item) {

        return new ItemPedidoResponseDTO(
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getPrecoUnitario() * item.getQuantidade()
        );
    }
}