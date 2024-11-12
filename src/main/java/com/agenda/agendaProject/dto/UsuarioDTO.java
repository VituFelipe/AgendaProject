package com.agenda.agendaProject.dto;

import java.util.Date;

public class UsuarioDTO {

    private Integer id;
    private String usuario;
    private String senha;
    private Date dtCadastro;
    private TipoAcessoDTO tipoAcesso;

    public UsuarioDTO(Integer id, String usuario, String senha, Date dtCadastro, TipoAcessoDTO tipoAcesso) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.dtCadastro = dtCadastro;
        this.tipoAcesso = tipoAcesso;
    }
}
