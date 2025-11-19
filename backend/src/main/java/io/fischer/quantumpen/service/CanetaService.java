package io.fischer.quantumpen.service;

import io.fischer.quantumpen.dto.request.CreateCanetaDTO;
import io.fischer.quantumpen.dto.request.UpdateCanetaDTO;
import io.fischer.quantumpen.dto.response.CanetaResponseDTO;
import io.fischer.quantumpen.exception.NotFoundException;
import io.fischer.quantumpen.model.Caneta;
import io.fischer.quantumpen.repository.CanetaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanetaService {

    private final CanetaRepository repository;

    public CanetaService(CanetaRepository repository) {
        this.repository = repository;
    }

    public List<CanetaResponseDTO> encontrarTodos() {
        return CanetaResponseDTO.fromEntities(repository.findAll());
    }

    public CanetaResponseDTO encontrarId(Long id) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        return CanetaResponseDTO.fromEntity(entity);
    }

    public CanetaResponseDTO criarCaneta(CreateCanetaDTO canetaDTO) {
        Caneta entity = repository.save(canetaDTO.toEntity());
        return CanetaResponseDTO.fromEntity(entity);
    }

    public CanetaResponseDTO editarCaneta(Long id, UpdateCanetaDTO canetaDTO) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        canetaDTO.applyTo(entity);
        Caneta updatedEntity = repository.save(entity);
        return CanetaResponseDTO.fromEntity(updatedEntity);
    }

    public ResponseEntity<?> deletarCaneta(Long id) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        repository.delete(entity);
        return ResponseEntity.noContent().build();
    }
}
