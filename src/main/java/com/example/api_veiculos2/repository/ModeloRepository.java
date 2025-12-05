package com.example.api_veiculos2.repository;

import com.example.api_veiculos2.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de acesso a dados da entidade Modelo.
 * <p>
 * Esta interface estende {@link JpaRepository} fornecendo métodos CRUD padrão
 * para a entidade {@link Modelo}.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

    boolean existsByDescricaoIgnoreCaseAndMarcaId(String descricao, Integer marcaId);

    boolean existsByDescricaoIgnoreCaseAndMarcaIdAndIdNot(String descricao, Integer marcaId, Integer id);
}
