package com.example.api_veiculos2.dto.response;

import lombok.Data;

/**
 * Quando alguém faz GET /veiculos, este é o pacote completo que entregamos.
 * Inclui placa, observações e todos os relacionamentos já resolvidos
 * (cor, modelo, dono) para evitar caça ao tesouro em múltiplas
 * requisições.
 * 
 * ISSO FAZ UMA BAITA DIFERENÇA, CONFIA...!
 * 
 */
@Data
public class VeiculoResponseDTO {

    private Integer id;
    private String placa;
    private String observacoes;
    private CorResponseDTO cor;
    private ModeloResponseDTO modelo;
    private ClienteResponseDTO proprietario;
}
