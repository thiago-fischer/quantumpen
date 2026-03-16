package io.fischer.quantumpen.pedidos.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoDTO(

        @NotNull
        Long produtoId,

        @Min(1)
        int quantidade

) {}