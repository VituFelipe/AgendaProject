package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.Endereco;
import com.AgendamentoProject.agenda.entity.Funcionario;
import com.AgendamentoProject.agenda.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView("funcionario/funcionarios");
        mv.addObject("funcionarios", funcionarioService.findAll());
        return mv;
    }

    public ModelAndView add(Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("funcionario/funcionarioform");
        mv.addObject("funcionarios", funcionarioService.findAll());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id) {
        funcionarioService.deleteById(id);
        return findAll();
    }

    @PostMapping
    public ModelAndView save(Funcionario funcionario) {
        funcionarioService.save(funcionario);
        return findAll();
    }

    @GetMapping("/new")
    public ModelAndView newFuncionario() {
        return add(new Funcionario());
    }


//    @GetMapping("/criar")
//    public String mostrarFormulario(Model model, @RequestParam(required = false) Integer id) {
//        Funcionario funcionario = (id != null) ?
//                funcionarioService.buscarPorId(id).orElse(new Funcionario()) :
//                new Funcionario();
//        if (funcionario.getEndereco() == null) {
//            funcionario.setEndereco(new Endereco());
//        }
//        model.addAttribute("funcionario", funcionario);
//        return "funcionarios/criar";
//    }
//
//    @GetMapping
//    public String listarFuncionario(Model model) {
//        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
//        model.addAttribute("funcionaros", funcionarios);
//        return "funcionarios/funcionarios";
//    }
//
//    @PostMapping("/salvar")
//    public String salvarFuncionario(@ModelAttribute Funcionario funcionario) {
//        if (funcionario.getEndereco() == null) {
//            funcionario.setEndereco(new Endereco());
//        }
//        funcionarioService.salvarFuncionario(funcionario);
//        return "redirect:/funcionarios";
//    }
//
//    @GetMapping("/excluir/{id}")
//    public String excluirFuncionario(@PathVariable int id) {
//        funcionarioService.excluirFuncionario(id);
//        return "redirect:/funcionarios";
//    }

}
