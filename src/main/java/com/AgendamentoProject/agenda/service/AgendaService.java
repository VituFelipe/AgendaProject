package com.AgendamentoProject.agenda.service;

import com.AgendamentoProject.agenda.entity.Agenda;
import com.AgendamentoProject.agenda.repository.AgendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Agenda salvarAgenda(Agenda agenda) {
        validarAgendamento(agenda);
        agenda.setStatus(false); // Define status como "não realizado" por padrão
        return agendaRepository.save(agenda);
    }

    public List<Agenda> listarAgendas() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> buscarAgendaId(int id) {
        return agendaRepository.findById(id);
    }

    @Transactional
    public Agenda atualizarAgenda(int id, Agenda agendaAtualizada) {
        Agenda agendaExistente = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

        if (agendaExistente.isStatus()) {
            throw new RuntimeException("Agenda já realizada. Não é possível editar.");
        }
        validarAgendamento(agendaAtualizada);

        agendaExistente.setDescricao(agendaAtualizada.getDescricao());
        agendaExistente.setServico(agendaAtualizada.getServico());
        agendaExistente.setData(agendaAtualizada.getData());
        agendaExistente.setCliente(agendaAtualizada.getCliente());
        agendaExistente.setFuncionario(agendaAtualizada.getFuncionario());
        agendaExistente.setUsuario(agendaAtualizada.getUsuario());

        return agendaRepository.save(agendaExistente);

    }


    private void validarAgendamento(Agenda agenda) {
        Optional<Agenda> agendaAtual = agendaRepository
                .findByClienteAndData(agenda.getCliente(), agenda.getData());

        if (agendaAtual.isPresent() && agendaAtual.get().getId() != agenda.getId()) {
            throw new RuntimeException("Já tem essa agenda");
        }

    }

    @Transactional
    public void realizarServico(int id) {
        Agenda agendaExistente = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agenda num foi achada nao amigo"));

        agendaExistente.setStatus(true);
        agendaRepository.save(agendaExistente);
    }

    @Transactional
    public boolean excluirAgenda(int id) {
        Agenda agendaExistente = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao encontrada"));
        if (agendaExistente.isStatus()) {
            throw new RuntimeException("Não é possível excluir.");
        }
        agendaRepository.deleteById(id);
        return true;
    }

}


