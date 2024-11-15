package com.agenda.agendaProject.service;

import com.agenda.agendaProject.model.Servico;
import com.agenda.agendaProject.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarServicos(){
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(int id) {
        return servicoRepository.findById(id);
    }
    @Transactional
    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }
    @Transactional
    public Servico atualizarServico(int id, Servico servicoAtualizado) {
        Servico servicoExistente = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado amigão"));

        servicoExistente.setNome(servicoAtualizado.getNome());
        servicoExistente.setValor(servicoAtualizado.getValor());

        return servicoRepository.save(servicoExistente);
    }

    @Transactional
    public void excluirServico(int id) {
        if (servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
        } else {
            throw new RuntimeException("O amigo o serviço não foi encontrado");
        }
    }
}
