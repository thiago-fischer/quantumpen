package io.fischer.quantumpen.clientes.dto.request;


import io.fischer.quantumpen.clientes.dto.common.EnderecoDTO;
import io.fischer.quantumpen.clientes.dto.common.TelefoneDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.List;

public record UpdateClienteDTO(
        String nome,
        LocalDate dataNascimento,
        String cpf,
        @Valid List<TelefoneDTO> telefones,
        @Email String email,
        @Valid EnderecoDTO endereco
){}
