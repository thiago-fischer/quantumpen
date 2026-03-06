package io.fischer.quantumpen.shared.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TelefoneDTO(
        @NotBlank
        @Pattern(regexp = "\\d{10,11}", message = "Telefone inválido")
        String telefone
) {}
