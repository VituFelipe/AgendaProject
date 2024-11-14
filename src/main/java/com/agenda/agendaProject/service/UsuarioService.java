package com.agenda.agendaProject.service;

import com.agenda.agendaProject.model.TipoAcesso;
import com.agenda.agendaProject.model.Usuario;
import com.agenda.agendaProject.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(int id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Não tem o usuario ai parceiro"));

        usuarioExistente.setUsuario(usuarioAtualizado.getUsuario());
        usuarioExistente.setSenha(usuarioAtualizado.getSenha());
        usuarioExistente.setTipoAcesso(usuarioAtualizado.getTipoAcesso());

        return usuarioRepository.save(usuarioExistente);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public void deletarUsuarioId(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorUsuario(String usuario) {
        return usuarioRepository.findByUsuarioContainingIgnoreCase(usuario);
    }

    public List<Usuario> buscarPorTipoAcesso(TipoAcesso tipoAcesso) {
        return usuarioRepository.findByTipoAcesso(tipoAcesso);
    }
}