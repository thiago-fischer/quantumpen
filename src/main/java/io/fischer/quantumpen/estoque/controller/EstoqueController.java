package io.fischer.quantumpen.estoque.controller;

import io.fischer.quantumpen.estoque.dto.request.CreateEstoqueDTO;
import io.fischer.quantumpen.estoque.dto.request.UpdateEstoqueDTO;
import io.fischer.quantumpen.estoque.dto.response.EstoqueResponseDTO;
import io.fischer.quantumpen.estoque.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estoque")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @GetMapping
    public List<EstoqueResponseDTO> listarEstoque(
            @RequestParam(name = "produtoId", required = false) Long produtoId
    ) {
        if(produtoId != null) return service.buscarPorProduto(produtoId);
        return service.listarEstoque();
    }

    @GetMapping("/{id}")
    public EstoqueResponseDTO buscarEstoque(@PathVariable("id") Long id) {
        return service.buscarEstoque(id);
    }

    @PostMapping
    public EstoqueResponseDTO criarEstoque(@RequestBody CreateEstoqueDTO estoqueDTO) {
        return service.criarEstoque(estoqueDTO);
    }

    @PutMapping("/{id}")
    public EstoqueResponseDTO editarEstoque(
            @PathVariable("id") Long id,
            @RequestBody UpdateEstoqueDTO estoqueDTO
    ) {
        return service.editarEstoque(id, estoqueDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEstoque(@PathVariable("id") Long id) {
        return service.deletarEstoque(id);
    }
}
