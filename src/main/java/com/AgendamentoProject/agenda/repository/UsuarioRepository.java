package com.AgendamentoProject.agenda.repository;

import com.AgendamentoProject.agenda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
