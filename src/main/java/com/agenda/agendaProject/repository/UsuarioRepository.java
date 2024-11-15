package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
