package io.fischer.quantumpen.clientes.mapper;

import io.fischer.quantumpen.clientes.dto.request.CreateClienteDTO;
import io.fischer.quantumpen.clientes.dto.request.UpdateClienteDTO;
import io.fischer.quantumpen.clientes.dto.response.ClienteResponseDTO;
import io.fischer.quantumpen.clientes.model.Cliente;

import java.util.List;

public class ClienteMapper {

    public static Cliente toEntity(CreateClienteDTO dto) {
        Cliente entity = new Cliente();

        entity.setNome(dto.nome());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setCpf(dto.cpf());
        entity.setEmail(dto.email());

        var telefonesEntity = dto.telefones()
                .stream()
                .map(TelefoneMapper::toEntity)
                .toList();

        entity.setTelefones(telefonesEntity);

        entity.setEndereco(EnderecoMapper.toEntity(dto.endereco()));

        return entity;
    }

    public static void patchCliente(Cliente entity, UpdateClienteDTO dto) {
        if (dto.nome() != null) entity.setNome(dto.nome());
        if (dto.dataNascimento() != null) entity.setDataNascimento(dto.dataNascimento());
        if (dto.cpf() != null) entity.setCpf(dto.cpf());
        if (dto.email() != null) entity.setEmail(dto.email());
        if (dto.telefones() != null) entity.setTelefones(dto.telefones()
                .stream()
                .map(TelefoneMapper::toEntity)
                .toList());
        if (dto.endereco() != null) entity.setEndereco(EnderecoMapper.toEntity(dto.endereco()));
    }

    public static void putCliente(Cliente entity, UpdateClienteDTO dto) {
        entity.setNome(dto.nome());
        entity.setDataNascimento(dto.dataNascimento());
        entity.setCpf(dto.cpf());
        entity.setEmail(dto.email());

        var telefonesEntity = dto.telefones()
                .stream()
                .map(TelefoneMapper::toEntity)
                .toList();

        entity.setTelefones(telefonesEntity);

        entity.setEndereco(EnderecoMapper.toEntity(dto.endereco()));
    }

    public static ClienteResponseDTO toDTO(Cliente entity) {
        return new ClienteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDataNascimento(),
                entity.getDataCadastro(),
                entity.getCpf(),
                entity.getTelefones().stream()
                        .map(TelefoneMapper::fromEntity)
                        .toList(),
                entity.getEmail(),
                EnderecoMapper.fromEntity(entity.getEndereco())
        );
    }

    public static List<ClienteResponseDTO> toListDTO(List<Cliente> entities) {
        return entities.stream()
                .map(ClienteMapper::toDTO)
                .toList();
    }
}
