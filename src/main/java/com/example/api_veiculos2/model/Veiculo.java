package com.example.api_veiculos2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um veículo cadastrado no sistema.
 * <p>
 * Esta classe mapeia a tabela "veiculos" no banco de dados e contém
 * todas as informações de um veículo, incluindo seus relacionamentos
 * com modelo, cor e proprietário.
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
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Placa é obrigatória")
    @Column(nullable = false, unique = true)
    private String placa;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @NotNull(message = "Cor é obrigatória")
    @ManyToOne
    @JoinColumn(name = "cor_id", nullable = false)
    private Cor cor;

    @NotNull(message = "Modelo é obrigatório")
    @ManyToOne
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;

    @NotNull(message = "Proprietário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Cliente proprietario;
}
