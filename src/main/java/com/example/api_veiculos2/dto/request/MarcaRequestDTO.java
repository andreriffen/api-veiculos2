package com.example.api_veiculos2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO da marca: outra ficha simples, porque marca é basicamente um nome bonito
 * estampado no carro.
 * Usa Bean Validation pra evitar cadastros vazios tipo "Marca Sem Nome S/A".
 */
@Data
public class MarcaRequestDTO {

    // 100 caracteres são suficientes para "International Superstar Motors" se
    // precisar.
    @NotBlank(message = "Nome da marca é obrigatório")
    @Size(max = 100, message = "Nome da marca deve ter no máximo 100 caracteres")
    private String nome;
}
