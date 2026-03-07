package io.fischer.quantumpen.produtos.controller;

import io.fischer.quantumpen.produtos.dto.request.CreateCanetaDTO;
import io.fischer.quantumpen.produtos.dto.request.UpdateCanetaDTO;
import io.fischer.quantumpen.produtos.dto.response.CanetaResponseDTO;
import io.fischer.quantumpen.produtos.service.CanetaService;
import io.fischer.quantumpen.shared.dto.ApiResponseDTO;
import io.fischer.quantumpen.shared.response.ResponseFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/canetas")
@Tag(name = "Canetas", description = "Gerenciamento de canetas da QuantumPen")
@SecurityRequirement(name = "bearerAuth")
public class CanetaController {

    private final CanetaService service;

    public CanetaController(CanetaService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas as canetas")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<CanetaResponseDTO>>> encontrarTodos(
            HttpServletRequest request
    ) {

        List<CanetaResponseDTO> canetas = service.encontrarTodos();

        return ResponseFactory.ok(
                canetas,
                "Lista de canetas retornada com sucesso",
                request
        );
    }

    @Operation(summary = "Buscar caneta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CanetaResponseDTO>> encontrarId(
            @PathVariable Long id,
            HttpServletRequest request
    ) {

        CanetaResponseDTO caneta = service.encontrarId(id);

        return ResponseFactory.ok(
                caneta,
                "Caneta encontrada",
                request
        );
    }

    @Operation(summary = "Criar nova caneta")
    @PostMapping
    public ResponseEntity<ApiResponseDTO<CanetaResponseDTO>> criarCaneta(
            @RequestBody CreateCanetaDTO canetaDTO,
            HttpServletRequest request
    ) {

        CanetaResponseDTO caneta = service.criarCaneta(canetaDTO);

        return ResponseFactory.created(
                caneta,
                "Caneta criada com sucesso",
                request
        );
    }

    @Operation(summary = "Atualizar caneta existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CanetaResponseDTO>> editarCaneta(
            @PathVariable Long id,
            @RequestBody UpdateCanetaDTO canetaDTO,
            HttpServletRequest request
    ) {

        CanetaResponseDTO caneta = service.editarCaneta(id, canetaDTO);

        return ResponseFactory.ok(
                caneta,
                "Caneta atualizada com sucesso",
                request
        );
    }

    @Operation(summary = "Remover caneta")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deletarCaneta(
            @PathVariable Long id,
            HttpServletRequest request
    ) {

        service.deletarCaneta(id);

        return ResponseFactory.noContent(
                "Caneta removida com sucesso",
                request
        );
    }
}