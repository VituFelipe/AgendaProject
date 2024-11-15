package com.agenda.agendaProject.service;

import com.agenda.agendaProject.model.Funcionario;
import com.agenda.agendaProject.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarFuncionario() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(int id){
        return funcionarioRepository.findById(id);
    }

    @Transactional
    public Funcionario salvarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void deletarFuncionario(int id){
        if (funcionarioRepository.existsById(id)){
            funcionarioRepository.deleteById(id);
        }
        throw new RuntimeException("Funcionario nao existe paizão");
    }

    @Transactional
    public Funcionario atualizarFuncionario(int id, Funcionario funcionarioAtualizado){
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario nao existe paizão"));

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
        funcionarioExistente.setEmail(funcionarioAtualizado.getEmail());
        funcionarioExistente.setEndereco(funcionarioAtualizado.getEndereco());
        funcionarioExistente.setSalario(funcionarioAtualizado.getSalario());
        return funcionarioRepository.save(funcionarioExistente);
    }
}
