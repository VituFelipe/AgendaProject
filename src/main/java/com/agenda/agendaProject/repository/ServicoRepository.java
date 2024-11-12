package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Optional<Servico> findByNome(String nome);
}