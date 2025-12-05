package com.example.api_veiculos2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidade que representa um cliente da lavação de veículos.
 * <p>
 * Esta classe mapeia a tabela "clientes" no banco de dados e armazena
 * informações de contato e cadastro dos clientes proprietários de veículos.
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
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome do cliente é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Celular é obrigatório")
    @Column(nullable = false)
    private String celular;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "Data de cadastro é obrigatória")
    @Column(nullable = false)
    private LocalDate dataCadastro;
}
