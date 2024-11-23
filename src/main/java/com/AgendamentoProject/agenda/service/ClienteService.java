package com.AgendamentoProject.agenda.service;

import com.AgendamentoProject.agenda.entity.Agenda;
import com.AgendamentoProject.agenda.entity.Cliente;
import com.AgendamentoProject.agenda.entity.Endereco;
import com.AgendamentoProject.agenda.repository.ClienteRepository;
import com.AgendamentoProject.agenda.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente add(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findById(Integer id){
        return clienteRepository.findById(id);
    }

    public void delete(Integer id){
        clienteRepository.deleteById(id);
    }



//    public Cliente salvarCliente(Cliente cliente){
//        Endereco endereco = cliente.getEndereco();
//        if (endereco != null || endereco.getCep() != null && endereco.getCep().length() == 8){
//            enderecoRepository.save(endereco);
//        }
//        return clienteRepository.save(cliente);
//    }
//
//    @Transactional
//    public Cliente atualizarCliente(int id, Cliente clienteAtualizado) {
//        Cliente clienteExistente = clienteRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
//
//        clienteExistente.setNome(clienteAtualizado.getNome());
//        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
//        clienteExistente.setEmail(clienteAtualizado.getEmail());
//        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
//
//        return clienteRepository.save(clienteExistente);
//    }
//
//    public List<Cliente> listarCliente(){
//        return clienteRepository.findAll();
//    }
//
//    public Optional<Cliente> buscarporid(int id){
//        return clienteRepository.findById(id);
//    }
//
//    public List<Cliente> buscarPorNome(String nome) {
//        return clienteRepository.findByNomeContainingIgnoreCase(nome);
//    }
//
//    @Transactional
//    public void excluirCliente(int id) {
//        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
//        if (clienteOptional.isPresent()) {
//            clienteRepository.delete(clienteOptional.get());
//        }
//    }



}
