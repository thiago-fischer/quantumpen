package io.fischer.quantumpen.clientes.repository;

import io.fischer.quantumpen.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
