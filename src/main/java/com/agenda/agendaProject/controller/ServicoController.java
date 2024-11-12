package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.model.Servico;
import com.agenda.agendaProject.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/novo")
    public String novoServico(Model model) {
        model.addAttribute("servico", new Servico());
        return "servico-form";
    }

    @PostMapping("/salvar")
    public String salvarServico(@ModelAttribute("servico") Servico servico) {
        servicoService.salvarServico(servico);
        return "redirect:/servicos";
    }

    @GetMapping
    public String listarServicos(Model model) {
        model.addAttribute("servicos", servicoService.listarServicos());
        return "lista-servicos";
    }
}











