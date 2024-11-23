package com.AgendamentoProject.agenda.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private int id;
    private String nomeUsuario;
    private String senhaUsuario;
    private LocalDateTime dataCadastro;

    @ManyToOne
    private TipoAcesso tipoAcesso;

    public Usuario(TipoAcesso tipoAcesso, int id, String nomeUsuario, String senhaUsuario, LocalDateTime dataCadastro) {
        this.tipoAcesso = tipoAcesso;
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public void setDataCadastro(Date date) {
    }
}
