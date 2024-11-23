package com.AgendamentoProject.agenda.controller;

import com.AgendamentoProject.agenda.entity.Agenda;
import com.AgendamentoProject.agenda.repository.AgendaRepository;
import com.AgendamentoProject.agenda.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
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

    @GetMapping("/agendas")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView("/agenda/agendas");
        mv.addObject("agendas", agendaService.findAll());
        return mv;
    }

    @GetMapping("/agenda")
    public ModelAndView add(Agenda agenda, String mensagem){
        ModelAndView mv = new ModelAndView("agenda/agendaform");
        mv.addObject("agenda", agenda);
        mv.addObject("clientes", clienteService.findAll());
        mv.addObject("servicos", servicoService.findAll());
        mv.addObject("funcionarios", funcionarioService.findAll());
        mv.addObject("mensagem", mensagem);
        return  mv;
    }

    @GetMapping("/agendaDelete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        agendaService.delete(id);
        return findAll();
    }

    @PostMapping("/agenda")
    public ModelAndView save(Agenda agenda, BindingResult result){
        if(result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                System.out.println(error.getDefaultMessage());
            }
            return add(agenda, "Num da pra sarvaaaa");
        }

        if(agenda.getId() == null && agendaService.checarAgendamento(agenda.getData()) != 0){
            return add(agenda, "nao vai da pa marca amigo, numdaaaaa");
        }
        agendaService.add(agenda);
        return findAll();
    }

//    @GetMapping("/criar")
//    public String criarAgenda(Model model) {
//        model.addAttribute("agenda", new Agenda());
//        model.addAttribute("clientes", clienteService.listarCliente());
//        model.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
//        model.addAttribute("servicos", servicoService.listarServico());
//        model.addAttribute("usuarios", usuarioService.listarUsuarios());
//        return "agenda/criar";
//    }
//
//    @GetMapping
//    public String listarAgendas(Model model) {
//        List<Agenda> agendas = agendaService.listarAgendas();
//        System.out.println("Agendas retornadas: " + agendas);
//        model.addAttribute("agendas", agendas);
//        return "agenda/agenda";
//    }
//
//
//    @PostMapping("/salvar")
//    public String salvarAgenda(@Valid @ModelAttribute("agenda") Agenda agenda, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("clientes", clienteService.listarCliente());
//            model.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
//            model.addAttribute("servicos", servicoService.listarServico());
//            model.addAttribute("usuarios", usuarioService.listarUsuarios());
//            return "agenda/criar";
//        }
//        agendaService.salvarAgenda(agenda);
//        return "redirect:/agenda";
//    }
//
//    @GetMapping("/editar/{id}")
//    public String editarAgenda(@PathVariable("id") Integer id, Model model) {
//        Agenda agenda = agendaService.buscarAgendaId(id).orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));
//        model.addAttribute("agendas", agenda);
//        model.addAttribute("clientes", clienteService.listarCliente());
//        model.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
//        model.addAttribute("servicos", servicoService.listarServico());
//        model.addAttribute("usuarios",usuarioService.listarUsuarios());
//        return "/agenda/criar";
//    }
//
//    @PostMapping("/realizar/{id}")
//    public String realizarServico(@RequestParam("id") int id) {
//        // fazer redirecionar e depois o trycatch
//        try {
//
//            agendaService.realizarServico(id);
//            return "redirect:/agenda";
//        } catch (RuntimeException e) {
//            return "error";
//        }
//    }
//
//    @PostMapping("/atualizar")
//    public String atualizarAgenda(@ModelAttribute Agenda agenda) {
//        agendaService.atualizarAgenda(agenda.getId(), agenda);
//        return "redirect:/agenda";
//    }
//
//    @GetMapping("/excluir/{id}")
//    public String excluirAgenda(@PathVariable("id") Integer id) {
//        agendaService.excluirAgenda(id);
//        return "redirect:/agenda";
//    }

}
