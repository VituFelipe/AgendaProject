package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.Agenda;
import com.AgendamentoProject.agenda.entity.Cliente;
import com.AgendamentoProject.agenda.entity.Funcionario;
import com.AgendamentoProject.agenda.entity.Servico;
import com.AgendamentoProject.agenda.repository.AgendaRepository;
import com.AgendamentoProject.agenda.repository.ClienteRepository;
import com.AgendamentoProject.agenda.repository.FuncionarioRepository;
import com.AgendamentoProject.agenda.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String getRelatorios(Model model){
        List<Agenda> agendas = agendaRepository.findAll();
        List<Servico> servicos = servicoRepository.findAll();
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("agendas", agendas);
        model.addAttribute("servicos", servicos);
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("clientes", clientes);
        return "relatorios/relatorios";


    }
}
