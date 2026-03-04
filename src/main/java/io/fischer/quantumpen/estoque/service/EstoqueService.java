package io.fischer.quantumpen.estoque.service;

import io.fischer.quantumpen.estoque.dto.request.CreateEstoqueDTO;
import io.fischer.quantumpen.estoque.dto.request.UpdateEstoqueDTO;
import io.fischer.quantumpen.estoque.dto.response.EstoqueResponseDTO;
import io.fischer.quantumpen.estoque.model.Estoque;
import io.fischer.quantumpen.estoque.repository.EstoqueRepository;
import io.fischer.quantumpen.exception.NotFoundException;
import io.fischer.quantumpen.produtos.model.Caneta;
import io.fischer.quantumpen.produtos.repository.CanetaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;
    private final CanetaRepository produtoRepository;

    public EstoqueService(EstoqueRepository repository, CanetaRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public List<EstoqueResponseDTO> listarEstoque() {
        return EstoqueResponseDTO.fromEntities(repository.findAll());
    }

    public EstoqueResponseDTO buscarEstoque(Long id) {
        Estoque entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Estoque com o ID " + id + " não encontrado!")
        );
        return EstoqueResponseDTO.fromEntity(entity);
    }

    public EstoqueResponseDTO criarEstoque(CreateEstoqueDTO estoqueDTO) {
        Caneta produtoEntity = produtoRepository.findById(estoqueDTO.produtoId()).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + estoqueDTO.produtoId() + " não encontrada!")
        );
        Estoque entity = repository.save(estoqueDTO.toEntity(produtoEntity));
        return EstoqueResponseDTO.fromEntity(entity);
    }

    public EstoqueResponseDTO editarEstoque(Long id, UpdateEstoqueDTO estoqueDTO) {
        Caneta produtoEntity = produtoRepository.findById(estoqueDTO.produtoId()).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + estoqueDTO.produtoId() + " não encontrada!")
        );
        Estoque entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Estoque com o ID " + id + " não encontrado!")
        );
        estoqueDTO.applyTo(entity, produtoEntity);
        Estoque updatedEntity = repository.save(entity);
        return EstoqueResponseDTO.fromEntity(updatedEntity);
    }

    public ResponseEntity<?> deletarEstoque(Long id) {
        Estoque entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Estoque com o ID " + id + " não encontrado!")
        );
        repository.delete(entity);
        return ResponseEntity.noContent().build();
    }

    public List<EstoqueResponseDTO> buscarPorProduto(Long produtoId) {
        return EstoqueResponseDTO.fromEntities(
                repository.findByProdutoId(produtoId)
        );
    }
}
