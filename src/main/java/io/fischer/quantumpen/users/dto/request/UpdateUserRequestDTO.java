package io.fischer.quantumpen.users.dto.request;

public record UpdateUserRequestDTO(
        String nome,
        String email,
        String senha,
        String role,
        Boolean ativo
) {
}
