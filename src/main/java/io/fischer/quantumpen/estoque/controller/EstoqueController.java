package io.fischer.quantumpen.estoque.controller;

import io.fischer.quantumpen.estoque.dto.request.CreateEstoqueDTO;
import io.fischer.quantumpen.estoque.dto.request.UpdateEstoqueDTO;
import io.fischer.quantumpen.estoque.dto.response.EstoqueResponseDTO;
import io.fischer.quantumpen.estoque.service.EstoqueService;
import io.fischer.quantumpen.shared.dto.ApiResponseDTO;
import io.fischer.quantumpen.shared.response.ResponseFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;

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
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<EstoqueResponseDTO>>> listarEstoque(
            @RequestParam(name = "produtoId", required = false) Long produtoId,
            HttpServletRequest request
    ) {

        List<EstoqueResponseDTO> estoque =
                produtoId != null
                        ? service.buscarPorProduto(produtoId)
                        : service.listarEstoque();

        return ResponseFactory.ok(
                estoque,
                "Estoque listado com sucesso",
                request
        );
    }

    @Operation(summary = "Buscar item de estoque por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EstoqueResponseDTO>> buscarEstoque(
            @PathVariable Long id,
            HttpServletRequest request
    ) {

        EstoqueResponseDTO estoque = service.buscarEstoque(id);

        return ResponseFactory.ok(
                estoque,
                "Item de estoque encontrado",
                request
        );
    }

    @Operation(summary = "Criar novo item de estoque")
    @PostMapping
    public ResponseEntity<ApiResponseDTO<EstoqueResponseDTO>> criarEstoque(
            @RequestBody CreateEstoqueDTO estoqueDTO,
            HttpServletRequest request
    ) {

        EstoqueResponseDTO estoque = service.criarEstoque(estoqueDTO);

        return ResponseFactory.created(
                estoque,
                "Estoque criado com sucesso",
                request
        );
    }

    @Operation(summary = "Atualizar item de estoque")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EstoqueResponseDTO>> editarEstoque(
            @PathVariable Long id,
            @RequestBody UpdateEstoqueDTO estoqueDTO,
            HttpServletRequest request
    ) {

        EstoqueResponseDTO estoque = service.editarEstoque(id, estoqueDTO);

        return ResponseFactory.ok(
                estoque,
                "Estoque atualizado com sucesso",
                request
        );
    }

    @Operation(summary = "Remover item de estoque")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deletarEstoque(
            @PathVariable Long id,
            HttpServletRequest request
    ) {

        service.deletarEstoque(id);

        return ResponseFactory.noContent(
                "Estoque removido com sucesso",
                request
        );
    }
}