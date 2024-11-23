package com.AgendamentoProject.agenda.service;

import com.AgendamentoProject.agenda.entity.Endereco;
import com.AgendamentoProject.agenda.entity.Funcionario;
import com.AgendamentoProject.agenda.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll(){
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(Integer id){
        return funcionarioRepository.findById(id);
    }

    public Funcionario save(Funcionario funcionario){
        return funcionarioRepository.saveAndFlush(funcionario);
    }

    public void deleteById(Integer id){
        funcionarioRepository.deleteById(id);
    }


//    @Transactional
//    public Funcionario salvarFuncionario(Funcionario funcionario) {
//        return funcionarioRepository.save(funcionario);
//    }
//
//    public Optional<Funcionario> buscarPorId(int id) {
//        return funcionarioRepository.findById(id);
//    }
//
//    public List<Funcionario> listarFuncionarios() {
//        return funcionarioRepository.findAll();
//    }
//
//    @Transactional
//    public Funcionario atualizarFuncionario(int id, Funcionario funcionarioAtualizado) {
//        Funcionario funcionarioCriado = funcionarioRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Funcionario nao ta aqui não"));
//
//        funcionarioCriado.setNome(funcionarioAtualizado.getNome());
//        funcionarioCriado.setTelefone(funcionarioAtualizado.getTelefone());
//        funcionarioCriado.setEmail(funcionarioAtualizado.getEmail());
//        funcionarioCriado.setEndereco(funcionarioAtualizado.getEndereco());
//        funcionarioCriado.setSalario(funcionarioAtualizado.getSalario());
//        return funcionarioRepository.save(funcionarioCriado);
//    }
//
//    public List<Funcionario> buscarPorNome(String nome) {
//        return funcionarioRepository.findByNomeContainingIgnoreCase(nome);
//    }
//
//    @Transactional
//    public void excluirFuncionario(int id) {
//        if (funcionarioRepository.existsById(id)) {
//            funcionarioRepository.deleteById(id);
//        } else {
//            throw new RuntimeException("Funcionário não encontrado");
//        }
//    }

  }
