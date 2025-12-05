package com.example.api_veiculos2.dto.response;

import java.time.LocalDate;

import lombok.Data;

/**
 * Payload que volta para quem consome a API quando pedem informações de
 * clientes. Pense nele como a
 * versão "já validada e salva" do
 * {@link com.example.api_veiculos2.dto.request.ClienteRequestDTO}.
 */
@Data
public class ClienteResponseDTO {

    // ID gerado pelo banco para referência futura.
    private Integer id;
    // Demais campos repetem o que veio da request, mas agora oficializados.
    private String nome;
    private String celular;
    private String email;
    private LocalDate dataCadastro;
}
