package com.agenda.agendaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agendaId;


    private String descricao;

    @ManyToOne
    private Servico servico;

    private LocalDate data;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Funcionario funcionario;

    public boolean status;

    public long getId() {
        return agendaId;
    }

    public void setId(int id) {
        this.agendaId = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    }
}

//@Entity
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "agendas")
//public class Agenda {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String descricao;
//
//    private LocalDateTime dataHora;
//
//    private Cliente cliente;
//
//    @ManyToOne
//    private Servico servico;
//
//    @ManyToOne
//    private Funcionario funcionario;
//
//    private Boolean status = false;

//}
