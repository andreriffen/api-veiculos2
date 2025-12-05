package com.example.api_veiculos2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um modelo de veículo.
 * <p>
 * Esta classe mapeia a tabela "modelos" no banco de dados e contém
 * as informações de modelos associados a marcas específicas.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modelos")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Descrição do modelo é obrigatória")
    @Column(nullable = false)
    private String descricao;

    @Embedded
    private Motor motor;

    @NotNull(message = "Marca é obrigatória")
    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;
}
