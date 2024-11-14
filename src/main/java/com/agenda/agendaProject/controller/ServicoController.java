package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.model.Servico;
import com.agenda.agendaProject.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public List<Servico> buscarServico(@RequestParam Long id){
        return (List<Servico>) servicoService.buscarPorId(id);
    }
}











