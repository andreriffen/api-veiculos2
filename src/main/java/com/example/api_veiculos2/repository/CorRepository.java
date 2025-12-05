package com.example.api_veiculos2.repository;

import com.example.api_veiculos2.model.Cor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de acesso a dados da entidade Cor.
 * <p>
 * Esta interface estende {@link JpaRepository} fornecendo métodos CRUD padrão
 * para a entidade {@link Cor}.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
@Repository
public interface CorRepository extends JpaRepository<Cor, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Integer id);
}
