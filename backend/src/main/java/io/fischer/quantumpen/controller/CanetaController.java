package io.fischer.quantumpen.controller;

import io.fischer.quantumpen.model.Caneta;
import io.fischer.quantumpen.service.CanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/canetas")
public class CanetaController {

    @Autowired
    private CanetaService service;

    @GetMapping
    public List<Caneta> encontrarTodos() {
        return service.encontrarTodos();
    }
}
