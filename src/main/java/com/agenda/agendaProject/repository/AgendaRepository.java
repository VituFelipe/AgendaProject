package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
