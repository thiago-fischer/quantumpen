package io.fischer.quantumpen.clientes.dto.response;

import io.fischer.quantumpen.clientes.dto.common.EnderecoDTO;
import io.fischer.quantumpen.clientes.dto.common.TelefoneDTO;

import java.time.LocalDate;
import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        LocalDate dataCadastro,
        String cpf,
        List<TelefoneDTO> telefones,
        String email,
        EnderecoDTO endereco,
        String role
) {}