package com.example.api_veiculos2.service;

import com.example.api_veiculos2.model.Modelo;
import com.example.api_veiculos2.repository.ModeloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Regras de negócio para {@link Modelo}.
 * Pense nela como o cartório que casa "descrição" com "marca" e não deixa
 * ninguém inventar dois
 * "Gol Bola" para a mesma fabricante.
 */
@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;

    public Modelo criar(Modelo modelo) {
        validarDuplicidade(modelo, null);
        return modeloRepository.save(modelo);
    }

    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    public Optional<Modelo> buscarPorId(Integer id) {
        return modeloRepository.findById(id);
    }

    public Optional<Modelo> atualizar(Integer id, Modelo dadosAtualizados) {
        return modeloRepository.findById(id)
                .map(modeloExistente -> {
                    validarDuplicidade(dadosAtualizados, id);
                    modeloExistente.setDescricao(dadosAtualizados.getDescricao());
                    modeloExistente.setMotor(dadosAtualizados.getMotor());
                    modeloExistente.setMarca(dadosAtualizados.getMarca());
                    return modeloRepository.save(modeloExistente);
                });
    }

    public boolean deletar(Integer id) {
        return modeloRepository.findById(id)
                .map(modelo -> {
                    modeloRepository.delete(modelo);
                    return true;
                })
                .orElse(false);
    }

    // Valida se a combinação descrição + marca já existe; modelos são fiéis a uma
    // marca específica.
    private void validarDuplicidade(Modelo modelo, Integer idParaIgnorar) {
        Integer marcaId = modelo.getMarca() != null ? modelo.getMarca().getId() : null;
        if (marcaId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Modelo precisa estar associado a uma marca");
        }

        boolean duplicado = idParaIgnorar == null
                ? modeloRepository.existsByDescricaoIgnoreCaseAndMarcaId(modelo.getDescricao(), marcaId)
                : modeloRepository.existsByDescricaoIgnoreCaseAndMarcaIdAndIdNot(modelo.getDescricao(), marcaId,
                        idParaIgnorar);

        if (duplicado) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Já existe um modelo com essa descrição para a mesma marca");
        }
    }
}
