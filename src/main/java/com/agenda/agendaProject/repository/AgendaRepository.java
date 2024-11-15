package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    boolean existsByDataAndCliente(LocalDateTime data, Cliente cliente);

    Optional<Agenda> findByClienteAndData(Cliente cliente, LocalDateTime data);

    Optional<Agenda> findById(int id);
}
