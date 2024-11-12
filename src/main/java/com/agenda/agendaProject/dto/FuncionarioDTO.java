package com.agenda.agendaProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String email;

    private String telefone;

    private List<Long> servicosHabilitadosIds;
    private List<String> servicosHabilitadosNomes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Long> getServicosHabilitadosIds() {
        return servicosHabilitadosIds;
    }

    public void setServicosHabilitadosIds(List<Long> servicosHabilitadosIds) {
        this.servicosHabilitadosIds = servicosHabilitadosIds;
    }

    public List<String> getServicosHabilitadosNomes() {
        return servicosHabilitadosNomes;
    }

    public void setServicosHabilitadosNomes(List<String> servicosHabilitadosNomes) {
        this.servicosHabilitadosNomes = servicosHabilitadosNomes;
    }
}
