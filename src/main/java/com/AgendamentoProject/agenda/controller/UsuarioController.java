package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.Usuario;
import com.AgendamentoProject.agenda.service.TipoAcessoService;
import com.AgendamentoProject.agenda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TipoAcessoService tipoAcessoService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuario/usuarios";
    }

    @GetMapping("/novo")
    public String addForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tiposAcesso", tipoAcessoService.listarTipoAcesso());
        return "usuario/usuarioform";
    }

    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("usuario", usuario);
        model.addAttribute("tiposAcesso", tipoAcessoService.listarTipoAcesso());
        return "usuario/usuarioform";
    }

    @PostMapping("/salvar")
    public String save(@Valid Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tiposAcesso", tipoAcessoService.listarTipoAcesso());
            return "usuario/usuarioform";
        }
        usuarioService.add(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/excluir/{id}")
    public String delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return "redirect:/usuarios";
    }
}


    /*@PostMapping("/salvar")
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
*/