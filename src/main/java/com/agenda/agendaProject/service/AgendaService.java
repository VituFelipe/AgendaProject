package com.agenda.agendaProject.service;


import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    private AgendaRepository agendaRepository;

    public List<Agenda> findAll() {
        return agendaRepository.findAll();
    }

    public Agenda findById(long id) {
        Optional<Agenda> agenda = agendaRepository.findById(id);
        return agenda.orElse(null);
    }

    public Agenda save(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    public Agenda update(Agenda agenda) {
        return agendaRepository.save(agenda); // save() serve tanto para inserir quanto para atualizar
    }

    public void deleteById(long id) {
        agendaRepository.deleteById(id);
    }
}
