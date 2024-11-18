package com.AgendamentoProject.agenda.repository;

import com.AgendamentoProject.agenda.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
//    List<Servico> findbyNameIgnoreCase(String nome) ;
}
