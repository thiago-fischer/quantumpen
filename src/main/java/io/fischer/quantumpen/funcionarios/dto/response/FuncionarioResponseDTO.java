package io.fischer.quantumpen.funcionarios.dto.response;

import io.fischer.quantumpen.shared.dto.EnderecoDTO;
import io.fischer.quantumpen.shared.dto.TelefoneDTO;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioResponseDTO(

        Long id,
        String nome,
        LocalDate dataNascimento,
        LocalDate dataAdmissao,
        String cpf,
        String cargo,
        String email,
        String role,
        EnderecoDTO endereco

) {}