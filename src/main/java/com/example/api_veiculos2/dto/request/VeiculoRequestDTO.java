package com.example.api_veiculos2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO mais completo da camada de request: descreve tudo que o backend precisa
 * saber para registrar
 * um veículo novo. É praticamente a ficha técnica do carro + relacionamentos.
 */
@Data
public class VeiculoRequestDTO {

    // Placa no padrão Mercosul. A regex barra placas estilo "ABC1234" antiquadas e
    // genéricas "AAAAAAA".
    @NotBlank(message = "Placa é obrigatória")
    @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$", message = "Placa deve seguir o padrão Mercosul, ex: ABC1D23")
    private String placa;

    // Campo livre para fofocas mecânicas: até 600 caracteres de observações.
    @Size(max = 600, message = "Observações deve ter no máximo 600 caracteres")
    private String observacoes;

    // Cada relacionamento usa IDs positivos para olhar tabelas já existentes (cor,
    // modelo, proprietário).
    @NotNull(message = "Cor é obrigatória")
    @Positive(message = "Identificador da cor deve ser positivo")
    private Integer corId;

    @NotNull(message = "Modelo é obrigatório")
    @Positive(message = "Identificador do modelo deve ser positivo")
    private Integer modeloId;

    @NotNull(message = "Proprietário é obrigatório")
    @Positive(message = "Identificador do proprietário deve ser positivo")
    private Integer proprietarioId;
}
