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
import java.util.logging.Logger;

@Service
public class CanetaService {

    private final Logger logger = Logger.getLogger(CanetaService.class.getName());
    private final CanetaRepository repository;

    public CanetaService(CanetaRepository repository) {
        this.repository = repository;
    }

    public List<CanetaResponseDTO> encontrarTodos() {
        logger.info("Listando todas as canetas!");
        return CanetaResponseDTO.fromEntities(repository.findAll());
    }

    public CanetaResponseDTO encontrarId(Long id) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        logger.info("Buscando uma caneta!");
        return CanetaResponseDTO.fromEntity(entity);
    }

    public CanetaResponseDTO criarCaneta(CreateCanetaDTO canetaDTO) {
        Caneta entity = repository.save(canetaDTO.toEntity());
        logger.info("Criando uma caneta!");
        return CanetaResponseDTO.fromEntity(entity);
    }

    public CanetaResponseDTO editarCaneta(Long id, UpdateCanetaDTO canetaDTO) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        canetaDTO.applyTo(entity);
        Caneta updatedEntity = repository.save(entity);
        logger.info("Editando uma caneta!");
        return CanetaResponseDTO.fromEntity(updatedEntity);
    }

    public ResponseEntity<?> deletarCaneta(Long id) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        repository.delete(entity);
        logger.info("Deletando uma caneta!");
        return ResponseEntity.noContent().build();
    }
}
