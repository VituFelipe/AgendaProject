package com.agenda.agendaProject.service;

import com.agenda.agendaProject.model.Cliente;
import com.agenda.agendaProject.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired ClienteService clienteService;

    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    public Optional<Cliente> buscarClientePorId(int id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizarCliente(int id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());

        return clienteRepository.save(clienteExistente);
    }

    public void excluirCliente(int id) {
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }
        throw new RuntimeException("Cliente nao encontrado paizão");
    }

}


