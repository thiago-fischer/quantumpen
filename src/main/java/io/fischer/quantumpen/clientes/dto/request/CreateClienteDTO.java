package io.fischer.quantumpen.clientes.dto.request;


import io.fischer.quantumpen.clientes.dto.common.EnderecoDTO;
import io.fischer.quantumpen.clientes.dto.common.TelefoneDTO;
import io.fischer.quantumpen.clientes.model.Cliente;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record CreateClienteDTO(
        @NotBlank
        String nome,

        LocalDate dataNascimento,

        @NotBlank
        String cpf,

        @NotEmpty
        @Valid
        List<TelefoneDTO> telefones,

        @Email
        String email,

        @Valid
        EnderecoDTO endereco,

        @NotBlank
        String senha
){}
