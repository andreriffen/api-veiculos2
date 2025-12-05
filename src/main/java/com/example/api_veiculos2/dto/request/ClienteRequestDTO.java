package com.example.api_veiculos2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO usado quando alguém cria/edita um cliente via API. Pense nele como o
 * formulário de cadastro
 * que o frontend entrega para o backend. Tudo que está anotado aqui vira
 * validação automática.
 */
@Data
public class ClienteRequestDTO {

    // Nome: obrigatório e com limite razoável. Se passar de 120 caracteres já
    // parece nome de firma fantasma.
    @NotBlank(message = "Nome do cliente é obrigatório")
    @Size(max = 120, message = "Nome do cliente deve ter no máximo 120 caracteres")
    private String nome;

    // Celular brasileiro com jeitinho: aceita +55, DDD com/sem parênteses e o traço
    // opcional.
    // Regex dá aquele abraço apertado pra impedir "1234" virar telefone.
    @NotBlank(message = "Celular é obrigatório")
    @Pattern(regexp = "^(\\+55)?\\s?\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$", message = "Celular deve estar no formato 48999999999 ou (48) 99999-9999")
    private String celular;

    // Email: não vale deixar em branco, nem inventar textos gigantes. A anotação
    // @Email faz o papel do fiscal.
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(max = 150, message = "Email deve ter no máximo 150 caracteres")
    private String email;

    // Data de cadastro: precisa existir e não pode estar no futuro (ninguém se
    // cadastra amanhã, né?).
    @NotNull(message = "Data de cadastro é obrigatória")
    @PastOrPresent(message = "Data de cadastro não pode estar no futuro")
    private LocalDate dataCadastro;
}
