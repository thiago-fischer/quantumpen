package io.fischer.quantumpen.estoque.repository;


import io.fischer.quantumpen.estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findByProdutoId(Long produtoId);
}
