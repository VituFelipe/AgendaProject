package com.agenda.agendaProject.controller;

import com.agenda.agendaProject.dto.EnderecoDTO;
import com.agenda.agendaProject.model.Endereco;
import com.agenda.agendaProject.model.Funcionario;
import com.agenda.agendaProject.service.EnderecoService;
import com.agenda.agendaProject.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/novo")
    public String novoFuncionario(Model model){
        //aqui quero nde o formulário para adicionar um novo funcionário seria exibido
        model.addAttribute("funcionario", new Funcionario());
        return funcionarioService.listarFuncionarios().toString();
    }

    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute("funcionario") Funcionario funcionario, @RequestParam("cep") String cep) {
        EnderecoDTO endereco = enderecoService.buscaEnderecoPorCep(cep);
        funcionario.setEndereco(endereco);
        funcionarioService.salvarFuncionario(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        return "lista-funcionarios";
    }

    @GetMapping("/{id}")
    public String detalhesFuncionario(@PathVariable("id") Long id, Model model) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        model.addAttribute("funcionario", funcionario);
        return "detalhes-funcionario";
    }
}
