package com.agenda.agendaProject.service;


import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    private AgendaRepository agendaRepository;

    public List<Agenda> listarAgendas() {
        return agendaRepository.findAll();
    }


    public Agenda salvarAgenda(Agenda agenda) {
        validarDuplicidade(agenda);
        agenda.setStatus(false);
        return agendaRepository.save(agenda);
    }

    @Transactional
    public Agenda atualizarAgenda(int id, Agenda agendaAtualizada){
        Agenda agendaExistente = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));
        if(agendaExistente.isStatus()){
            throw new RuntimeException("Agenda já foi atualizada");
        }
        validarDuplicidade(agendaAtualizada);
        agendaExistente.setDescricao(agendaAtualizada.getDescricao());
        agendaExistente.setServico(agendaAtualizada.getServico());
        agendaExistente.setData(agendaAtualizada.getData());
        agendaExistente.setCliente(agendaAtualizada.getCliente());
        agendaExistente.setFuncionario(agendaAtualizada.getFuncionario());
        agendaExistente.setUsuario(agendaAtualizada.getUsuario());

        return agendaRepository.save(agendaExistente);
    }

    private void validarDuplicidade(Agenda agenda) {
    }


    public void excluirAgenda(int id) {
        Agenda agendaExistente = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

        if (agendaExistente.isStatus()) {
            throw new RuntimeException("Agenda já foi atualizada");
        }
    }

    public Optional<Agenda> buscarAgendaPorId(Integer id) {
        return agendaRepository.findById(id);
    }
}
