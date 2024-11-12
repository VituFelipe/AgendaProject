package com.agenda.agendaProject.service;

import com.agenda.agendaProject.model.Servico;
import com.agenda.agendaProject.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarServicos(){
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(Long id){
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nao achou"));
    }

    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public void deletarServico(Long id) {
        Servico servico = buscarPorId(id);
        servicoRepository.delete(servico);
    }

}

