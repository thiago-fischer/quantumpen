package io.fischer.quantumpen.users.controller;

import io.fischer.quantumpen.users.dto.request.CreateUserRequestDTO;
import io.fischer.quantumpen.users.dto.request.UpdateUserRequestDTO;
import io.fischer.quantumpen.users.dto.response.UserResponseDTO;
import io.fischer.quantumpen.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public List<UserResponseDTO> findAll() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable("id") Long id) {
        return service.buscarId(id);
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody CreateUserRequestDTO dto) {
        return service.criarUsuario(dto);
    }

    @PutMapping("/{id}")
    public UserResponseDTO putUser(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserRequestDTO dto
    ) {
        return service.putUsuario(id, dto);
    }

    @PatchMapping("/{id}")
    public UserResponseDTO patchUser(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserRequestDTO dto
    ) {
        return service.patchUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        service.desativarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
