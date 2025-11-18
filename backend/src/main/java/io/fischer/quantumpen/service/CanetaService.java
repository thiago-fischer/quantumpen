package io.fischer.quantumpen.service;

import io.fischer.quantumpen.model.Caneta;
import io.fischer.quantumpen.repository.CanetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanetaService {

    private CanetaRepository repository;

    public CanetaService(CanetaRepository repository) {
        this.repository = repository;
    }

    public List<Caneta> encontrarTodos() {
        return repository.findAll();
    }
}
