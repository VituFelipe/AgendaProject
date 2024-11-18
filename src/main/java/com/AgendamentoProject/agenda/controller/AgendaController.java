package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.Agenda;
import com.AgendamentoProject.agenda.repository.AgendaRepository;
import com.AgendamentoProject.agenda.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping("/criar")
    public String criarAgenda(Model model) {
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("clientes", clienteService.listarCliente());
        model.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        model.addAttribute("servicos", servicoService.listarServico());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "agenda/criar";
    }

    @GetMapping
    public String listarAgendas(Model model) {
        List<Agenda> agendas = agendaService.listarAgendas();
        System.out.println("Agendas retornadas: " + agendas);
        model.addAttribute("agendas", agendas);
        return "agenda/agenda";
    }


    @PostMapping("/salvar")
    public String salvarAgenda(@Valid @ModelAttribute("agenda") Agenda agenda, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteService.listarCliente());
            model.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
            model.addAttribute("servicos", servicoService.listarServico());
            model.addAttribute("usuarios", usuarioService.listarUsuarios());
            return "agenda/criar";
        }
        agendaService.salvarAgenda(agenda);
        return "redirect:/agenda";
    }

    @GetMapping("/editar/{id}")
    public String editarAgenda(@PathVariable("id") Integer id, Model model) {
        Agenda agenda = agendaService.buscarAgendaId(id).orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));
        model.addAttribute("agendas", agenda);
        model.addAttribute("clientes", clienteService.listarCliente());
        model.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        model.addAttribute("servicos", servicoService.listarServico());
        model.addAttribute("usuarios",usuarioService.listarUsuarios());
        return "/agenda/criar";
    }

    @PostMapping("/realizar/{id}")
    public String realizarServico(@RequestParam("id") int id) {
        // fazer redirecionar e depois o trycatch
        try {

            agendaService.realizarServico(id);
            return "redirect:/agenda";
        } catch (RuntimeException e) {
            return "error";
        }
    }

    @PostMapping("/atualizar")
    public String atualizarAgenda(@ModelAttribute Agenda agenda) {
        agendaService.atualizarAgenda(agenda.getId(), agenda);
        return "redirect:/agenda";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAgenda(@PathVariable("id") Integer id) {
        agendaService.excluirAgenda(id);
        return "redirect:/agenda";
    }

}
