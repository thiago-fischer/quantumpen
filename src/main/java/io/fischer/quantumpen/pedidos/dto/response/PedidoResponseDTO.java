package io.fischer.quantumpen.pedidos.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(

        Long id,
        Long clienteId,
        LocalDateTime dataPedido,
        double valorTotal,
        List<ItemPedidoResponseDTO> itens

) {}