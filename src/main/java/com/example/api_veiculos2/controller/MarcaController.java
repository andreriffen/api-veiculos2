package com.example.api_veiculos2.controller;

import com.example.api_veiculos2.dto.request.MarcaRequestDTO;
import com.example.api_veiculos2.dto.response.MarcaResponseDTO;
import com.example.api_veiculos2.model.Marca;
import com.example.api_veiculos2.service.MarcaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gerenciamento de marcas de veículos.
 * <p>
 * Esta classe expõe endpoints RESTful para operações CRUD (Create, Read,
 * Update, Delete)
 * relacionadas a marcas. Todos os endpoints retornam respostas no formato JSON.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
@RestController
@RequestMapping("/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;
    private final ModelMapper modelMapper;

    /**
     * Cria uma nova marca no sistema.
     * 
     * @param marca objeto contendo os dados da marca a ser criada
     * @return ResponseEntity contendo a marca criada e status HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<MarcaResponseDTO> criar(@Valid @RequestBody MarcaRequestDTO dto) {
        Marca novaMarca = marcaService.criar(modelMapper.map(dto, Marca.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(novaMarca));
    }

    /**
     * Lista todas as marcas cadastradas no sistema.
     * 
     * @return ResponseEntity contendo a lista de marcas e status HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> listarTodas() {
        List<MarcaResponseDTO> marcas = marcaService.listarTodas()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(marcas);
    }

    /**
     * Busca uma marca específica pelo identificador.
     * 
     * @param id o identificador da marca
     * @return ResponseEntity contendo a marca encontrada e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se a marca não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return marcaService.buscarPorId(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Atualiza os dados de uma marca existente.
     * 
     * @param id    o identificador da marca a ser atualizada
     * @param marca objeto contendo os novos dados da marca
     * @return ResponseEntity contendo a marca atualizada e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se a marca não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> atualizar(@PathVariable Integer id,
            @Valid @RequestBody MarcaRequestDTO dto) {
        Marca marca = modelMapper.map(dto, Marca.class);
        return marcaService.atualizar(id, marca)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove uma marca do sistema.
     * 
     * @param id o identificador da marca a ser removida
     * @return ResponseEntity vazio com status HTTP 204 (NO_CONTENT) se a marca foi
     *         removida,
     *         ou status HTTP 404 (NOT_FOUND) se a marca não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean removida = marcaService.deletar(id);
        return removida ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private MarcaResponseDTO toResponse(Marca marca) {
        return modelMapper.map(marca, MarcaResponseDTO.class);
    }
}
