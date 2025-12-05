package com.example.api_veiculos2.controller;

import com.example.api_veiculos2.dto.request.CorRequestDTO;
import com.example.api_veiculos2.dto.response.CorResponseDTO;
import com.example.api_veiculos2.model.Cor;
import com.example.api_veiculos2.service.CorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gerenciamento de cores de veículos.
 * <p>
 * Esta classe expõe endpoints RESTful para operações CRUD (Create, Read,
 * Update, Delete)
 * relacionadas a cores. Todos os endpoints retornam respostas no formato JSON.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
@RestController
@RequestMapping("/cores")
@RequiredArgsConstructor
public class CorController {

    private final CorService corService;
    private final ModelMapper modelMapper;

    /**
     * Cria uma nova cor no sistema.
     * 
     * @param cor objeto contendo os dados da cor a ser criada
     * @return ResponseEntity contendo a cor criada e status HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<CorResponseDTO> criar(@Valid @RequestBody CorRequestDTO dto) {
        Cor novaCor = corService.criar(modelMapper.map(dto, Cor.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(novaCor));
    }

    /**
     * Lista todas as cores cadastradas no sistema.
     * 
     * @return ResponseEntity contendo a lista de cores e status HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<CorResponseDTO>> listarTodas() {
        List<CorResponseDTO> cores = corService.listarTodas()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cores);
    }

    /**
     * Busca uma cor específica pelo identificador.
     * 
     * @param id o identificador da cor
     * @return ResponseEntity contendo a cor encontrada e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se a cor não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<CorResponseDTO> buscarPorId(@PathVariable Integer id) {
        return corService.buscarPorId(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Atualiza os dados de uma cor existente.
     * 
     * @param id  o identificador da cor a ser atualizada
     * @param cor objeto contendo os novos dados da cor
     * @return ResponseEntity contendo a cor atualizada e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se a cor não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<CorResponseDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody CorRequestDTO dto) {
        Cor cor = modelMapper.map(dto, Cor.class);
        return corService.atualizar(id, cor)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove uma cor do sistema.
     * 
     * @param id o identificador da cor a ser removida
     * @return ResponseEntity vazio com status HTTP 204 (NO_CONTENT) se a cor foi
     *         removida,
     *         ou status HTTP 404 (NOT_FOUND) se a cor não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean removida = corService.deletar(id);
        return removida ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private CorResponseDTO toResponse(Cor cor) {
        return modelMapper.map(cor, CorResponseDTO.class);
    }
}
