package io.fischer.quantumpen.funcionarios.dto.request;

import io.fischer.quantumpen.shared.dto.EnderecoDTO;
import io.fischer.quantumpen.shared.dto.TelefoneDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateFuncionarioDTO(

        @NotBlank
        String nome,

        LocalDate dataNascimento,

        @NotBlank
        String cpf,

        @NotBlank
        String cargo,

        @Valid
        List<TelefoneDTO> telefones,

        @Email
        String email,

        @NotBlank
        String senha,

        @Valid
        EnderecoDTO endereco,

        @NotNull
        Double salario

) {}