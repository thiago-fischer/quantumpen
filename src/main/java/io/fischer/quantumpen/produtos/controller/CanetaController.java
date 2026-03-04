package io.fischer.quantumpen.produtos.controller;

import io.fischer.quantumpen.exception.ExceptionResponse;
import io.fischer.quantumpen.produtos.dto.request.CreateCanetaDTO;
import io.fischer.quantumpen.produtos.dto.request.UpdateCanetaDTO;
import io.fischer.quantumpen.produtos.dto.response.CanetaResponseDTO;
import io.fischer.quantumpen.produtos.service.CanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/v1/canetas")
@Tag(name = "Canetas", description = "Gerenciamento de canetas da QuantumPen")
public class CanetaController {

    @Autowired
    private CanetaService service;

    @Operation(summary = "Listar todas as canetas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public List<CanetaResponseDTO> encontrarTodos() {
        return service.encontrarTodos();
    }

    @Operation(summary = "Buscar caneta por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caneta encontrada"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Caneta não encontrada",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "ID inválido")
    })
    @GetMapping("/{id}")
    public CanetaResponseDTO encontrarId(
            @Parameter(description = "ID da caneta", example = "1")
            @PathVariable Long id) {

        return service.encontrarId(id);
    }

    @Operation(summary = "Criar nova caneta")
    @ApiResponse(responseCode = "200", description = "Caneta criada com sucesso")
    @PostMapping
    public CanetaResponseDTO criarCaneta(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para criação da caneta",
                    required = true
            )
            @RequestBody CreateCanetaDTO canetaDTO) {

        return service.criarCaneta(canetaDTO);
    }

    @Operation(summary = "Atualizar caneta existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caneta atualizada"),
            @ApiResponse(responseCode = "404", description = "Caneta não encontrada")
    })
    @PutMapping("/{id}")
    public CanetaResponseDTO editarCaneta(
            @Parameter(description = "ID da caneta", example = "1")
            @PathVariable Long id,
            @RequestBody UpdateCanetaDTO canetaDTO) {

        return service.editarCaneta(id, canetaDTO);
    }

    @Operation(summary = "Remover caneta")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Caneta removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caneta não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCaneta(
            @Parameter(description = "ID da caneta", example = "1")
            @PathVariable Long id) {

        return service.deletarCaneta(id);
    }
}