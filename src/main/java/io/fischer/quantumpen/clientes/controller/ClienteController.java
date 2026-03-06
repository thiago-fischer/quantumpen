package io.fischer.quantumpen.clientes.controller;

import io.fischer.quantumpen.clientes.dto.request.CreateClienteDTO;
import io.fischer.quantumpen.clientes.dto.request.UpdateClienteDTO;
import io.fischer.quantumpen.clientes.dto.response.ClienteResponseDTO;
import io.fischer.quantumpen.clientes.service.ClienteService;
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
@RequestMapping("api/v1/clientes")
@Tag(name = "Clientes", description = "Operações relacionadas ao gerenciamento de clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar clientes", description = "Retorna uma lista com todos os clientes cadastrados")
    public ResponseEntity<ApiResponseDTO<List<ClienteResponseDTO>>> findAll(
            HttpServletRequest request
    ) {

        return ResponseFactory.ok(
                service.findAll(),
                "Clientes listados com sucesso!",
                request
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<ApiResponseDTO<ClienteResponseDTO>> findById(
            @Parameter(description = "ID do cliente", example = "1")
            @PathVariable("id") Long id,
            HttpServletRequest request
    ) {
        return ResponseFactory.ok(
                service.findById(id),
                "Cliente listado com sucesso!",
                request
        );
    }

    @PostMapping
    @Operation(summary = "Cadastrar cliente")
    public ResponseEntity<ApiResponseDTO<ClienteResponseDTO>> createCliente(
            @Valid @RequestBody CreateClienteDTO dto,
            HttpServletRequest request
    ) {
        return ResponseFactory.created(
                service.createCliente(dto),
                "Cliente cadastrado com sucesso",
                request
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente completamente (PUT)")
    public ResponseEntity<ApiResponseDTO<ClienteResponseDTO>> putCliente(
            @Parameter(description = "ID do cliente", example = "1")
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateClienteDTO dto,
            HttpServletRequest request
    ) {
        return ResponseFactory.ok(
                service.putCliente(id, dto),
                "Cliente sobrescrito com sucesso!",
                request
        );
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar cliente parcialmente (PATCH)")
    public ResponseEntity<ApiResponseDTO<ClienteResponseDTO>> patchCliente(
            @Parameter(description = "ID do cliente", example = "1")
            @PathVariable("id") Long id,
            @RequestBody UpdateClienteDTO dto,
            HttpServletRequest request
    ) {
        return ResponseFactory.ok(
                service.patchCliente(id, dto),
                "Cliente atualizado com sucesso!",
                request
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover cliente")
    public ResponseEntity<ApiResponseDTO<Void>> deleteCliente(
            @Parameter(description = "ID do cliente", example = "1")
            @PathVariable("id") Long id,
            HttpServletRequest request
    ) {
        service.deleteCliente(id);
        return ResponseFactory.noContent(
                "Cliente deletado com sucesso!",
                request
        );
    }

}