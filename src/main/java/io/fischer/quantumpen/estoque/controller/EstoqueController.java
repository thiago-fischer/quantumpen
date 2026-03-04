package io.fischer.quantumpen.estoque.controller;

import io.fischer.quantumpen.estoque.dto.request.CreateEstoqueDTO;
import io.fischer.quantumpen.estoque.dto.request.UpdateEstoqueDTO;
import io.fischer.quantumpen.estoque.dto.response.EstoqueResponseDTO;
import io.fischer.quantumpen.estoque.service.EstoqueService;
import io.fischer.quantumpen.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estoque")
@Tag(name = "Estoque", description = "Gerenciamento de estoque das canetas")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @Operation(summary = "Listar estoque ou filtrar por produto")
    @ApiResponse(responseCode = "200", description = "Estoque listado com sucesso")
    @GetMapping
    public List<EstoqueResponseDTO> listarEstoque(
            @Parameter(description = "ID do produto para filtrar", example = "1")
            @RequestParam(name = "produtoId", required = false) Long produtoId
    ) {
        if(produtoId != null) return service.buscarPorProduto(produtoId);
        return service.listarEstoque();
    }

    @Operation(summary = "Buscar item de estoque por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Estoque não encontrado",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @GetMapping("/{id}")
    public EstoqueResponseDTO buscarEstoque(
            @Parameter(description = "ID do estoque", example = "1")
            @PathVariable Long id) {
        return service.buscarEstoque(id);
    }

    @Operation(summary = "Criar novo item de estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estoque criado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @PostMapping
    public EstoqueResponseDTO criarEstoque(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para criação do estoque",
                    required = true
            )
            @RequestBody CreateEstoqueDTO estoqueDTO) {

        return service.criarEstoque(estoqueDTO);
    }

    @Operation(summary = "Atualizar item de estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estoque atualizado"),
            @ApiResponse(responseCode = "404", description = "Estoque ou produto não encontrado")
    })
    @PutMapping("/{id}")
    public EstoqueResponseDTO editarEstoque(
            @Parameter(description = "ID do estoque", example = "1")
            @PathVariable Long id,
            @RequestBody UpdateEstoqueDTO estoqueDTO) {

        return service.editarEstoque(id, estoqueDTO);
    }

    @Operation(summary = "Remover item de estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estoque removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEstoque(
            @Parameter(description = "ID do estoque", example = "1")
            @PathVariable Long id) {

        return service.deletarEstoque(id);
    }
}