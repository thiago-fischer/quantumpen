package io.fischer.quantumpen.controller;

import io.fischer.quantumpen.dto.request.CreateCanetaDTO;
import io.fischer.quantumpen.dto.request.UpdateCanetaDTO;
import io.fischer.quantumpen.dto.response.CanetaResponseDTO;
import io.fischer.quantumpen.service.CanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/canetas")
public class CanetaController {

    @Autowired
    private CanetaService service;

    @GetMapping
    public List<CanetaResponseDTO> encontrarTodos() {
        return service.encontrarTodos();
    }

    @GetMapping("/{id}")
    public CanetaResponseDTO encontrarId(@PathVariable("id") Long id) {
        return service.encontrarId(id);
    }

    @PostMapping
    public CanetaResponseDTO criarCaneta(@RequestBody CreateCanetaDTO canetaDTO) {
        return service.criarCaneta(canetaDTO);
    }

    @PutMapping("/{id}")
    public CanetaResponseDTO editarCaneta(@PathVariable("id") Long id, @RequestBody UpdateCanetaDTO canetaDTO) {
        return service.editarCaneta(id, canetaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCaneta(@PathVariable("id") Long id) {
        return service.deletarCaneta(id);
    }
}
