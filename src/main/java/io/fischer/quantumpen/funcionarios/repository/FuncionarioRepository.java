package io.fischer.quantumpen.funcionarios.repository;

import io.fischer.quantumpen.funcionarios.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
