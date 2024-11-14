package com.agenda.agendaProject.repository;

import com.agenda.agendaProject.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByCeo(String cep);

}
