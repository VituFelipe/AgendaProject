package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.TipoAcesso;
import com.AgendamentoProject.agenda.service.TipoAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tipoAcesso")
public class TipoAcessoController {

    @Autowired
    private TipoAcessoService tipoAcessoService;

    @GetMapping("/listar")
    public ModelAndView listarTodos() {
        ModelAndView mv = new ModelAndView("tipoAcesso/tipoAcessos");
        mv.addObject("tipoAcessos", tipoAcessoService.listarTipoAcesso());
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionar(TipoAcesso tipoAcesso) {
        ModelAndView mv = new ModelAndView("tipoAcesso/tipoAcessoForm");
        mv.addObject("tipoAcesso", tipoAcesso);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        TipoAcesso tipoAcesso = tipoAcessoService.listarTipoAcesso().stream()
                .filter(tipo -> tipo.getId().equals(id))
                .findFirst()
                .orElse(new TipoAcesso());
        return adicionar(tipoAcesso);
    }

    @PostMapping("/salvar")
    public String salvar(TipoAcesso tipoAcesso) {
        tipoAcessoService.listarTipoAcesso().add(tipoAcesso);
        return "redirect:/tipoAcesso/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        tipoAcessoService.listarTipoAcesso().removeIf(tipo -> tipo.getId().equals(id));
        return "redirect:/tipoAcesso/listar";
    }
}
