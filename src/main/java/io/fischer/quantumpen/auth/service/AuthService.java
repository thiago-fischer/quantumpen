package io.fischer.quantumpen.auth.service;

import io.fischer.quantumpen.auth.dto.LoginRequestDTO;
import io.fischer.quantumpen.auth.dto.LoginResponseDTO;
import io.fischer.quantumpen.auth.security.JwtService;
import io.fischer.quantumpen.users.model.User;
import io.fischer.quantumpen.users.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtService jwtService;

    public AuthService(UserRepository repository, JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {

        User user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!user.getSenha().equals(dto.senha())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponseDTO(token);
    }
}
