package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.model.Agenda;
import com.agenda.agendaProject.model.Cliente;
import com.agenda.agendaProject.model.Funcionario;
import com.agenda.agendaProject.model.Servico;
import com.agenda.agendaProject.repository.AgendaRepository;
import com.agenda.agendaProject.repository.ClienteRepository;
import com.agenda.agendaProject.repository.FuncionarioRepository;
import com.agenda.agendaProject.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/agenda")
    public String getAgendas(Model model) {
        List<Agenda> agendas = agendaRepository.findAll();
        model.addAttribute("agendas", agendas);
        return "relatorios";
    }

    @GetMapping("/servicos")
    public String getServicos(Model model) {
        List<Servico> servicos = servicoRepository.findAll();
        model.addAttribute("servicos", servicos);
        return "relatorios";
    }

    @GetMapping("/funcionarios")
    public String getFuncionarios(Model model) {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        model.addAttribute("funcionarios", funcionarios);
        return "relatorios";
    }

    @GetMapping("/clientes")
    public String getClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "relatorios";
    }
}


