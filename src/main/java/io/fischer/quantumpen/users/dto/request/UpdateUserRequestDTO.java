package io.fischer.quantumpen.users.dto.request;

public record UpdateUserRequestDTO(
        String email,
        String senha,
        String role,
        Boolean ativo
) {
}
