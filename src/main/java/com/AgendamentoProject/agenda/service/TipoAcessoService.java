package com.AgendamentoProject.agenda.service;

import com.AgendamentoProject.agenda.entity.TipoAcesso;
import com.AgendamentoProject.agenda.repository.TipoAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAcessoService {

    @Autowired
    private TipoAcessoRepository tipoAcessoRepository;

    public List<TipoAcesso> listarTipoAcesso() {
        return tipoAcessoRepository.findAll();
    }

    public void adicionarTipoAcesso() {
        if (tipoAcessoRepository.count() == 0) {
            TipoAcesso admin = new TipoAcesso();
            admin.setDescricao("Admin");
            TipoAcesso usuario = new TipoAcesso();
            usuario.setDescricao("Usuario");
            TipoAcesso convidado = new TipoAcesso();
            convidado.setDescricao("Convidado");
            TipoAcesso moderador = new TipoAcesso();
            moderador.setDescricao("MODERADOR");
            TipoAcesso suporte = new TipoAcesso();
            suporte.setDescricao("SUPORTE");

            tipoAcessoRepository.save(admin);
            tipoAcessoRepository.save(usuario);
            tipoAcessoRepository.save(convidado);
            tipoAcessoRepository.save(moderador);
            tipoAcessoRepository.save(suporte);
        }
    }


}
