package com.example.api_veiculos2.controller;

import com.example.api_veiculos2.dto.request.VeiculoRequestDTO;
import com.example.api_veiculos2.dto.response.VeiculoResponseDTO;
import com.example.api_veiculos2.model.Cliente;
import com.example.api_veiculos2.model.Cor;
import com.example.api_veiculos2.model.Modelo;
import com.example.api_veiculos2.model.Veiculo;
import com.example.api_veiculos2.service.ClienteService;
import com.example.api_veiculos2.service.CorService;
import com.example.api_veiculos2.service.ModeloService;
import com.example.api_veiculos2.service.VeiculoService;
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
 * Controlador REST para gerenciamento de veículos.
 * <p>
 * Esta classe expõe endpoints RESTful para operações CRUD (Create, Read,
 * Update, Delete)
 * relacionadas a veículos. Todos os endpoints retornam respostas no formato
 * JSON.
 * </p>
 * 
 * @author Andre GB Farias @andreriffen
 * @version 1.0
 * @since 2025-12-01
 */
@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final CorService corService;
    private final ModeloService modeloService;
    private final ClienteService clienteService;
    private final ModelMapper modelMapper;

    /**
     * Cria um novo veículo no sistema.
     * 
     * @param veiculo objeto contendo os dados do veículo a ser criado
     * @return ResponseEntity contendo o veículo criado e status HTTP 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> criar(@Valid @RequestBody VeiculoRequestDTO dto) {
        Veiculo novoVeiculo = veiculoService.criar(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(novoVeiculo));
    }

    /**
     * Lista todos os veículos cadastrados no sistema.
     * 
     * @return ResponseEntity contendo a lista de veículos e status HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listarTodos() {
        List<VeiculoResponseDTO> veiculos = veiculoService.listarTodos()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(veiculos);
    }

    /**
     * Busca um veículo específico pelo identificador.
     * 
     * @param id o identificador do veículo
     * @return ResponseEntity contendo o veículo encontrado e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se o veículo não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return veiculoService.buscarPorId(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Atualiza os dados de um veículo existente.
     * 
     * @param id      o identificador do veículo a ser atualizado
     * @param veiculo objeto contendo os novos dados do veículo
     * @return ResponseEntity contendo o veículo atualizado e status HTTP 200 (OK),
     *         ou status HTTP 404 (NOT_FOUND) se o veículo não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizar(@PathVariable Integer id,
            @Valid @RequestBody VeiculoRequestDTO dto) {
        Veiculo veiculo = toEntity(dto);
        return veiculoService.atualizar(id, veiculo)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Remove um veículo do sistema.
     * 
     * @param id o identificador do veículo a ser removido
     * @return ResponseEntity vazio com status HTTP 204 (NO_CONTENT) se o veículo
     *         foi removido,
     *         ou status HTTP 404 (NOT_FOUND) se o veículo não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        boolean removido = veiculoService.deletar(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    private Veiculo toEntity(VeiculoRequestDTO dto) {
        Veiculo veiculo = modelMapper.map(dto, Veiculo.class);
        veiculo.setCor(buscarCor(dto.getCorId()));
        veiculo.setModelo(buscarModelo(dto.getModeloId()));
        veiculo.setProprietario(buscarCliente(dto.getProprietarioId()));
        return veiculo;
    }

    private VeiculoResponseDTO toResponse(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoResponseDTO.class);
    }

    private Cor buscarCor(Integer corId) {
        return corService.buscarPorId(corId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cor não encontrada"));
    }

    private Modelo buscarModelo(Integer modeloId) {
        return modeloService.buscarPorId(modeloId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));
    }

    private Cliente buscarCliente(Integer clienteId) {
        return clienteService.buscarPorId(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
}
