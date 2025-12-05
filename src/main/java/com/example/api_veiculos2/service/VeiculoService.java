package com.example.api_veiculos2.service;

import com.example.api_veiculos2.model.Veiculo;
import com.example.api_veiculos2.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Regras de negócio para {@link Veiculo}.
 * Atua como o Detran da aplicação: confere placas, garante que cada carro tenha
 * dono, cor, modelo e
 * motor certinhos antes de liberar para a rua.
 */
@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public Veiculo criar(Veiculo veiculo) {
        validarPlacaDuplicada(veiculo.getPlaca(), null);
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Integer id) {
        return veiculoRepository.findById(id);
    }

    public Optional<Veiculo> atualizar(Integer id, Veiculo dadosAtualizados) {
        return veiculoRepository.findById(id)
                .map(veiculoExistente -> {
                    validarPlacaDuplicada(dadosAtualizados.getPlaca(), id);
                    veiculoExistente.setPlaca(dadosAtualizados.getPlaca());
                    veiculoExistente.setObservacoes(dadosAtualizados.getObservacoes());
                    veiculoExistente.setCor(dadosAtualizados.getCor());
                    veiculoExistente.setModelo(dadosAtualizados.getModelo());
                    veiculoExistente.setProprietario(dadosAtualizados.getProprietario());
                    return veiculoRepository.save(veiculoExistente);
                });
    }

    public boolean deletar(Integer id) {
        return veiculoRepository.findById(id)
                .map(veiculo -> {
                    veiculoRepository.delete(veiculo);
                    return true;
                })
                .orElse(false);
    }

    // Placa repetida é multa instantânea aqui também, então checamos antes de
    // salvar.
    private void validarPlacaDuplicada(String placa, Integer idParaIgnorar) {
        boolean duplicada = idParaIgnorar == null
                ? veiculoRepository.existsByPlacaIgnoreCase(placa)
                : veiculoRepository.existsByPlacaIgnoreCaseAndIdNot(placa, idParaIgnorar);

        if (duplicada) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Já existe um veículo cadastrado com essa placa");
        }
    }
}
