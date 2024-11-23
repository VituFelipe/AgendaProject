package com.AgendamentoProject.agenda.repository;

import com.AgendamentoProject.agenda.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
