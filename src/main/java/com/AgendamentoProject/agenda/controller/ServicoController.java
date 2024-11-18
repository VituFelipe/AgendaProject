package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.Servico;
import com.AgendamentoProject.agenda.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/criar")
    public String criarServico(Model model) {
        model.addAttribute("servico", new Servico());
        return "servicos/criar";
    }

    @GetMapping("/editar/{id}")
    public String editarServico(@PathVariable("id") Integer id, Model model) {
        Optional<Servico> servicoOptional = servicoService.buscarPorId(id);
        if (servicoOptional.isPresent()) {
            model.addAttribute("servico", servicoOptional.get());
            return "servicos/criar";
        }
        return "redirect:/servicos";
    }

    @GetMapping
    public String listarServico(Model model) {
        List<Servico> servicos = servicoService.listarServico();
        model.addAttribute("servicos", servicos);
        return "servicos/servicos";
    }

    @PostMapping("/salvar")
    public String salvarServico(@ModelAttribute("servico") Servico servico) {
        servicoService.salvarServico(servico);
        return "redirect:/servicos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirServico(@PathVariable int id) {
        servicoService.excluirServico(id);
        return "redirect:/servicos";
    }
}

