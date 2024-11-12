package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Optional<Agenda> findByDataAndClienteId(LocalDateTime data, int clienteId);
//    boolean existsByClienteAndData(Cliente cliente, LocalDate data);
//    List<Agenda> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
//
    boolean existsByClienteAndData(Long id);
}
