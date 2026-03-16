package io.fischer.quantumpen.pedidos.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CreatePedidoDTO(

        @NotNull
        Long clienteId,

        @NotNull
        List<ItemPedidoDTO> itens

) {}