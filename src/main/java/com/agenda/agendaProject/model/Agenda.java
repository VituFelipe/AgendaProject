package com.agenda.agendaProject.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agendaId;
    private String descricao;

    @ManyToOne
    private Servico servico;

    private Date data;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Usuario usuario;

    public int getId() {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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
}
