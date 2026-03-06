package io.fischer.quantumpen.users.dto.response;

public record UserResponseDTO(
        Long id,
        String email,
        String role,
        Boolean ativo
) {
}
