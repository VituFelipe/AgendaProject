package com.AgendamentoProject.agenda.service;

import com.AgendamentoProject.agenda.entity.TipoAcesso;
import com.AgendamentoProject.agenda.entity.Usuario;
import com.AgendamentoProject.agenda.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoAcessoService tipoAcessoService;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario){
        usuario.setDataCadastro(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id){
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario atualizarUsuario(Integer id, Usuario usuarioAtualizado){
        Usuario usuarioCriado = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tem o usuario não amigo"));
        usuarioCriado.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
        usuarioCriado.setSenhaUsuario(usuarioAtualizado.getSenhaUsuario());
        usuarioCriado.setTipoAcesso(usuarioAtualizado.getTipoAcesso());
        return usuarioRepository.save(usuarioCriado);
    }

    @Transactional
    public void excluirUsuario(int id){
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        } throw new RuntimeException("Nao tem o usuario amigo");

    }


}
