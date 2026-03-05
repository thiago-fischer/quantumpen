package io.fischer.quantumpen.users.service;

import io.fischer.quantumpen.exception.NotFoundException;
import io.fischer.quantumpen.users.dto.request.CreateUserRequestDTO;
import io.fischer.quantumpen.users.dto.request.UpdateUserRequestDTO;
import io.fischer.quantumpen.users.dto.response.UserResponseDTO;
import io.fischer.quantumpen.users.mapper.UserMapper;
import io.fischer.quantumpen.users.model.User;
import io.fischer.quantumpen.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private User findOrFail(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Usuário de id " + id + " não encontrado!")
        );
    }

    public List<UserResponseDTO> buscarTodos() {
        logger.info("Listando todos os usuários");
        return UserMapper.toResponseList(repository.findAll());
    }

    public UserResponseDTO buscarId(Long id) {
        logger.info("Buscando o usuário de id " + id);
        return UserMapper.toResponseDTO(findOrFail(id));
    }

    public UserResponseDTO criarUsuario(CreateUserRequestDTO dto) {
        logger.info("Criando um usuário");
        return UserMapper.toResponseDTO(
                repository.save(UserMapper.toEntity(dto))
        );
    }

    public UserResponseDTO putUsuario(Long id, UpdateUserRequestDTO dto) {
        logger.info("Substituindo o usuário de id " + id);
        User entity = findOrFail(id);
        UserMapper.putEntity(entity, dto);
        repository.save(entity);
        return UserMapper.toResponseDTO(entity);
    }

    public UserResponseDTO patchUsuario(Long id, UpdateUserRequestDTO dto) {
        logger.info("Atualizando o usuário de id " + id);
        User entity = findOrFail(id);
        UserMapper.patchEntity(entity, dto);
        repository.save(entity);
        return UserMapper.toResponseDTO(entity);
    }

    public void desativarUsuario(Long id) {
        logger.info("Desativando o usuário de id " + id);
        User entity = findOrFail(id);
        entity.setAtivo(false);
    }
}
