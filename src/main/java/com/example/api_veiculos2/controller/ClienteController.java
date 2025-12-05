package com.example.api_veiculos2.controller;

import com.example.api_veiculos2.dto.request.ClienteRequestDTO;
import com.example.api_veiculos2.dto.response.ClienteResponseDTO;
import com.example.api_veiculos2.model.Cliente;
import com.example.api_veiculos2.service.ClienteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gerenciamento de clientes.
 * <p>
 * Esta classe expõe endpoints RESTful para operações CRUD (Create, Read,
 * Update, Delete)
 * relacionadas a clientes. Todos os endpoints retornam respostas no formato
 * JSON.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    /**
     * Cria um novo cliente no sistema.
     * 
     * @param cliente objeto contendo os dados do cliente a ser criado
     * @return ResponseEntity contendo o cliente criado e status HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(@Valid @RequestBody ClienteRequestDTO dto) {
        Cliente novoCliente = clienteService.criar(modelMapper.map(dto, Cliente.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(novoCliente));
    }

    /**
     * Lista todos os clientes cadastrados no sistema.
     * 
     * @return ResponseEntity contendo a lista de clientes e status HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        List<ClienteResponseDTO> clientes = clienteService.listarTodos()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    /**
     * Busca um cliente específico pelo identificador.
     * 
     * @param id o identificador do cliente
     * @return ResponseEntity contendo o cliente encontrado e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se o cliente não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Integer id) {
        return clienteService.buscarPorId(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Atualiza os dados de um cliente existente.
     * 
     * @param id      o identificador do cliente a ser atualizado
     * @param cliente objeto contendo os novos dados do cliente
     * @return ResponseEntity contendo o cliente atualizado e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se o cliente não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Integer id,
            @Valid @RequestBody ClienteRequestDTO dto) {
        Cliente cliente = modelMapper.map(dto, Cliente.class);
        return clienteService.atualizar(id, cliente)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove um cliente do sistema.
     * 
     * @param id o identificador do cliente a ser removido
     * @return ResponseEntity vazio com status HTTP 204 (NO_CONTENT) se o cliente
     *         foi removido,
     *         ou status HTTP 404 (NOT_FOUND) se o cliente não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean removido = clienteService.deletar(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private ClienteResponseDTO toResponse(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }
}
