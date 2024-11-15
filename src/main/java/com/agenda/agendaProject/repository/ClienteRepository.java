package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
