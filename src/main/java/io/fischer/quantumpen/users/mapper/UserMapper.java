package io.fischer.quantumpen.users.mapper;

import io.fischer.quantumpen.users.dto.request.CreateUserRequestDTO;
import io.fischer.quantumpen.users.dto.request.UpdateUserRequestDTO;
import io.fischer.quantumpen.users.dto.response.UserResponseDTO;
import io.fischer.quantumpen.users.model.User;
import java.util.List;

public class UserMapper {

    public static User toEntity(CreateUserRequestDTO dto) {
        User entity = new User();
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setSenha(dto.senha());
        entity.setRole(dto.role());
        entity.setAtivo(dto.ativo());
        return entity;
    }

    public static void patchEntity(User entity, UpdateUserRequestDTO dto) {
        if(dto.nome() != null) entity.setNome(dto.nome());
        if(dto.email() != null) entity.setEmail(dto.email());
        if(dto.senha() != null) entity.setSenha(dto.senha());
        if(dto.role() != null) entity.setRole(dto.role());
        if(dto.ativo() != null) entity.setAtivo(dto.ativo());
    }

    public static void putEntity(User entity, UpdateUserRequestDTO dto) {
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setSenha(dto.senha());
        entity.setRole(dto.role());
        entity.setAtivo(dto.ativo());
    }

    public static UserResponseDTO toResponseDTO(User entity) {
        return new UserResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getRole(),
                entity.isAtivo()
        );
    }

    public static List<UserResponseDTO> toResponseList(List<User> entities) {
        return entities.stream()
                .map(UserMapper::toResponseDTO)
                .toList();
    }
}
