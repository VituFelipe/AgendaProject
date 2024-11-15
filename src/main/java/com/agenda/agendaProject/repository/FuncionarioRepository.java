package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {


}
