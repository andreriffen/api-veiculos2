package com.example.api_veiculos2.dto.request;

import com.example.api_veiculos2.dto.common.MotorDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Quando o usuário fala "quero cadastrar um Modelo", este DTO é o que chega.
 * Junta a descrição (nome comercial) e diz de qual marca ele faz parte.
 */
@Data
public class ModeloRequestDTO {

    // Descrição do modelo: Mustang GT, Uno Mille, etc. Cabe em 120 caracteres para
    // evitar romances.
    @NotBlank(message = "Descrição do modelo é obrigatória")
    @Size(max = 120, message = "Descrição do modelo deve ter no máximo 120 caracteres")
    private String descricao;

    // Motor detalhado reutiliza o DTO comum. @Valid garante que as validações
    // internas do motor rodem também.
    @Valid
    @NotNull(message = "Dados do motor são obrigatórios")
    private MotorDTO motor;

    // Sem marca não existe modelo, então obrigamos informar o ID positivo da marca
    // já cadastrada.
    @NotNull(message = "Identificador da marca é obrigatório")
    @Positive(message = "Identificador da marca deve ser positivo")
    private Integer marcaId;
}
