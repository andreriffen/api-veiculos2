package com.example.api_veiculos2.dto.response;

import lombok.Data;

/**
 * Resultado enviado quando listamos/buscamos cores. Serve para mostrar o
 * identificador e o nome já aprovados pelo backend.
 */
@Data
public class CorResponseDTO {

    private Integer id;
    private String nome;
}
