package io.fischer.quantumpen.users.dto.request;

public record CreateUserRequestDTO(
        String email,
        String senha,
        String role,
        Boolean ativo
) {
}
