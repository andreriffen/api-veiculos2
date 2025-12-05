package com.example.api_veiculos2.controller;

import com.example.api_veiculos2.dto.request.ModeloRequestDTO;
import com.example.api_veiculos2.dto.response.ModeloResponseDTO;
import com.example.api_veiculos2.model.Marca;
import com.example.api_veiculos2.model.Modelo;
import com.example.api_veiculos2.service.MarcaService;
import com.example.api_veiculos2.service.ModeloService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gerenciamento de modelos de veículos.
 * <p>
 * Esta classe expõe endpoints RESTful para operações CRUD (Create, Read,
 * Update, Delete)
 * relacionadas a modelos. Todos os endpoints retornam respostas no formato
 * JSON.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
@RestController
@RequestMapping("/modelos")
@RequiredArgsConstructor
public class ModeloController {

    private final ModeloService modeloService;
    private final MarcaService marcaService;
    private final ModelMapper modelMapper;

    /**
     * Cria um novo modelo no sistema.
     * 
     * @param modelo objeto contendo os dados do modelo a ser criado
     * @return ResponseEntity contendo o modelo criado e status HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<ModeloResponseDTO> criar(@Valid @RequestBody ModeloRequestDTO dto) {
        Modelo novoModelo = modeloService.criar(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(novoModelo));
    }

    /**
     * Lista todos os modelos cadastrados no sistema.
     * 
     * @return ResponseEntity contendo a lista de modelos e status HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<ModeloResponseDTO>> listarTodos() {
        List<ModeloResponseDTO> modelos = modeloService.listarTodos()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(modelos);
    }

    /**
     * Busca um modelo específico pelo identificador.
     * 
     * @param id o identificador do modelo
     * @return ResponseEntity contendo o modelo encontrado e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se o modelo não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModeloResponseDTO> buscarPorId(@PathVariable Integer id) {
        return modeloService.buscarPorId(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Atualiza os dados de um modelo existente.
     * 
     * @param id     o identificador do modelo a ser atualizado
     * @param modelo objeto contendo os novos dados do modelo
     * @return ResponseEntity contendo o modelo atualizado e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se o modelo não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModeloResponseDTO> atualizar(@PathVariable Integer id,
            @Valid @RequestBody ModeloRequestDTO dto) {
        Modelo modelo = toEntity(dto);
        return modeloService.atualizar(id, modelo)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove um modelo do sistema.
     * 
     * @param id o identificador do modelo a ser removido
     * @return ResponseEntity vazio com status HTTP 204 (NO_CONTENT) se o modelo foi
     *         removido,
     *         ou status HTTP 404 (NOT_FOUND) se o modelo não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean removido = modeloService.deletar(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private Modelo toEntity(ModeloRequestDTO dto) {
        Modelo modelo = modelMapper.map(dto, Modelo.class);
        modelo.setMarca(buscarMarca(dto.getMarcaId()));
        return modelo;
    }

    private ModeloResponseDTO toResponse(Modelo modelo) {
        return modelMapper.map(modelo, ModeloResponseDTO.class);
    }

    private Marca buscarMarca(Integer marcaId) {
        return marcaService.buscarPorId(marcaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));
    }
}
