package com.AgendamentoProject.agenda.repository;

import com.AgendamentoProject.agenda.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository  extends JpaRepository<Funcionario, Integer> {
}
