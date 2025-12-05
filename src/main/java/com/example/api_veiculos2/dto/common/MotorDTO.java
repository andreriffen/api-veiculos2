package com.example.api_veiculos2.dto.common;

import com.example.api_veiculos2.model.enums.ETipoCombustivel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Representa os dados transferidos de um motor embutido em um veículo.
 *
 * Explicando pro futuro eu-mesmo:
 * - Esta classe vive em dto/common porque o motor aparece dentro de vários DTOs
 * (pedacinhos reutilizados)
 * e ninguém merece repetir essas validações em cada request/response.
 * - Pense nela como o formulário do motor: um lugarzinho para perguntar
 * "quantos cavalos? qual combustível?".
 * 
 * Basicamente é um anti-burro que usa jakarta validation como barreira
 * 
 */
@Data
public class MotorDTO {

    // Potência: limitamos entre 1 e 2000 para servir tanto o Fusca humildão quanto 
    // pro carro do Toretto do Velozes & Furiosos.
    @Min(value = 1, message = "Potência deve ser positiva")
    @Max(value = 2000, message = "Potência não pode ultrapassar 2000 cv")
    private int potencia;

    // Sem combustível não tem carro andando: obrigamos informar o tipo (gasolina,
    // etanol, etc.).
    @NotNull(message = "Tipo de combustível é obrigatório")
    private ETipoCombustivel tipoCombustivel;
}
