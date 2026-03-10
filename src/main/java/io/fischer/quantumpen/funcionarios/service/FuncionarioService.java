package io.fischer.quantumpen.funcionarios.service;

import io.fischer.quantumpen.exception.NotFoundException;
import io.fischer.quantumpen.funcionarios.dto.request.CreateFuncionarioDTO;
import io.fischer.quantumpen.funcionarios.dto.request.UpdateFuncionarioDTO;
import io.fischer.quantumpen.funcionarios.dto.response.FuncionarioResponseDTO;
import io.fischer.quantumpen.funcionarios.mapper.FuncionarioMapper;
import io.fischer.quantumpen.funcionarios.model.Funcionario;
import io.fischer.quantumpen.funcionarios.repository.FuncionarioRepository;
import io.fischer.quantumpen.users.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final Logger logger = Logger.getLogger(FuncionarioService.class.getName());

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    private Funcionario findOrFail(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Funcionario de id " + id + " não encontrado!")
        );
    }

    public List<FuncionarioResponseDTO> findAll() {
        logger.info("Listando todos os funcionarios");
        return FuncionarioMapper.toListDTO(repository.findAll());
    }

    public FuncionarioResponseDTO findById(Long id) {
        logger.info("Buscando o funcionario com id " + id);
        return FuncionarioMapper.toDTO(findOrFail(id));
    }

    public FuncionarioResponseDTO createFuncionario(CreateFuncionarioDTO dto) {

        logger.info("Criando um novo funcionario");

        Funcionario funcionario = FuncionarioMapper.toEntity(dto);

        User user = new User();
        user.setEmail(dto.email());
        user.setSenha(dto.senha());
        user.setRole("FUNCIONARIO");
        user.setAtivo(true);

        funcionario.setUser(user);

        return FuncionarioMapper.toDTO(repository.save(funcionario));
    }

    public FuncionarioResponseDTO putFuncionario(Long id, UpdateFuncionarioDTO dto) {

        logger.info("Sobreescrevendo o funcionario de id " + id);

        Funcionario entity = findOrFail(id);

        FuncionarioMapper.putFuncionario(entity, dto);

        repository.save(entity);

        return FuncionarioMapper.toDTO(entity);
    }

    public FuncionarioResponseDTO patchFuncionario(Long id, UpdateFuncionarioDTO dto) {

        logger.info("Alterando o funcionario de id " + id);

        Funcionario entity = findOrFail(id);

        FuncionarioMapper.patchFuncionario(entity, dto);

        repository.save(entity);

        return FuncionarioMapper.toDTO(entity);
    }

    public void deleteFuncionario(Long id) {

        logger.info("Deletando o funcionario de id " + id);

        Funcionario entity = findOrFail(id);

        repository.delete(entity);
    }

}