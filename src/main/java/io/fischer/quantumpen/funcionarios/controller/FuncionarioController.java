package io.fischer.quantumpen.funcionarios.controller;

import io.fischer.quantumpen.funcionarios.dto.request.CreateFuncionarioDTO;
import io.fischer.quantumpen.funcionarios.dto.request.UpdateFuncionarioDTO;
import io.fischer.quantumpen.funcionarios.dto.response.FuncionarioResponseDTO;
import io.fischer.quantumpen.funcionarios.service.FuncionarioService;
import io.fischer.quantumpen.shared.dto.ApiResponseDTO;
import io.fischer.quantumpen.shared.response.ResponseFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/funcionarios")
@Tag(name = "Funcionários", description = "Operações relacionadas ao gerenciamento de funcionários")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar funcionários", description = "Retorna uma lista com todos os funcionários cadastrados")
    public ResponseEntity<ApiResponseDTO<List<FuncionarioResponseDTO>>> findAll(
            HttpServletRequest request
    ) {

        return ResponseFactory.ok(
                service.findAll(),
                "Funcionários listados com sucesso!",
                request
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar funcionário por ID")
    public ResponseEntity<ApiResponseDTO<FuncionarioResponseDTO>> findById(
            @Parameter(description = "ID do funcionário", example = "1")
            @PathVariable("id") Long id,
            HttpServletRequest request
    ) {
        return ResponseFactory.ok(
                service.findById(id),
                "Funcionário listado com sucesso!",
                request
        );
    }

    @PostMapping
    @Operation(summary = "Cadastrar funcionário")
    public ResponseEntity<ApiResponseDTO<FuncionarioResponseDTO>> createFuncionario(
            @Valid @RequestBody CreateFuncionarioDTO dto,
            HttpServletRequest request
    ) {
        return ResponseFactory.created(
                service.createFuncionario(dto),
                "Funcionário cadastrado com sucesso",
                request
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar funcionário completamente (PUT)")
    public ResponseEntity<ApiResponseDTO<FuncionarioResponseDTO>> putFuncionario(
            @Parameter(description = "ID do funcionário", example = "1")
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateFuncionarioDTO dto,
            HttpServletRequest request
    ) {
        return ResponseFactory.ok(
                service.putFuncionario(id, dto),
                "Funcionário sobrescrito com sucesso!",
                request
        );
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar funcionário parcialmente (PATCH)")
    public ResponseEntity<ApiResponseDTO<FuncionarioResponseDTO>> patchFuncionario(
            @Parameter(description = "ID do funcionário", example = "1")
            @PathVariable("id") Long id,
            @RequestBody UpdateFuncionarioDTO dto,
            HttpServletRequest request
    ) {
        return ResponseFactory.ok(
                service.patchFuncionario(id, dto),
                "Funcionário atualizado com sucesso!",
                request
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover funcionário")
    public ResponseEntity<ApiResponseDTO<Void>> deleteFuncionario(
            @Parameter(description = "ID do funcionário", example = "1")
            @PathVariable("id") Long id,
            HttpServletRequest request
    ) {
        service.deleteFuncionario(id);

        return ResponseFactory.noContent(
                "Funcionário deletado com sucesso!",
                request
        );
    }

    @PostMapping("/batch")
    @Operation(summary = "Cadastrar lista de funcionários")
    public ResponseEntity<ApiResponseDTO<List<FuncionarioResponseDTO>>> createFuncionarios(
            @Valid @RequestBody List<CreateFuncionarioDTO> dtos,
            HttpServletRequest request
    ) {

        List<FuncionarioResponseDTO> funcionarios = dtos.stream()
                .map(service::createFuncionario)
                .toList();
        return ResponseFactory.created(
                funcionarios,
                "Funcionários cadastrados com sucesso",
                request
        );
    }

}