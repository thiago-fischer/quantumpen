package io.fischer.quantumpen.auth.controller;

import io.fischer.quantumpen.auth.dto.LoginRequestDTO;
import io.fischer.quantumpen.auth.dto.LoginResponseDTO;
import io.fischer.quantumpen.auth.service.AuthService;
import io.fischer.quantumpen.shared.dto.ApiResponseDTO;
import io.fischer.quantumpen.shared.response.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "Operações relacionadas à autenticação e geração de token JWT")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Realizar login",
            description = "Autentica um usuário e retorna um token JWT.",
            security = {}
    )
    public ResponseEntity<ApiResponseDTO<LoginResponseDTO>> login(
            @RequestBody LoginRequestDTO dto,
            HttpServletRequest request
    ) {

        return ResponseFactory.ok(
                service.login(dto),
                "Login realizado com sucesso",
                request
        );
    }
}
