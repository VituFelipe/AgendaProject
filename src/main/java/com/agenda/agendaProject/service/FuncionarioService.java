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

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(int id) {
        return funcionarioRepository.findById(id);
    }

    @Transactional
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public Funcionario atualizarFuncionario(int id, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
        funcionarioExistente.setEmail(funcionarioAtualizado.getEmail());
        funcionarioExistente.setEndereco(funcionarioAtualizado.getEndereco());
        funcionarioExistente.setSalario(funcionarioAtualizado.getSalario());

        return funcionarioRepository.save(funcionarioExistente);
    }

    @Transactional
    public void excluirFuncionario(int id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Funcionário não encontrado");
        }
    }

    public List<Funcionario> buscarPorNome(String nome) {
        return funcionarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Funcionario> buscarPorSalarioAcimaDe(double salario) {
        return funcionarioRepository.findBySalarioGreaterThan(salario);
    }
}
