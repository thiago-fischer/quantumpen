package io.fischer.quantumpen.clientes.dto.common;

import io.fischer.quantumpen.clientes.model.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TelefoneDTO(
        @NotBlank
        @Pattern(regexp = "\\d{10,11}", message = "Telefone inválido")
        String telefone
) {
    public Telefone toEntity() {
        Telefone entity = new Telefone();
        entity.setTelefone(telefone);
        return entity;
    }
}
