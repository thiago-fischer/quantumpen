package io.fischer.quantumpen.auth.dto;

public record LoginRequestDTO(
        String email,
        String senha
) {}
