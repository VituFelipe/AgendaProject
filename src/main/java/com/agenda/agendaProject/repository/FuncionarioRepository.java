package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Funcionario;
import com.agenda.agendaProject.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
}
