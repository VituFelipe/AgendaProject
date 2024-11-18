package com.AgendamentoProject.agenda.repository;

import com.AgendamentoProject.agenda.entity.Agenda;
import com.AgendamentoProject.agenda.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    Optional<Agenda> findByClienteAndData(Cliente cliente, LocalDateTime data);
}
