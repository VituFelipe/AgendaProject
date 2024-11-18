package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.TipoAcesso;
import com.AgendamentoProject.agenda.entity.Usuario;
import com.AgendamentoProject.agenda.service.TipoAcessoService;
import com.AgendamentoProject.agenda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TipoAcessoService tipoAcessoService;

    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "usuarios/criar";
        }
        if (usuario.getId() != 0) {
            usuarioService.atualizarUsuario(usuario.getId(), usuario);
        } else {
            usuario.setDataCadastro(LocalDateTime.now());
            usuarioService.salvarUsuario(usuario);
        }

        return "redirect:/usuarios";
    }

    @GetMapping("/adicionar")
    public String adicionarUsuario(Model model) {
        List<TipoAcesso> tiposAcesso = tipoAcessoService.listarTipoAcesso();
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tiposAcesso", tiposAcesso);
        return "usuarios/criar";
    }

    @GetMapping
    public String listarUsuario(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/usuarios";

    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable int id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<TipoAcesso> tiposAcesso = tipoAcessoService.listarTipoAcesso();
        model.addAttribute("usuario", usuario);
        model.addAttribute("tiposAcesso", tiposAcesso);

        return "usuarios/criar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable int id) {
        usuarioService.excluirUsuario(id);
        return "redirect:/usuarios";
    }


}
