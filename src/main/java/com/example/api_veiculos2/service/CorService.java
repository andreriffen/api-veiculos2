package com.example.api_veiculos2.service;

import com.example.api_veiculos2.model.Cor;
import com.example.api_veiculos2.repository.CorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Regras de negócio para {@link Cor}.
 * Esta classe é o estilista que evita coleções repetidas: se alguém quiser
 * cadastrar "vermelho Ferrari"
 * duas vezes, ela solta um "já temos essa tinta no catálogo".
 */
@Service
@RequiredArgsConstructor
public class CorService {

    private final CorRepository corRepository;

    public Cor criar(Cor cor) {
        validarNomeDuplicado(cor.getNome(), null);
        return corRepository.save(cor);
    }

    public List<Cor> listarTodas() {
        return corRepository.findAll();
    }

    public Optional<Cor> buscarPorId(Integer id) {
        return corRepository.findById(id);
    }

    public Optional<Cor> atualizar(Integer id, Cor dadosAtualizados) {
        return corRepository.findById(id)
                .map(corExistente -> {
                    validarNomeDuplicado(dadosAtualizados.getNome(), id);
                    corExistente.setNome(dadosAtualizados.getNome());
                    return corRepository.save(corExistente);
                });
    }

    public boolean deletar(Integer id) {
        return corRepository.findById(id)
                .map(cor -> {
                    corRepository.delete(cor);
                    return true;
                })
                .orElse(false);
    }

    // Reaproveitamos essa lógica tanto no POST quanto no PUT para barrar nomes
    // clones.
    private void validarNomeDuplicado(String nome, Integer idParaIgnorar) {
        boolean duplicado = idParaIgnorar == null
                ? corRepository.existsByNomeIgnoreCase(nome)
                : corRepository.existsByNomeIgnoreCaseAndIdNot(nome, idParaIgnorar);

        if (duplicado) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Já existe uma cor cadastrada com esse nome");
        }
    }
}
