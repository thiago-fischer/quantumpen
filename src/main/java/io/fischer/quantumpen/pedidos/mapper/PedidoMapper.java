package io.fischer.quantumpen.pedidos.mapper;

import io.fischer.quantumpen.clientes.model.Cliente;
import io.fischer.quantumpen.pedidos.dto.request.CreatePedidoDTO;
import io.fischer.quantumpen.pedidos.dto.request.ItemPedidoDTO;
import io.fischer.quantumpen.pedidos.dto.response.*;
import io.fischer.quantumpen.pedidos.model.*;
import io.fischer.quantumpen.produtos.model.Caneta;

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

    public static ItemPedido toItemEntity(
            ItemPedidoDTO dto,
            Caneta produto,
            Pedido pedido
    ) {
        ItemPedido entity = new ItemPedido();
        entity.setPedido(pedido);
        entity.setProduto(produto);
        entity.setQuantidade(dto.quantidade());
        entity.setPrecoUnitario(produto.getPreco());

        return entity;
    }

    public static Pedido toEntity(
            CreatePedidoDTO dto,
            Cliente cliente,
            List<ItemPedido> itens
    ) {
        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setItens(itens);
        pedido.setDataPedido(java.time.LocalDateTime.now());

        return pedido;
    }
}