package io.fischer.quantumpen.produtos.service;

import io.fischer.quantumpen.produtos.dto.request.CreateCanetaDTO;
import io.fischer.quantumpen.produtos.dto.request.UpdateCanetaDTO;
import io.fischer.quantumpen.produtos.dto.response.CanetaResponseDTO;
import io.fischer.quantumpen.exception.NotFoundException;
import io.fischer.quantumpen.produtos.model.Caneta;
import io.fischer.quantumpen.produtos.repository.CanetaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("permitAll()")
    public List<CanetaResponseDTO> findAll() {
        logger.info("Listando todas as canetas!");
        return CanetaResponseDTO.fromEntities(repository.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public CanetaResponseDTO findById(Long id) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        logger.info("Buscando uma caneta!");
        return CanetaResponseDTO.fromEntity(entity);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public CanetaResponseDTO createCaneta(CreateCanetaDTO canetaDTO) {
        Caneta entity = repository.save(canetaDTO.toEntity());
        logger.info("Criando uma caneta!");
        return CanetaResponseDTO.fromEntity(entity);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public CanetaResponseDTO PutCaneta(Long id, UpdateCanetaDTO canetaDTO) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        canetaDTO.applyTo(entity);
        Caneta updatedEntity = repository.save(entity);
        logger.info("Editando uma caneta!");
        return CanetaResponseDTO.fromEntity(updatedEntity);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public void deleteCaneta(Long id) {
        Caneta entity = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Caneta com o ID " + id + " não encontrada!")
        );
        repository.delete(entity);
        logger.info("Deletando uma caneta!");
    }
}
