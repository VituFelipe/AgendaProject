package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.model.Cliente;
import com.agenda.agendaProject.model.Servico;
import com.agenda.agendaProject.model.Usuario;
import com.agenda.agendaProject.repository.AgendaRepository;
import com.agenda.agendaProject.repository.ClienteRepository;
import com.agenda.agendaProject.repository.ServicoRepository;
import com.agenda.agendaProject.repository.UsuarioRepository;
import com.agenda.agendaProject.service.AgendaService;
import com.agenda.agendaProject.service.ClienteService;
import com.agenda.agendaProject.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AgendaService agendaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FuncionarioService funcionarioService;


    @GetMapping("/form")
    public String formAgenda(Model model, @RequestParam(required = false) Integer id) {
        Agenda agenda = id != null ? agendaService.buscarAgendaPorId(id).orElse(new Agenda()) : new Agenda();
        model.addAttribute("agenda", agenda);
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("funcionarios", funcionarioService.listarTodosFuncionarios());
        return "agenda/form";
    }


    @GetMapping("/agenda")
    public String listarAgenda(Model model) {
        model.addAttribute("agenda", agendaRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("servicos", servicoRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "agenda";
    }

    @PostMapping("/save")
    public String salvarAgenda(@ModelAttribute Agenda agenda) {
        agendaService.salvarAgenda(agenda);
        return "redirect:/agenda";
    }

    @GetMapping("/editar/{id}")
    public String editarAgenda(@PathVariable("id") Integer id, Model model) {
        Agenda agenda = agendaService.buscarAgendaPorId(id).orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));
        model.addAttribute("agenda", agenda);
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("funcionarios", funcionarioService.listarTodosFuncionarios());
        return "agenda/form";
    }

    @PostMapping("/atualizar")
    public String atualizarAgenda(@ModelAttribute Agenda agenda) {
        agendaService.atualizarAgenda(agenda.getId(), agenda);
        return "redirect:/agenda/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAgenda(@PathVariable("id") Integer id) {
        agendaService.excluirAgenda(id);
        return "redirect:/agenda/listar";
    }

}
