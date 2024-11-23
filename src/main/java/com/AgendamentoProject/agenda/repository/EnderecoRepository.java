package com.AgendamentoProject.agenda.repository;

import com.AgendamentoProject.agenda.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
