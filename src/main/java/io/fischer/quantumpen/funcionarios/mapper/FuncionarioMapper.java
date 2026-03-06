package io.fischer.quantumpen.funcionarios.mapper;

import io.fischer.quantumpen.funcionarios.dto.request.CreateFuncionarioDTO;
import io.fischer.quantumpen.funcionarios.dto.request.UpdateFuncionarioDTO;
import io.fischer.quantumpen.funcionarios.dto.response.FuncionarioResponseDTO;
import io.fischer.quantumpen.funcionarios.model.Funcionario;
import io.fischer.quantumpen.shared.mapper.EnderecoMapper;

import java.util.List;

public class FuncionarioMapper {

    public static Funcionario toEntity(CreateFuncionarioDTO dto) {

        Funcionario entity = new Funcionario();

        entity.setNome(dto.nome());
        entity.setCpf(dto.cpf());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setCargo(dto.cargo());
        entity.setSalario(dto.salario());

        if (dto.endereco() != null) {
            entity.setEndereco(
                    EnderecoMapper.toEntity(dto.endereco())
            );
        }

        return entity;
    }

    public static void patchFuncionario(Funcionario entity, UpdateFuncionarioDTO dto) {

        if (dto.nome() != null) entity.setNome(dto.nome());
        if (dto.cpf() != null) entity.setCpf(dto.cpf());
        if (dto.dataNascimento() != null) entity.setDataNascimento(dto.dataNascimento());
        if (dto.cargo() != null) entity.setCargo(dto.cargo());
        if (dto.salario() != null) entity.setSalario(dto.salario());

        if (dto.endereco() != null) {
            entity.setEndereco(
                    EnderecoMapper.toEntity(dto.endereco())
            );
        }
    }

    public static void putFuncionario(Funcionario entity, UpdateFuncionarioDTO dto) {

        entity.setNome(dto.nome());
        entity.setCpf(dto.cpf());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setCargo(dto.cargo());
        entity.setSalario(dto.salario());

        entity.setEndereco(
                EnderecoMapper.toEntity(dto.endereco())
        );
    }

    public static FuncionarioResponseDTO toDTO(Funcionario entity) {

        return new FuncionarioResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDataNascimento(),
                entity.getDataContratacao(),
                entity.getCpf(),
                entity.getCargo(),
                entity.getUser().getEmail(),
                entity.getUser().getRole(),
                EnderecoMapper.fromEntity(entity.getEndereco())
        );
    }

    public static List<FuncionarioResponseDTO> toListDTO(List<Funcionario> entities) {

        return entities.stream()
                .map(FuncionarioMapper::toDTO)
                .toList();
    }
}