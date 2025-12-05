package com.example.api_veiculos2.dto.response;

import com.example.api_veiculos2.dto.common.MotorDTO;
import lombok.Data;

/**
 * Retorno dos modelos: além do identificador e descrição, já embute o objeto da
 * marca para que o
 * consumidor não precise fazer outra requisição só pra descobrir a fabricante.
 */
@Data
public class ModeloResponseDTO {

    private Integer id;
    private String descricao;
    private MotorDTO motor;
    private MarcaResponseDTO marca;
}
