package com.example.api_veiculos2.dto.response;

import lombok.Data;

/**
 * Espelho das marcas salvas: devolve o id (pra futuras referências) e o nome
 * oficializado.
 */
@Data
public class MarcaResponseDTO {

    private Integer id;
    private String nome;
}
