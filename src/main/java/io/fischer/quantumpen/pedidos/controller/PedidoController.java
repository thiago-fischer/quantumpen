package io.fischer.quantumpen.pedidos.controller;

import io.fischer.quantumpen.pedidos.dto.request.CreatePedidoDTO;
import io.fischer.quantumpen.pedidos.dto.response.PedidoResponseDTO;
import io.fischer.quantumpen.pedidos.mapper.PedidoMapper;
import io.fischer.quantumpen.pedidos.model.Pedido;
import io.fischer.quantumpen.pedidos.service.PedidoService;
import io.fischer.quantumpen.shared.dto.ApiResponseDTO;
import io.fischer.quantumpen.shared.response.ResponseFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/pedidos")
@Tag(name = "Pedidos", description = "Operações relacionadas ao gerenciamento de pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Retorna uma lista com todos os pedidos cadastrados")
    public ResponseEntity<ApiResponseDTO<List<PedidoResponseDTO>>> findAll(
            HttpServletRequest request
    ) {

        List<PedidoResponseDTO> pedidos = service.listar()
                .stream()
                .map(PedidoMapper::toDTO)
                .toList();

        return ResponseFactory.ok(
                pedidos,
                "Pedidos listados com sucesso!",
                request
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<ApiResponseDTO<PedidoResponseDTO>> findById(
            @Parameter(description = "ID do pedido", example = "1")
            @PathVariable("id") Long id,
            HttpServletRequest request
    ) {

        Pedido pedido = service.buscar(id);

        return ResponseFactory.ok(
                PedidoMapper.toDTO(pedido),
                "Pedido encontrado com sucesso!",
                request
        );
    }

    @PostMapping
    @Operation(summary = "Cadastrar pedido")
    public ResponseEntity<ApiResponseDTO<PedidoResponseDTO>> createPedido(
            @Valid @RequestBody CreatePedidoDTO dto,
            HttpServletRequest request
    ) {

        Pedido pedido = service.criarPedido(dto);

        return ResponseFactory.created(
                PedidoMapper.toDTO(pedido),
                "Pedido cadastrado com sucesso!",
                request
        );
    }
}