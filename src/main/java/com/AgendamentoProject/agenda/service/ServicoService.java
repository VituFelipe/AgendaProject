package com.AgendamentoProject.agenda.service;

import com.AgendamentoProject.agenda.entity.Servico;
import com.AgendamentoProject.agenda.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Transactional
    public Servico criarServico(Servico servico){
        return servicoRepository.save(servico);
    }
    @Transactional
    public List<Servico> listarServico(){
        return servicoRepository.findAll();
    }

    @Transactional
    public Servico salvarServico(Servico servico){
        return servicoRepository.save(servico);
    }

    public Optional<Servico> buscarPorId(int id) {
        return servicoRepository.findById(id);
    }

    @Transactional
    public void excluirServico(Integer id){
        if (servicoRepository.existsById(id)){
            servicoRepository.deleteById(id);
        } throw new RuntimeException("Nao achemo o serviço");
    }




}
