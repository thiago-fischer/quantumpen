package io.fischer.quantumpen.users.controller;

import io.fischer.quantumpen.users.dto.request.CreateUserRequestDTO;
import io.fischer.quantumpen.users.dto.request.UpdateUserRequestDTO;
import io.fischer.quantumpen.users.dto.response.UserResponseDTO;
import io.fischer.quantumpen.users.service.UserService;
import io.fischer.quantumpen.shared.dto.ApiResponseDTO;
import io.fischer.quantumpen.shared.response.ResponseFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Usuários", description = "Gerenciamento de usuários da plataforma")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos os usuários")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<UserResponseDTO>>> findAll(
            HttpServletRequest request
    ) {

        List<UserResponseDTO> users = service.buscarTodos();

        return ResponseFactory.ok(
                users,
                "Usuários listados com sucesso",
                request
        );
    }

    @Operation(summary = "Buscar usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> findById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {

        UserResponseDTO user = service.buscarId(id);

        return ResponseFactory.ok(
                user,
                "Usuário encontrado",
                request
        );
    }

    @Operation(summary = "Criar novo usuário")
    @PostMapping
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> createUser(
            @RequestBody CreateUserRequestDTO dto,
            HttpServletRequest request
    ) {

        UserResponseDTO user = service.criarUsuario(dto);

        return ResponseFactory.created(
                user,
                "Usuário criado com sucesso",
                request
        );
    }

    @Operation(summary = "Substituir usuário (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> putUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDTO dto,
            HttpServletRequest request
    ) {

        UserResponseDTO user = service.putUsuario(id, dto);

        return ResponseFactory.ok(
                user,
                "Usuário atualizado com sucesso",
                request
        );
    }

    @Operation(summary = "Atualizar parcialmente usuário (PATCH)")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> patchUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDTO dto,
            HttpServletRequest request
    ) {

        UserResponseDTO user = service.patchUsuario(id, dto);

        return ResponseFactory.ok(
                user,
                "Usuário atualizado parcialmente",
                request
        );
    }

    @Operation(summary = "Desativar usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteUser(
            @PathVariable Long id,
            HttpServletRequest request
    ) {

        service.desativarUsuario(id);

        return ResponseFactory.noContent(
                "Usuário desativado com sucesso",
                request
        );
    }
}