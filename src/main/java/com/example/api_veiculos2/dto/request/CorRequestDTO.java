package com.example.api_veiculos2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Formulário minimalista para cadastrar/editar cores. Quase um post-it: só
 * precisa saber o nome
 * da tinta que vai no carro. Validações garantem que ninguém tente registrar ""
 * ou textos quilométricos.
 */
@Data
public class CorRequestDTO {

    // Nome simpático e curto (até 60 letras). "Preto" sim, "Preto com nuances
    // cintilantes" também.
    @NotBlank(message = "Nome da cor é obrigatório")
    @Size(max = 60, message = "Nome da cor deve ter no máximo 60 caracteres")
    private String nome;
}
