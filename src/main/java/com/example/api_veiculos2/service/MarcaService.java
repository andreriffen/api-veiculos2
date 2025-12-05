package com.example.api_veiculos2.service;

import com.example.api_veiculos2.model.Marca;
import com.example.api_veiculos2.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço para regras de negócio relacionadas a {@link Marca}.
 * Tradução livre: é o departamento jurídico das montadoras, garantindo que não
 * existam duas "Tesla"
 * cadastradas só porque alguém esqueceu a letra maiúscula.
 */
@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public Marca criar(Marca marca) {
        validarNomeDuplicado(marca.getNome(), null);
        return marcaRepository.save(marca);
    }

    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> buscarPorId(Integer id) {
        return marcaRepository.findById(id);
    }

    public Optional<Marca> atualizar(Integer id, Marca dadosAtualizados) {
        return marcaRepository.findById(id)
                .map(marcaExistente -> {
                    validarNomeDuplicado(dadosAtualizados.getNome(), id);
                    marcaExistente.setNome(dadosAtualizados.getNome());
                    return marcaRepository.save(marcaExistente);
                });
    }

    public boolean deletar(Integer id) {
        return marcaRepository.findById(id)
                .map(marca -> {
                    marcaRepository.delete(marca);
                    return true;
                })
                .orElse(false);
    }

    // Mesma verificação usada ao criar/atualizar para evitar marcas com nomes
    // clonados.
    private void validarNomeDuplicado(String nome, Integer idParaIgnorar) {
        boolean duplicado = idParaIgnorar == null
                ? marcaRepository.existsByNomeIgnoreCase(nome)
                : marcaRepository.existsByNomeIgnoreCaseAndIdNot(nome, idParaIgnorar);

        if (duplicado) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Já existe uma marca cadastrada com esse nome");
        }
    }
}
