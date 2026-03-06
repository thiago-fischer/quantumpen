package io.fischer.quantumpen.clientes.service;

import io.fischer.quantumpen.clientes.dto.request.CreateClienteDTO;
import io.fischer.quantumpen.clientes.dto.request.UpdateClienteDTO;
import io.fischer.quantumpen.clientes.dto.response.ClienteResponseDTO;
import io.fischer.quantumpen.clientes.mapper.ClienteMapper;
import io.fischer.quantumpen.clientes.model.Cliente;
import io.fischer.quantumpen.clientes.repository.ClienteRepository;
import io.fischer.quantumpen.exception.NotFoundException;
import io.fischer.quantumpen.users.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final Logger logger = Logger.getLogger(ClienteService.class.getName());

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    private Cliente findOrFail(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Cliente de id " + id + " não encontrado!")
        );
    }

    public List<ClienteResponseDTO> findAll() {
        logger.info("Listando todos os clientes");
        return ClienteMapper.toListDTO(repository.findAll());
    }

    public ClienteResponseDTO findById(Long id) {
        logger.info("Buscando o cliente com id " + id);
        return ClienteMapper.toDTO(findOrFail(id));
    }

    public ClienteResponseDTO createCliente(CreateClienteDTO dto) {
        logger.info("Criando um novo cliente");
        Cliente cliente = ClienteMapper.toEntity(dto);

        User user = new User();
        user.setEmail(dto.email());
        user.setSenha(dto.senha());
        user.setRole("CLIENTE");
        user.setAtivo(true);

        cliente.setUser(user);

        return ClienteMapper.toDTO(repository.save(cliente));
    }

    public ClienteResponseDTO putCliente(Long id, UpdateClienteDTO dto) {
        logger.info("Sobreescrevendo o cliente de id " + id);
        Cliente entity = findOrFail(id);
        ClienteMapper.putCliente(entity, dto);
        repository.save(entity);
        return ClienteMapper.toDTO(entity);
    }

    public ClienteResponseDTO patchCliente(Long id, UpdateClienteDTO dto) {
        logger.info("Alterando o cliente de id " + id);
        Cliente entity = findOrFail(id);
        ClienteMapper.patchCliente(entity, dto);
        repository.save(entity);
        return ClienteMapper.toDTO(entity);
    }

    public void deleteCliente(Long id) {
        logger.info("Deletando o cliente de id " + id);
        Cliente entity = findOrFail(id);
        repository.delete(entity);
    }

}
