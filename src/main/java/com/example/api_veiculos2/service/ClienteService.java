package com.example.api_veiculos2.service;

import com.example.api_veiculos2.model.Cliente;
import com.example.api_veiculos2.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Regras de negócio para {@link Cliente}.
 * Versão em português claro: esta classe é o "call center" que impede cadastrar
 * o mesmo cliente duas
 * vezes e lembra o banco de dados de que telefone e e-mail não são figurinhas
 * repetidas.
 */
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente criar(Cliente cliente) {
        validarDuplicidadeContato(cliente, null);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> atualizar(Integer id, Cliente dadosAtualizados) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    validarDuplicidadeContato(dadosAtualizados, id);
                    clienteExistente.setNome(dadosAtualizados.getNome());
                    clienteExistente.setCelular(dadosAtualizados.getCelular());
                    clienteExistente.setEmail(dadosAtualizados.getEmail());
                    clienteExistente.setDataCadastro(dadosAtualizados.getDataCadastro());
                    return clienteRepository.save(clienteExistente);
                });
    }

    public boolean deletar(Integer id) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return true;
                })
                .orElse(false);
    }

    // Faz o papel do fiscal chato que checa se já existe alguém com o mesmo
    // e-mail/celular.
    private void validarDuplicidadeContato(Cliente cliente, Integer idParaIgnorar) {
        boolean emailDuplicado = idParaIgnorar == null
                ? clienteRepository.existsByEmailIgnoreCase(cliente.getEmail())
                : clienteRepository.existsByEmailIgnoreCaseAndIdNot(cliente.getEmail(), idParaIgnorar);

        if (emailDuplicado) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Email já cadastrado para outro cliente");
        }

        boolean celularDuplicado = idParaIgnorar == null
                ? clienteRepository.existsByCelular(cliente.getCelular())
                : clienteRepository.existsByCelularAndIdNot(cliente.getCelular(), idParaIgnorar);

        if (celularDuplicado) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Celular já cadastrado para outro cliente");
        }
    }
}
